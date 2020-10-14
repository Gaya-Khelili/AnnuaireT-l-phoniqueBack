package upn_gestionContact.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c")
public class Contact implements Serializable {

    private String fname;
    private String lname;
    private String email;

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idContact;

    public Contact(){}

    public Contact(String fname,String lname,String email) {
        this.fname=fname;
        this.lname=lname;
        this.email=email;
    }

    public Contact(int id,String fname,String lname,String email) {
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.idContact=id;
    }

    public long getIdContact() {
        return this.idContact;
    }

    public String getfname() {
        return this.fname;
    }

    public String getlname() {
        return this.lname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(long id) {
        this.idContact=id;
    }

    public void setlname(String lname) {
        this.lname=lname;
    }

    public void setfname(String fname) {
        this.fname=fname;
    }

    public void setEmail(String email) {
        this.email=email;
    }

}
