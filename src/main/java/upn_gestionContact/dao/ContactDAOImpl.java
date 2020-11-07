package upn_gestionContact.dao;

import org.springframework.stereotype.Repository;
import upn_gestionContact.entities.Contact;
import java.util.Optional;

@Repository
public class ContactDAOImpl extends AbstractDao<Contact> {

   private ContactDAOImpl(){
       super();
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
           getEntityManager().merge(actualContact);
           getEntityManager().getTransaction().commit();
       }
   }
}
