package project.bfour.debtormaintenance.model;

public class DetailsForm {

    //    Debtor form
    private String Id; // uid
    private String name;
    private String address1;
    private String address2;
    private long fax;
    private long phone;
    private String email;

    //    Bank Form
    private long number;
    private String bankName;
    private String branchName;
    private String swiftAddress;
    private String accountCurrency;

    //    Transaction
    private String txnId;
    private String dateTime;
    private String status;
    private String information;

    public DetailsForm() {
    }

    public DetailsForm(String id, String name, String address1, String address2, long fax, long phone, String email, long number, String bankName, String branchName, String swiftAddress, String accountCurrency, String txnId, String dateTime, String status, String information) {
        Id = id;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.fax = fax;
        this.phone = phone;
        this.email = email;
        this.number = number;
        this.bankName = bankName;
        this.branchName = branchName;
        this.swiftAddress = swiftAddress;
        this.accountCurrency = accountCurrency;
        this.txnId = txnId;
        this.dateTime = dateTime;
        this.status = status;
        this.information = information;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public long getFax() {
        return fax;
    }

    public void setFax(long fax) {
        this.fax = fax;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
