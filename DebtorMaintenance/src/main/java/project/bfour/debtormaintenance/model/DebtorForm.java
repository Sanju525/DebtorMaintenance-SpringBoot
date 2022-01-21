package project.bfour.debtormaintenance.model;

public class DebtorForm {

    private String debtorId; // Debtor ID
    private int debtorFormId;
    private String name;
    private String address1;
    private String address2;
    private long fax;
    private long phone;
    private String email;

    public DebtorForm() {
    }

    public DebtorForm(String debtorId, int debtorFormId, String name, String address1, String address2, long fax, long phone, String email) {
        this.debtorId = debtorId;
        this.debtorFormId = debtorFormId;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.fax = fax;
        this.phone = phone;
        this.email = email;
    }

    public int getDebtorFormId() {
        return debtorFormId;
    }

    public void setDebtorFormId(int debtorFormId) {
        this.debtorFormId = debtorFormId;
    }

    public String getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(String debtorId) {
        this.debtorId = debtorId;
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

    @Override
    public String toString() {
        return "DebtorForm{" +
                "debtorId='" + debtorId + '\'' +
                ", name='" + name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", fax=" + fax +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
