package upn_gestionContact.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c")
public class Contact implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3275341715333606008L;

    private String fname;
    private String lname;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcontact;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_address")
    private Address address;

    @OneToMany(cascade=CascadeType.ALL  ,mappedBy="contact",fetch = FetchType.LAZY)
    Set<PhoneNumber> phones =new HashSet<PhoneNumber>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="CTC_GRP",
            joinColumns=@JoinColumn(name="CTC_ID"),
            inverseJoinColumns=@JoinColumn(name="GRP_ID"))
    private Set<ContactGroup> contactGroups = new HashSet<>();

    public Contact(){
    }
    public Contact(String lastName, String firstName , String email) {
        this.lname = lastName;
        this.fname = firstName;
        this.email = email;

    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getfname(){
        return fname;
    }

    public void setfname(String fName){
        this.fname = fName;
    }


    public String getlname(){
        return lname;
    }

    public void setlname(String lname){
        this.lname = lname;
    }

    public long getIdcontact() {
        return idcontact;
    }

    public void setIdcontact(long idcontact) {
        this.idcontact = idcontact;
    }


        public Address getAddress() {
            return address;
        }
        public void setAddress(Address address) {
            this.address = address;
        }

    public Set<PhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneNumber> phones) {
        this.phones = phones;
    }

    public Set<ContactGroup> getContactGroups() {
        return contactGroups;
    }

    public void setContactGroups(Set<ContactGroup> contactGroups) {
        this.contactGroups = contactGroups;
    }

    @Override
    public String toString() {
        return "Contact [firstName=" + fname + ", lastName=" + lname + ", email=" + email + ", id_contact=" + idcontact + "]";
    }


}
