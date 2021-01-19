package upn_gestionContact.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;
import upn_gestionContact.util.JpaUtil;

import javax.persistence.EntityManager;
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
    public void addContact(long idC, long idG) {
        Optional<Contact> optionContact = contactDao.findById(idC);
        Optional<ContactGroup> optionContactGroup =  contactGroupDao.findById(idG);

        optionContact.get().addContactGroup(optionContactGroup.get());
        optionContactGroup.get().addContact(optionContact.get());

        entityManager.getTransaction().begin();
        entityManager.merge( optionContact.get());
        entityManager.merge( optionContactGroup.get());


        entityManager.getTransaction().commit();
    }

    @Override
    public void removeContactFromGroup(long idC, long idG) {
        Optional<Contact> optionContact = contactDao.findById(idC);
        Optional<ContactGroup> optionContactGroup =  contactGroupDao.findById(idG);

        optionContact.get().setContactGroups(new HashSet<>());//à modifier pour supprimé proprement
        optionContactGroup.get().setContacts(new HashSet<>());
        entityManager.getTransaction().begin();
       // entityManager.remove( optionContact.get());
       entityManager.merge(optionContactGroup.get());
        entityManager.merge(optionContact.get());

        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<T> findById(long id) {
        return Optional.ofNullable(entityManager.find(getGenericClass(), id));
    }

    @Override
    public List<T> findAll() {
       return entityManager.createNamedQuery(getGenericClass().getSimpleName()+".findAll").getResultList();
    }
    // rajouter * contacts dans un groups en mm temps
    @Override
    public void addContacts(long idG, Set<Contact> contact) {

    }

    @Override
    public Optional<T> save(T entity)
    {
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

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManager openEntityManager(){
        return JpaUtil.getEntityManagerFactory().createEntityManager();
    }

}