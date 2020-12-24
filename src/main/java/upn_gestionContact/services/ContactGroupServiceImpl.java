package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.ContactDAOImpl;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContactGroupServiceImpl   extends AbstractService<ContactGroup> {

    @Autowired
    private ContactDAOImpl contactDao;


    public ContactGroupServiceImpl() {
        super();
    }
    @Override
    public void saveFullGroupContact(ContactGroup contactGroup){


    /** ContactGroup newContactGroup = new ContactGroup();
        // rajout du nom du group dans le new group
        newContactGroup.setGroupName(contactGroup.getGroupName());
       Set<Contact> sContact= new HashSet<>();
        contactGroup.getContacts().forEach(contact -> {
            Optional<Contact> optionalContact = contactDao.findById(contact.getidContact());
            Contact c = optionalContact.get();
            // rajout du new group dans le contact
            c.getContactGroups().add(newContactGroup);
            sContact.add(c);
        });
        // rajout des contacte dans le newGroup
        newContactGroup.getContacts().addAll(sContact);
     /** newContactGroup.getContacts()
                .addAll(contactGroup
                        .getContacts()
                        .stream()
                        .map(c -> {
                            Optional<Contact> cc = contactDao.findById(c.getidContact());
                            cc.get().addContactGroup(newContactGroup);

                            return cc.get();
                        }).collect(Collectors.toList()));**/

      /**  System.out.println("tout les contact"+newContactGroup.getContacts());
        System.out.println("Nom du group"+newContactGroup.getGroupName());
        newContactGroup.getContacts().forEach( contact ->
                System.out.println("le group des contacts"+contact.getContactGroups()));**/
        Optional<Contact> c = contactDao.findById(1);

        contactGroup.addContact(c.get());

        getDao().save(contactGroup);

    }
    //trouver tout les groups qui appartiennent à un contact
    @Override
    public Set<ContactGroup> findByIdList(long idContact) {
        Optional<Contact> optionalContact = contactDao.findById(idContact);
        if (optionalContact.isPresent()) {
            Set<ContactGroup> groups = new HashSet<>();
            optionalContact.get().getContactGroups()
                                .forEach(group -> {
                            Optional<ContactGroup> optionalContactGroup = super.getDao().findById(group.getGroupId());
                                    groups.add(optionalContactGroup.get());
                              });
                           return groups;
        }
        return null;
    }
}
