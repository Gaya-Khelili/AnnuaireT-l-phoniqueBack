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


    public Address() {

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

    @Override
    public String toString() {
        return "Address [id_address=" + idAddress + ", street=" + street + ", city=" + city + ", zip=" + zip + ", country="
        + country + "]";
    }

}
