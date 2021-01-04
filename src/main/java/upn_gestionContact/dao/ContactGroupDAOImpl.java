package upn_gestionContact.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class ContactGroupDAOImpl extends AbstractDao<ContactGroup> {

    private ContactGroupDAOImpl(){
        super();
    }
    @Autowired
    private ContactDAOImpl contactDao;

    @Override
    public Optional<ContactGroup> save(ContactGroup cg){

        getEntityManager().getTransaction().begin();
        System.out.println("avant persist"+cg.toString());
        cg.getContacts().forEach(contact -> {System.out.println("avant persist"+contact.toString());

        });

        getEntityManager().persist(cg);

        System.out.println("après persist"+cg.toString());
        cg.getContacts().forEach(contact -> {System.out.println("après persist"+contact.toString());
                    getEntityManager().merge(contact);
        });


        getEntityManager().getTransaction().commit();


        return Optional.ofNullable(cg);
    }

    @Override
    public void delete(long id) {
        Optional<ContactGroup> contactGroupOpt = super.findById(id);
        if(contactGroupOpt.isPresent()){
            getEntityManager().getTransaction().begin();
            ContactGroup contactGroup = contactGroupOpt.get();
            contactGroup.getContacts().forEach(contact -> contact.getContactGroups().remove(contactGroup));
            getEntityManager().remove(contactGroup);
            getEntityManager().getTransaction().commit();
        }
    }

    @Override
    public void update(long id, ContactGroup updatedContactGroup){
        getEntityManager().getTransaction().begin();

        Optional<ContactGroup> actualContactGroupOpt = super.findById(id);
        if (actualContactGroupOpt.isPresent()) {
            ContactGroup actualContactGroup = actualContactGroupOpt.get();
            actualContactGroup.setGroupName(updatedContactGroup.getGroupName());
            //update all contacts in the group
            updatedContactGroup.getContacts().forEach(contact -> {
                Optional<Contact> contactOpt = contactDao.findById(contact.getidContact());

                actualContactGroup.addContact(contactOpt.get());
                contactOpt.get().getContactGroups().add(actualContactGroup);
                getEntityManager().merge(contactOpt.get());

            } );

            getEntityManager().merge(actualContactGroup);
            getEntityManager().getTransaction().commit();


        }
    }





}
