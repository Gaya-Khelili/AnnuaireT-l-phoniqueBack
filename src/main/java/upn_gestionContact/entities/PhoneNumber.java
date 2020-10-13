package upn_gestionContact.entities;

public class PhoneNumber {
    private long idNumber;
    private String phoneKind,phoneNumber;

    public PhoneNumber(String phoneKind,String phoneNumber) {
        //this.idNumber=idNumber;
        this.phoneKind=phoneKind;
        this.phoneNumber=phoneNumber;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdPhone(long idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneKind() {
        return phoneKind;
    }

    public void setPhoneKind(String phoneKind) {
        this.phoneKind = phoneKind;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
