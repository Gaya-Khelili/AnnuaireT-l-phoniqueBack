package upn_gestionContact.entities;


import com.fasterxml.jackson.annotation.JsonProperty;

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
    private long idContact;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="id_address")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Address address;

    @OneToMany(cascade=CascadeType.ALL  ,mappedBy="contact")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<Phone> phones =new HashSet<Phone>();

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

    public long getidContact() {
        return idContact;
    }

    public void setidContact(long idContact) {
        this.idContact = idContact;
    }

    public Address getAddress() {
            return address;
        }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }
/*
    public Set<ContactGroup> getContactGroups() {
        return contactGroups;
    }
*/
    public void setContactGroups(Set<ContactGroup> contactGroups) {
        this.contactGroups = contactGroups;
    }

    @Override
    public String toString() {
        return "Contact [firstName=" + fname + ", lastName=" + lname + ", email=" + email + ", id_contact=" + idContact + "]";
    }


}
