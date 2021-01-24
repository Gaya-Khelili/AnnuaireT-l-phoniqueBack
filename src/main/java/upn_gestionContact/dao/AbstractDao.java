package upn_gestionContact.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;
import upn_gestionContact.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
abstract class AbstractDao<T> implements Dao<T> {

    private EntityManager entityManager = openEntityManager();
    @Autowired
    private ContactDAOImpl contactDao;
    @Autowired
    private ContactGroupDAOImpl contactGroupDao;
    private Class<T> getGenericClass() { //retourne la classe passé à la place du T
        try {
            Type mySuperclass = getClass().getGenericSuperclass();
            Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
            String className = tType.toString().split(" ")[1];
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Optional<T> findById(long id) {
        return Optional.ofNullable(entityManager.find(getGenericClass(), id));
    }

    @Override
    public List<T> findAll() {
       return entityManager.createNamedQuery(getGenericClass().getSimpleName()+".findAll").getResultList();
    }



    @Override
    public Optional<T> save(T entity)
    {
        getGenericClass();

        entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();

        return Optional.ofNullable(entity);
    }
    @Override
    public void delete(long id) {
        // redéfinir dans contactDAOImpl et groupContactDAOImpl
    }


    @Override
    public void update(long id,T entity) {
        //à redéfinir pour chaque classe car set sur les champs
    }



    @Override
    public  List<T> search(String criteria){
        return entityManager.createNamedQuery(getGenericClass().getSimpleName()+".search")
                .setParameter("criteria","%" + criteria + "%").getResultList();
    }

    @Override
    public void updateContactGroup( ContactGroup updatedContactGroup) {

        entityManager.getTransaction().begin();

        //update all contacts in the group
        updatedContactGroup.getContacts().forEach(contact -> {
            contact.addContactGroup(updatedContactGroup);
            entityManager.merge(contact);

        });

        entityManager.merge(updatedContactGroup);
        entityManager.getTransaction().commit();


    }
    @Override
    public void addContacts(ContactGroup cg) {

        entityManager.getTransaction().begin();

        cg.getContacts().forEach(contact -> {
            contact.addContactGroup(cg);
            entityManager.merge(contact);
        });

        entityManager.merge(cg);
        entityManager.getTransaction().commit();
    }


    @Override
    public void deleteContactGroup(long id) {

        Optional<ContactGroup>  cg = contactGroupDao.findById(id);

        if(cg.isPresent()){

            ContactGroup group = cg.get();
            entityManager.getTransaction().begin();


            group.getContacts().forEach( contact -> {
                    Set<ContactGroup> cgH = new HashSet<ContactGroup>();
                   contact.getContactGroups().forEach(contactGroup -> {
                       if(contactGroup.getGroupId()!=group.getGroupId()){
                           cgH.add(contactGroup);
                       }
                   });
                   contact.setContactGroups(cgH);

                   entityManager.merge(contact);
            });

            group.setContacts(new HashSet<>());
            entityManager.merge(group);
            entityManager.remove(group);
            entityManager.getTransaction().commit();
        }


        }

    @Override
    public void deleteContactFromGroup(ContactGroup cg) {
      entityManager.getTransaction().begin();

          //  Optional<ContactGroup> ocg = contactGroupDao.findById(cg.getGroupId());
           // ContactGroup contactg = ocg.get();
            //enlever le contact du group
        /**    Set<Contact> cH = new HashSet<Contact>();
        contactg.getContacts().forEach(contact -> {
                cg.getContacts().forEach(contact1 -> {
                    if(contact.getidContact()== contact1.getidContact()){
                        cH.add(contact);
                    }
                });
            });
            // garder les autres group lié au contact
        contactg.setContacts(cH);**/
        cg.getContacts().forEach(contact -> {
                Set<ContactGroup> cgH = new HashSet<ContactGroup>();
                        Optional<Contact> oC = contactDao.findById(contact.getidContact());
                        Contact c  = oC.get();
                  // garder les autre groups lié au contacts
                        c.getContactGroups().forEach(contactGroup -> {
                            if(contactGroup.getGroupId() !=  cg.getGroupId()){
                                cgH.add(contactGroup);
                            }
                      });

                c.setContactGroups(cgH);

               // contactg.getContacts().remove(c);

            //cH.add(oC.get());
            entityManager.merge(c);
        });

        entityManager.merge(cg);

        entityManager.getTransaction().commit();

        /**
        entityManager.getTransaction().begin();
        entityManager.merge(cg);
        cg.getContacts().forEach(contact -> {
            contact.getContactGroups().remove(cg);
            entityManager.merge(contact);
        });


        entityManager.getTransaction().commit();**/
    }

    public EntityManager getEntityManager() {
        return entityManager;
     }

    public EntityManager openEntityManager(){
        return JpaUtil.getEntityManagerFactory().createEntityManager();
    }


}