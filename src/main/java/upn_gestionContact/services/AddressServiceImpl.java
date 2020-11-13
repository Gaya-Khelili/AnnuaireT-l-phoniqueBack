package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.ContactDAOImpl;
import upn_gestionContact.entities.Address;
import upn_gestionContact.entities.Contact;

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
        if (optionalContact.isPresent()){
            return super.getDao().findById(optionalContact.get().getAddress().getIdAddress());
        }
        return Optional.empty();
    }

}
