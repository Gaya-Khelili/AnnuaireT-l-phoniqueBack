package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.ContactGroupDAOImpl;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl extends AbstractService<Contact> {

    private ContactServiceImpl() {
        super();
    }

    @Autowired
    private ContactGroupDAOImpl contactGroupDao;


    @Override
    public void saveFullContact(Contact contact){
       /** contact.getContactGroups().forEach(contactGroup ->
                contactGroup.getContacts().add(contact));**/

        contact.getPhones().forEach(phone -> phone.setContact(contact));
        getDao().save(contact);
    }
      @Override
    //trouver tout les contacts qui appartiennent à un groupe
  public Set<Contact> findByIdGroupContactList(long groupId){
        Optional<ContactGroup> optionalGroup = contactGroupDao.findById(groupId);

        if (optionalGroup.isPresent()) {
            Set<Contact> contacts = new HashSet<>();
            optionalGroup.get().getContacts()
                    .forEach(contact -> {
                        Optional<Contact> optionalContact = super.getDao().findById(contact.getidContact());
                        optionalContact.ifPresent(contacts::add);
                    });
            return contacts;
        }
            return null;
    }

}


