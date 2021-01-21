package upn_gestionContact.dao;
import upn_gestionContact.entities.ContactGroup;

import java.util.List;
import java.util.Optional;


public interface Dao<T> {
    Optional<T> findById(long id);
    List<T> findAll();
    Optional<T> save(T entity);
    void delete(long id);
    void update(long id,T entity);
    void addContact(long idC,long idG);
    void addContacts(ContactGroup entity);
    void removeContactFromGroup(long idC,long idG);
    void updateContactGroup( ContactGroup updatedContactGroup);
    void deleteContactGroup(long id);
    List<T> search(String criteria);
}
