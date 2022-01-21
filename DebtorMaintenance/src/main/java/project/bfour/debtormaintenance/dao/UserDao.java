package project.bfour.debtormaintenance.dao;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import project.bfour.debtormaintenance.model.User;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


@Repository
public class UserDao {

    public DataSource datasource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/project1")
                .username("root")
                .password("12345678")
                .build();
    }

    DataSource dataSource = datasource();

    //    Register
    public int register (User user) {
        int status;
        String checkUsername = "select count(*) from user where username=?";
        JdbcTemplate jdbcTemplate  = new JdbcTemplate(dataSource);
        int exists = jdbcTemplate.queryForObject(checkUsername, Integer.class, new Object[] {user.getUsername()});
        if (exists>0) {
            return -1;
        }
        String hashedPassword = applySha256(user.getPassword());
        String register = "insert into user(username, password, uid) values (?,?,?)";
        status = jdbcTemplate.update(register, user.getUsername(), hashedPassword, getNewDebId());
        return status;
    }

    //    Login
    public int validate (User user, String who) {
        String nameQuery;
        String countQuery;
        String passQuery;
        if (who.equals("deb")) {
            countQuery = "select count(*) from user where username=\""+ user.getUsername() + "\" and uid like 'deb-%'";
            nameQuery = "select username from user where username=\""+ user.getUsername() + "\" and uid like 'deb-%'";
            passQuery = "select password from user where username=\""+ user.getUsername() + "\" and uid like 'deb-%'";
        } else {
            countQuery = "select count(*) from user where username=\""+ user.getUsername() + "\" and uid like 'adm-%'";
            nameQuery = "select username from user where username=\""+ user.getUsername() + "\" and uid like 'adm-%'";
            passQuery = "select password from user where username=\""+ user.getUsername() + "\" and uid like 'adm-%'";
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int userExists = jdbcTemplate.queryForObject(countQuery, Integer.class);
        if (userExists == 0) {
            return 0;
        }

        String un = jdbcTemplate.queryForObject(nameQuery, String.class);
        String pw = jdbcTemplate.queryForObject(passQuery, String.class);
        User dbUser = new User(un, pw);
        user.setPassword(applySha256(user.getPassword()));
        if (dbUser.equals(user)) {
            return 1;
        } else if (dbUser.getUsername().equals(user.getUsername()) &&
                !dbUser.getPassword().equals(user.getPassword())) {
            return 10;
        }
        return 0;
    }


    private String getNewDebId () {
        String uid;
        String checkQuery = "select count(*) as count from user where uid like \"deb-%\"";
        JdbcTemplate jdbcTemplate  = new JdbcTemplate(dataSource);
        int count = jdbcTemplate.queryForObject(checkQuery, Integer.class);
        if (count == 0) {
            return "deb-100";
        }
        String query = "select max(uid) as max from user where uid like \"deb-%\"";
        uid = jdbcTemplate.queryForObject(query, String.class);
        uid = uid.replace("deb-", "");
        int id = Integer.parseInt(uid);
        return "deb-" + (++id);
    }

    //    returns uid->who
    public String getUid(String username) {
        String query = "select uid from user where username=\"" + username + "\"";
        JdbcTemplate jdbcTemplate  = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(query, String.class);
    }

    //    applies 256-bit encryption
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
