package upn_gestionContact.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name="Phone.findAll", query="SELECT p FROM Phone p")
public class Phone implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8715943084142676443L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPhone;
    private String phoneKind;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name="idContact")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Contact contact = null;

    public Phone() {

    }
    public Phone(String phoneKind, String phoneNumber) {

        this.phoneKind = phoneKind;
        this.phoneNumber = phoneNumber;
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

    public long getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(long idPhone) {
        this.idPhone = idPhone;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "PhoneNumber [id=" + idPhone + ", phoneKind=" + phoneKind + ", phoneNumber=" + phoneNumber
                +  "]";
    }

}
