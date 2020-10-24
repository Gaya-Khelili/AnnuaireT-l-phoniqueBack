package upn_gestionContact.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Services<T> {
    Optional<T> findById(long id);
    Optional<T> findByIdContact(long id);
    Set<T> findByIdContactList(long idContact);
    List<T> findAll();
    void save(T entity);
    void delete(long id);
    void update(long id,T entity);

    void saveFullContact(T contact);
}
