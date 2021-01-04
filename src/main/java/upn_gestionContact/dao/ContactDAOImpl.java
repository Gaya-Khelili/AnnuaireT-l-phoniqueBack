package upn_gestionContact.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;


import java.util.Optional;



@Repository
public class ContactDAOImpl extends AbstractDao<Contact> {

    @Autowired
    private ContactGroupDAOImpl contactGroupDao;
    private ContactDAOImpl(){
       super();
   }

    @Override
    public void delete(long id) {
        Optional<Contact> contactOpt = super.findById(id);
        getEntityManager().getTransaction().begin();
        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            contact.getContactGroups().forEach(contactGroup ->
                    contactGroup.getContacts().remove(contact)
            );
            getEntityManager().remove(contact);
            getEntityManager().getTransaction().commit();
        }

    }

    @Override
    public void update(long id,Contact updatedContact){
       super.getEntityManager().getTransaction().begin();

       Optional<Contact> actualContactOpt = super.findById(id);
       if (actualContactOpt.isPresent()){
           Contact actualContact = actualContactOpt.get();
           actualContact.setfname(updatedContact.getfname());
           actualContact.setlname(updatedContact.getlname());
           actualContact.setEmail(updatedContact.getEmail());
           updatedContact.getAddress().setIdAddress(actualContact.getAddress().getIdAddress());
           actualContact.setAddress(updatedContact.getAddress());
           // à modifier car ne marche que pour 1 seul téléphone associé
           updatedContact.getPhones().forEach(newPhone -> {
               newPhone.setIdPhone(actualContact.getPhones().iterator().next().getIdPhone());
               newPhone.setContact(actualContact);
           });
           actualContact.setPhones(updatedContact.getPhones());
           // update all groups in the contact

           updatedContact.getContactGroups().forEach(contactGroup -> {
               Optional<ContactGroup> contactGroupOpt = contactGroupDao.findById(contactGroup.getGroupId());
               contactGroupOpt.get().setGroupName(contactGroup.getGroupName());
               //contactGroupOpt.get().getContacts().add(updatedContact);
               actualContact.getContactGroups().add(contactGroupOpt.get());
           } );

           getEntityManager().merge(actualContact);
           getEntityManager().getTransaction().commit();
       }
   }
}
