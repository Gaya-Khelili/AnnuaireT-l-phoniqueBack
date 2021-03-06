package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.ContactDAOImpl;
import upn_gestionContact.entities.Address;
import upn_gestionContact.entities.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AddressServiceImpl extends AbstractService<Address>{

    @Autowired
    private ContactDAOImpl contactDao;

    public AddressServiceImpl() {
        super();
    }

    @Override
    public Optional<Address> findByIdContact(long id){
        Optional<Contact> optionalContact = contactDao.findById(id);
        return optionalContact.map(contact -> Optional.ofNullable(contact.getAddress())).orElse(null);
    }

    @Override
    public List<Contact> search(String criteria){
        List<Address> addresses =  getDao().search(criteria);
        List<Contact> associatedContact = new ArrayList<>();

        addresses.forEach(address -> {
            associatedContact.add(contactDao.getContactByIdAddress(address.getIdAddress()));
        });
        return associatedContact;
    }

    @Override
    public void fillDatabase() {

    }

}
