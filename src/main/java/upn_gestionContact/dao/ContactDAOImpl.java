package upn_gestionContact.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import upn_gestionContact.entities.Contact;
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

        if (contactOpt.isPresent()) {
            getEntityManager().getTransaction().begin();
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
       //    updatedContact.getAddress().setIdAddress(actualContact.getAddress().getIdAddress());
           actualContact.setAddress(updatedContact.getAddress());

           updatedContact.getPhones().forEach(newPhone -> {
               newPhone.setContact(actualContact);
           });
           actualContact.setPhones(updatedContact.getPhones());
           // update all groups in the contact

           updatedContact.getContactGroups().forEach(contactGroup -> {
               //Optional<ContactGroup> contactGroupOpt = contactGroupDao.findById(contactGroup.getGroupId());
               contactGroup.setGroupId(actualContact.getContactGroups().iterator().next().getGroupId());
               contactGroup.getContacts().add(actualContact);
               actualContact.getContactGroups().add(contactGroup);

           } );

           getEntityManager().merge(actualContact);
           getEntityManager().getTransaction().commit();
       }
   }


    public Contact getContactByIdAddress(long idAddress){
        return  (Contact) super.getEntityManager()
                .createQuery("SELECT c FROM Contact c WHERE c.address.idAddress = :idAddress")
                .setParameter("idAddress",idAddress).getSingleResult();

    }
}
