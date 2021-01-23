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
