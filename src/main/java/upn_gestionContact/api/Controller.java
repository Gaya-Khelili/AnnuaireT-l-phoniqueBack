package upn_gestionContact.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upn_gestionContact.entities.Contact;

import java.util.List;
import java.util.Optional;

public interface Controller<T>  {

    Optional<T> findById(long id);
    List<T> findAll();
    void save(T entity);
    void delete(long id);
    void update(long id,T entity);
}
