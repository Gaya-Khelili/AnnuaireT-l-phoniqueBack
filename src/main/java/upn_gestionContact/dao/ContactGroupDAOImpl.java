package upn_gestionContact.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;


import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Repository
public class ContactGroupDAOImpl extends AbstractDao<ContactGroup> {

    @Autowired
    private ContactDAOImpl contactDao;
    @Autowired
    private  ContactGroupDAOImpl contactGroupDAO;
    private ContactGroupDAOImpl(){
        super();
    }

    // pas fini
    // cas pas obligatoire
    // créer un ccontactGroup avec des contacts existant pour éviter les addContact et les removesContacts
 /**  @Override
    public Optional<ContactGroup> save(ContactGroup contactGroup){
            Set<Contact> sContact = new HashSet<Contact>();
            contactGroup.getContacts().forEach(contact -> {
                Optional<Contact> contactOpt = contactDao.findById(contact.getidContact());
                sContact.add(contactOpt.get());
            });

            EntityManager eM = getEntityManager();
            eM.getTransaction().begin();
            eM.persist(contactGroup);
            eM.getTransaction().commit();
            this.addContacts(contactGroup.getGroupId(),sContact);


        return Optional.ofNullable(contactGroup);
    }
    //pas fini
    @Override
    public void addContacts(long idG, Set<Contact> contacts) {
        Optional<ContactGroup> contactGroupOpt = super.findById(idG);
        if (contactGroupOpt.isPresent()) {
            getEntityManager().getTransaction().begin();
            ContactGroup contactGroup = contactGroupOpt.get();
            for (Contact c : contacts) {
                c.addContactGroup(contactGroup);
                getEntityManager().merge(c);

            }
            getEntityManager().merge(contactGroup);
            getEntityManager().getTransaction().commit();

        }
    }**/

    @Override
    public void delete(long id) {

        Optional<ContactGroup> contactGroupOpt = super.findById(id);
        if(contactGroupOpt.isPresent()){
            getEntityManager().getTransaction().begin();
            ContactGroup contactGroup = contactGroupOpt.get();
            for(Contact c : contactGroup.getContacts()){
                Optional<Contact> contactOpt = contactDao.findById(id);
                contactOpt.get().setContactGroups(new HashSet<>());// à modifier pour supprimé proprement
                    contactGroup.setContacts(new HashSet<>());
                    getEntityManager().merge(contactOpt.get());
            }
            getEntityManager().remove(contactGroup);
            getEntityManager().getTransaction().commit();

        }



    }

    @Override
    public void update(long id, ContactGroup updatedContactGroup){
        super.getEntityManager().getTransaction().begin();

        Optional<ContactGroup> actualContactGroupOpt = super.findById(id);
        if (actualContactGroupOpt.isPresent()) {
            ContactGroup actualContactGroup = actualContactGroupOpt.get();
            actualContactGroup.setGroupName(updatedContactGroup.getGroupName());
            //update all contacts in the group
            updatedContactGroup.getContacts().forEach(contact -> {
               Optional<Contact> optionalContact = contactDao.findById(contact.getidContact());
                optionalContact.get().addContactGroup(actualContactGroup);
                super.getEntityManager().merge(optionalContact.get());

            } );

            super.getEntityManager().merge(actualContactGroup);
            super.getEntityManager().getTransaction().commit();


        }
    }





}
