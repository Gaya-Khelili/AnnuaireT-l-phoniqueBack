package upn_gestionContact.dao;

import upn_gestionContact.entities.Contact;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Dao<T> {
    Optional<T> findById(long id);
    List<T> findAll();
    Optional<T> save(T entity);
    void delete(long id);
    void update(long id,T entity);
    void addContact(long idC,long idG);
    void addContacts(long idG, Set<Contact> contact);
    void removeContactFromGroup(long idC,long idG);
    List<T> search(String criteria);
}
