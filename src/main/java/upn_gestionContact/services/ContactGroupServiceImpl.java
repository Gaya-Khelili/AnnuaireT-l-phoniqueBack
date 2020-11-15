package upn_gestionContact.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.ContactDAOImpl;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;
import upn_gestionContact.entities.Phone;


import java.util.*;


@Service
public class ContactGroupServiceImpl   extends AbstractService<ContactGroup> {

    @Autowired
    private ContactDAOImpl contactDao;

    public ContactGroupServiceImpl() {
        super();
    }

    public void saveFullGroupContact(ContactGroup contactGroup ){
        //à override dans contactGroupService

        getDao().save(contactGroup);

    }
    @Override
    public Set<ContactGroup> findByIdList(long idContact) {

        Optional<Contact> optionalContact = contactDao.findById(idContact);
        if (optionalContact.isPresent()){
            Set<ContactGroup> groups = new HashSet<>();
            optionalContact.get().getContactGroups()
                    .forEach(group -> {
                        Optional<ContactGroup> optionalContactGroup = super.getDao().findById(group.getGroupId());
                        optionalContactGroup.ifPresent(groups::add);
                    });
            return groups;
        }
        return null;

    }


}
