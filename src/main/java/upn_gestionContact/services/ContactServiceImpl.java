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
        // pas le choix pour contourner le Set qu'il y a dans  setContact ( je ne peux pas le changer dans contact
        // car ça ne marcherait pas pour faire le relation many to many entre contact et group
        // en cour de modification
        Set<Contact> contacts = new HashSet<>();
        contacts.add(contact);
        contact.getContactGroups().forEach(group -> group.setContacts(contacts));

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
            System.out.println("Contacts "+contacts.isEmpty());
            return contacts;
        }
            return null;
    }

}


