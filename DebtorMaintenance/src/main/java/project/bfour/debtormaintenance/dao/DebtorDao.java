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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Repository
public class DebtorDao {

    public DataSource datasource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/project1")
                .username("root")
                .password("12345678")
                .build();
    }

    DataSource dataSource = datasource();

    public boolean insertForm(DebtorForm debtorForm, BankAccount bankAccount, Transaction transaction) {
        String df_stmt = "insert into debtorform " +
                " values(?,?,?,?,?,?,?,?)";
        String ba_stmt = "insert into bankform  values (?,?,?,?,?,?,?)";
        String txn_stmt = "insert into transaction values(?,?,?,?,?)";

        int debtorFormId = getNewDebtorFormId(dataSource);
        int bankFormId = getNewBankFormId(dataSource);

        JdbcTemplate jdbcTemplate  = new JdbcTemplate(dataSource);
        int df_inserted = jdbcTemplate.update(df_stmt, debtorFormId,
                debtorForm.getDebtorId(),
                debtorForm.getName(),
                debtorForm.getAddress1(),
                debtorForm.getAddress2(),
                debtorForm.getFax(),
                debtorForm.getPhone(),
                debtorForm.getEmail());

        int bf_inserted = jdbcTemplate.update(ba_stmt, bankFormId,
                debtorFormId,
                bankAccount.getNumber(),
                bankAccount.getBankName(),
                bankAccount.getBranchName(),
                bankAccount.getSwiftAddress(),
                bankAccount.getAccountCurrency());

        int tx_inserted = jdbcTemplate.update(txn_stmt, transaction.getTransactionId(),
                bankFormId,
                transaction.getDateTime(),
                transaction.getStatus(),
                transaction.getInformation());

        return df_inserted == 1 && bf_inserted == 1 && tx_inserted == 1;
    }

    //    For Debtor to view
    public List<DetailsForm> getDebtorDetails(String uid){
        String query = "select user.uid\n" +
                ", df.name, df.address1, df.address2, df.email, df.fax, df.phone\n" +
                ", bf.accountNumber, bf.bankName, bf.branchName, bf.swiftAddress, bf.accountCurrency\n" +
                ", tx.transactionId, tx.date_time, tx.status, tx.information from user\n" +
                "\n" +
                "inner join debtorform as df on df.debtorId=user.uid\n" +
                "\n" +
                "inner join bankform as bf on  bf.debtorFormId=df.debtorFormId\n" +
                "\n" +
                "inner join transaction as tx on tx.bankFormId=bf.bankFormId where uid=? order by tx.date_time desc;";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<DetailsForm> detailsForms = jdbcTemplate.query(query, new RowMapper<DetailsForm>() {
            @Override
            public DetailsForm mapRow(ResultSet rs, int rowNum) throws SQLException {
                DetailsForm detailsForm = new DetailsForm(
                        rs.getString("uid"),
                        rs.getString("name"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getLong("fax"),
                        rs.getLong("phone"),
                        rs.getString("email"),
                        rs.getLong("accountNumber"),
                        rs.getString("bankName"),
                        rs.getString("branchName"),
                        rs.getString("swiftAddress"),
                        rs.getString("accountCurrency"),
                        rs.getString("transactionId"),
                        rs.getString("date_time"),
                        rs.getString("status"),
                        rs.getString("information")
                );
                return detailsForm;
            }
        }, uid);
        return detailsForms;
    }

    //    for view more Details view
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

    public Transaction getNewTxn() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // sql formatting
        Transaction transaction = new Transaction(); // init
        transaction.setTransactionId(getNewTxnId(dataSource)); // new txn id
        transaction.setDateTime(formatter.format(LocalDateTime.now())); // date time
        transaction.setStatus("P"); // default
        transaction.setInformation("Pending"); // default
        return transaction;
    }

    private static String getNewTxnId(DataSource dataSource) {
        String txnId = UUID.randomUUID().toString().replace("-", "");
        String checkQuery = "select count(*) as count from transaction where transactionId='" + txnId +  "'";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int count = jdbcTemplate.queryForObject(checkQuery, Integer.class);
        while (count != 0) {
            txnId = UUID.randomUUID().toString().replace("-", "");
        }
        return txnId;
    }

    private static int getNewDebtorFormId(DataSource dataSource) {
        String checkQuery = "select count(*) from debtorform";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int count = jdbcTemplate.queryForObject(checkQuery, Integer.class);
        if (count == 0) {
            return 1;
        }
        String query = "select max(debtorFormId) from debtorform";
        int id = jdbcTemplate.queryForObject(query, Integer.class);
        return (++id);
    }

    private static int getNewBankFormId(DataSource dataSource) {

        String checkQuery = "select count(*) from bankform";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int count = jdbcTemplate.queryForObject(checkQuery, Integer.class);
        if (count == 0) {
            return 1;
        }
        String query = "select max(bankFormId) from bankform";
        int id = jdbcTemplate.queryForObject(query, Integer.class);
        return (++id);
    }
}
