package project.bfour.debtormaintenance.model;

public class Transaction {
    private int bankFormId;
    private String transactionId;
    private String dateTime;
    private String status;
    private String information;

    public Transaction() {
    }

    public Transaction(int bankFormId, String transactionId, String dateTime, String status, String information) {
        this.bankFormId = bankFormId;
        this.transactionId = transactionId;
        this.dateTime = dateTime;
        this.status = status;
        this.information = information;
    }

    public int getBankFormId() {
        return bankFormId;
    }

    public void setBankFormId(int bankFormId) {
        this.bankFormId = bankFormId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "bankFormId=" + bankFormId +
                ", transactionId='" + transactionId + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", status='" + status + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
}
