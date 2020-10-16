package upn_gestionContact.dao;

import org.springframework.stereotype.Repository;
import upn_gestionContact.util.JpaUtil;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Repository
abstract class AbstractDao<T> implements Dao<T> {

    private EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();

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
    public void save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        Optional<T> entity = this.findById(id);
        if (entity.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(entity.get());
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void update(long id,T entity) {
        //à redéfinir pour chaque classe car set sur les champs
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}