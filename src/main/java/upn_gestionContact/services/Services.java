package upn_gestionContact.services;

import upn_gestionContact.entities.Address;
import upn_gestionContact.entities.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface Services<T> {
    Optional<T> findById(long id);
    List<T> findAll();
    void save(T entity);
    void delete(long id);
    void update(long id,T entity);

    void saveFullContact(T contact, Address address, PhoneNumber phoneNumber);
}
