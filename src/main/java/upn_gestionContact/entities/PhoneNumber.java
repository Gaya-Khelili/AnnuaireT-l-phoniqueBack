package upn_gestionContact.entities;

import javax.persistence.*;
import java.io.Serializable;

public class PhoneNumber implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8715943084142676443L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String phoneKind;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name="id_contact")
    private Contact contact = null;

    public PhoneNumber() {

    }
    public PhoneNumber( String phoneKind, String phoneNumber) {

        this.phoneKind = phoneKind;
        this.phoneNumber = phoneNumber;

    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "PhoneNumber [id=" + id + ", phoneKind=" + phoneKind + ", phoneNumber=" + phoneNumber + ", contact="
                + contact + "]";
    }

}
