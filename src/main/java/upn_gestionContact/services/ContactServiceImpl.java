package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.AddressDAOImpl;
import upn_gestionContact.dao.Dao;
import upn_gestionContact.entities.Address;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.PhoneNumber;

import java.io.DataInput;
import java.util.Optional;

@Service
public class ContactServiceImpl extends AbstractService<Contact> {

    private ContactServiceImpl() {
        super();
    }

    @Override
    public void saveFullContact(Contact contact, Address address, PhoneNumber phoneNumber){
        contact.setAddress(address);
        address.setContact(contact);

        contact.getPhones().add(phoneNumber);
        phoneNumber.setContact(contact);

        Optional<Contact> optionalContact = getDao().save(contact);

    }

}
