package upn_gestionContact.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
@NamedQuery(name="Address.search", query = "SELECT a FROM Address a WHERE a.street LIKE :criteria OR a.city LIKE :criteria OR a.zip LIKE : criteria OR a.country LIKE :criteria")
public class Address implements Serializable {

/**
 *
 */
private static final long serialVersionUID = -1020164386467891074L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAddress;
    private String street, city, zip ,country;

    /**
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="idContact")
   @OneToOne(mappedBy="address")
    private Contact contact;  **/



    public Address() {

    }

    public Address( String street, String city, String zip, String country) {

        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;

    }

    public long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(long id_address) {
        this.idAddress = id_address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
/*
    public long getId_address() {
        return id_address;
    }

    public void setId_address(long id_address) {
        this.id_address = id_address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

 */



    @Override
    public String toString() {
        return "Address [id_address=" + idAddress + ", street=" + street + ", city=" + city + ", zip=" + zip + ", country="
        + country + "]";
    }

}
