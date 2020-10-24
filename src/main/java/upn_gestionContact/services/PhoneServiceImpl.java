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
        if (optionalContact.isPresent()){
            Set<Phone> phones = new HashSet<>();
             optionalContact.get().getPhones()
                    .forEach(phone -> {
                       Optional<Phone> optionalPhoneNumber = super.getDao().findById(phone.getIdPhone());
                       if (optionalPhoneNumber.isPresent()){
                           phones.add(optionalPhoneNumber.get());
                       }
                    });
                 return phones;
        }
        return null;
    }
}
