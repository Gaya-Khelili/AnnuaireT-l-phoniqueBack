package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.ContactDAOImpl;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.Phone;

import java.util.*;

@Service
public class PhoneServiceImpl extends AbstractService<Phone> {

    @Autowired
    private ContactDAOImpl contactDao;
    
    @Override
    public Set<Phone> findByIdContactList(long idContact){
        Optional<Contact> optionalContact = contactDao.findById(idContact);
        return optionalContact.map(Contact::getPhones).orElse(null);
    }
}
