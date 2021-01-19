package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.ContactGroupDAOImpl;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;
import java.util.*;

@Service
public class ContactServiceImpl extends AbstractService<Contact> {

    private ContactServiceImpl() {
        super();
    }
    @Autowired
    private ContactGroupDAOImpl contactGroupDao;
    @Override
    public void saveFullContact(Contact contact){

        contact.getPhones().forEach(phone -> phone.setContact(contact));

        getDao().save(contact);
    }


    //trouver tout les contacts qui appartiennent à un groupe
    @Override
  public Set<Contact> findByIdGroupContactList(long groupId){
        Optional<ContactGroup> optionalGroup = contactGroupDao.findById(groupId);
        ContactGroup cg = optionalGroup.get();
        if (optionalGroup.isPresent()) {
            Set<Contact> contacts = new HashSet<>();
            cg.getContacts()
                    .forEach(contact -> {
                        Optional<Contact> optionalContact = super.getDao().findById(contact.getidContact());
                        optionalContact.ifPresent(contacts::add);
                    });
            return contacts;
        }
            return null;
    }

}


