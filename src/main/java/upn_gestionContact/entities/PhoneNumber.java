package upn_gestionContact.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name="PhoneNumber.findAll", query="SELECT p FROM PhoneNumber p")
public class PhoneNumber implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8715943084142676443L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPhoneNumber;
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


    @Override
    public String toString() {
        return "PhoneNumber [id=" + idPhoneNumber + ", phoneKind=" + phoneKind + ", phoneNumber=" + phoneNumber
                +  "]";
    }

}
