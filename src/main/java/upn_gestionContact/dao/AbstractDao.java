package upn_gestionContact.dao;

import org.springframework.stereotype.Repository;
import upn_gestionContact.entities.ContactGroup;
import upn_gestionContact.util.JpaUtil;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Repository
abstract class AbstractDao<T> implements Dao<T> {

    private EntityManager entityManager = openEntityManager();

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

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManager openEntityManager(){
        return JpaUtil.getEntityManagerFactory().createEntityManager();
    }

}