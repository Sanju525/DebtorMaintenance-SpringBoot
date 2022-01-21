package project.bfour.debtormaintenance.model;


public class BankAccount {
    private int debtorFormId;
    private long number;
    private String bankName;
    private String branchName;
    private String swiftAddress;
    private String accountCurrency;

    public BankAccount() {
    }

    public BankAccount(int debtorFormId, long number, String bankName, String branchName, String swiftAddress, String accountCurrency) {
        this.debtorFormId = debtorFormId;
        this.number = number;
        this.bankName = bankName;
        this.branchName = branchName;
        this.swiftAddress = swiftAddress;
        this.accountCurrency = accountCurrency;
    }

    public int getDebtorFormId() {
        return debtorFormId;
    }

    public void setDebtorFormId(int debtorFormId) {
        this.debtorFormId = debtorFormId;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSwiftAddress() {
        return swiftAddress;
    }

    public void setSwiftAddress(String swiftAddress) {
        this.swiftAddress = swiftAddress;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "debtorFormId=" + debtorFormId +
                ", number=" + number +
                ", bankName='" + bankName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", swiftAddress='" + swiftAddress + '\'' +
                ", accountCurrency='" + accountCurrency + '\'' +
                '}';
    }
}
