package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.ContactDAOImpl;
import upn_gestionContact.dao.ContactGroupDAOImpl;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;
import java.util.*;


@Service
public class ContactGroupServiceImpl   extends AbstractService<ContactGroup> {

    @Autowired
    private ContactDAOImpl contactDao;
    @Autowired
    private ContactGroupDAOImpl contactGroupDao;

    public ContactGroupServiceImpl() {
        super();
    }

    @Override
    public void addContact(long idC, long idG) {
        getDao().addContact(idC, idG);
    }

    /** @Override
    public void saveFullGroupContact(ContactGroup contactGroup){
                // dans cette méthode je prend tout les contacts que l'utilsiateur veut rajouter pendant
                // la création du group avec un find de la BDD, ensuite je le met dans le contact group
                // la méthode addContact rajoute le contact dans la liste contacts du group
                // et aussi le group dans la liste groups de contact

        getDao().save(contactGroup);
    }**/


    //trouver tout les groups qui appartiennent à un contact
    @Override
    public Set<ContactGroup> findByIdList(long idContact) {
        Optional<Contact> optionalContact = contactDao.findById(idContact);
        Contact contact = optionalContact.get();
        if (optionalContact.isPresent()) {
            Set<ContactGroup> groups = new HashSet<>();
            contact.getContactGroups()
                                .forEach(group -> {
                                    Optional<ContactGroup> optionalContactGroup = getDao().findById(group.getGroupId());
                                    optionalContactGroup.ifPresent(groups::add);
                              });
                           return groups;
        }
        return null;
    }
}
