package upn_gestionContact.dao;

import org.springframework.stereotype.Repository;

import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class ContactGroupDAOImpl extends AbstractDao<ContactGroup> {

    private ContactGroupDAOImpl(){
        super();
    }


    @Override
    public void update(long id, ContactGroup updatedContactGroup){
        super.getEntityManager().getTransaction().begin();

        Optional<ContactGroup> actualContactGroupOpt = super.findById(id);
        if (actualContactGroupOpt.isPresent()){
            ContactGroup actualContactGroup = actualContactGroupOpt.get();

            actualContactGroup.setGroupName(updatedContactGroup.getGroupName());
            Set<ContactGroup> groupSet = new HashSet<ContactGroup>();
            groupSet.add(actualContactGroup);
            updatedContactGroup.getContacts().forEach(contact -> {
                contact.setidContact(actualContactGroup.getContacts().iterator().next().getidContact());
                contact.setContactGroups(groupSet);
            });
            actualContactGroup.setContacts(updatedContactGroup.getContacts());

            getEntityManager().getTransaction().commit();
        }
    }
}
