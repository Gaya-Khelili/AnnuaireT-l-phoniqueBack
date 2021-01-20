package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.ContactDAOImpl;
import upn_gestionContact.entities.Address;
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

    @Override
    public  List<Contact> search(String criteria){

        List<Phone> searchedPhone =  super.getDao().search(criteria);
        List<Contact> associatedContact = new ArrayList<>();

        searchedPhone.forEach(phone -> {
            Contact c = new Contact();
            c.setidContact(phone.getContact().getidContact());
            c.setfname(phone.getContact().getfname());
            c.setlname(phone.getContact().getlname());
            c.setEmail(phone.getContact().getEmail());
            associatedContact.add(c);
        });
        return associatedContact;
    }
}
