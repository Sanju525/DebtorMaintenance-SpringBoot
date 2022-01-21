package project.bfour.debtormaintenance.dao;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import project.bfour.debtormaintenance.model.BankAccount;
import project.bfour.debtormaintenance.model.DebtorForm;
import project.bfour.debtormaintenance.model.DetailsForm;
import project.bfour.debtormaintenance.model.Transaction;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDao {

    public DataSource datasource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/project1")
                .username("root")
                .password("12345678")
                .build();
    }

    DataSource dataSource = datasource();

    public DetailsForm getDebtorDetailsByTxnId(String txnId) {
        String txnQuery = "select * from transaction where transactionId=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Transaction txnSet = jdbcTemplate.queryForObject(txnQuery, new RowMapper<Transaction>() {
            @Override
            public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Transaction(rs.getInt("bankFormId"),
                        rs.getString("transactionId"),
                        rs.getString("date_time"),
                        rs.getString("status"),
                        rs.getString("information"));
            }
        }, txnId);

        int bankFormId = txnSet.getBankFormId();

        String bankQuery = "select * from bankform where bankFormId=?";
        BankAccount bnkSet = jdbcTemplate.queryForObject(bankQuery, new RowMapper<BankAccount>() {
            @Override
            public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new BankAccount(rs.getInt("debtorFormId"),
                        rs.getLong("accountNumber"),
                        rs.getString("bankName"),
                        rs.getString("branchName"),
                        rs.getString("swiftAddress"),
                        rs.getString("accountCurrency"));
            }
        }, bankFormId);

        int debtorFormId = bnkSet.getDebtorFormId();

        String debQuery = "select * from debtorform where debtorFormId=?";
        DebtorForm debSet = jdbcTemplate.queryForObject(debQuery, new RowMapper<DebtorForm>() {
            @Override
            public DebtorForm mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new DebtorForm(rs.getString("debtorId"),
                        rs.getInt("debtorFormId"),
                        rs.getString("name"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getLong("fax"),
                        rs.getLong("phone"),
                        rs.getString("email"));
            }
        }, debtorFormId);

        return new DetailsForm(
                debSet.getDebtorId(),
                debSet.getName(),
                debSet.getAddress1(),
                debSet.getAddress2(),
                debSet.getFax(),
                debSet.getPhone(),
                debSet.getEmail(),
                bnkSet.getNumber(),
                bnkSet.getBankName(),
                bnkSet.getBranchName(),
                bnkSet.getSwiftAddress(),
                bnkSet.getAccountCurrency(),
                txnSet.getTransactionId(),
                txnSet.getDateTime(),
                txnSet.getStatus(),
                txnSet.getInformation()
        );
    }

    public List<DetailsForm> getDebtorDetails() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String query = "select user.uid\n" +
                ", df.name, df.address1, df.address2, df.email, df.fax, df.phone\n" +
                ", bf.accountNumber, bf.bankName, bf.branchName, bf.swiftAddress, bf.accountCurrency\n" +
                ", tx.transactionId, tx.date_time, tx.status, tx.information from user\n" +
                "\n" +
                "inner join debtorform as df on df.debtorId=user.uid\n" +
                "\n" +
                "inner join bankform as bf on  bf.debtorFormId=df.debtorFormId\n" +
                "\n" +
                "inner join transaction as tx on tx.bankFormId=bf.bankFormId where tx.status=\"P\" " +
                "order by tx.date_time desc;";

        return template.query(query, new RowMapper<DetailsForm>() {
            @Override
            public DetailsForm mapRow (ResultSet rs, int rowNum) throws SQLException {
                DetailsForm detailsForm = new DetailsForm();
                detailsForm.setId(rs.getString("uid"));
                detailsForm.setName(rs.getString("name"));
                detailsForm.setAddress1(rs.getString("address1"));
                detailsForm.setAddress2(rs.getString("address2"));
                detailsForm.setFax(rs.getLong("fax"));
                detailsForm.setPhone(rs.getLong("phone"));
                detailsForm.setEmail(rs.getString("email"));
                detailsForm.setNumber(rs.getLong("accountNumber"));
                detailsForm.setBankName(rs.getString("bankName"));
                detailsForm.setBranchName(rs.getString("branchName"));
                detailsForm.setSwiftAddress(rs.getString("swiftAddress"));
                detailsForm.setAccountCurrency(rs.getString("accountCurrency"));
                detailsForm.setTxnId(rs.getString("transactionId"));
                detailsForm.setDateTime(rs.getString("date_time"));
                detailsForm.setStatus(rs.getString("status"));
                detailsForm.setInformation(rs.getString("information"));
                return detailsForm;
            }
        });
    }

    public int authorize(String txnId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "update transaction set status=\"A\", information=\"Authorized\" where transactionId=?";
        return jdbcTemplate.update(query, txnId);
    }

    public int reject(String txnId, String reason) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "update transaction set status=\"R\", information=? where transactionId=?";
        return jdbcTemplate.update(query, reason, txnId);
    }


    public int getPendingFormCount() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "select count(*) from transaction where status='P'";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
