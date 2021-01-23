package upn_gestionContact.api;

import org.springframework.web.bind.annotation.RequestBody;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Controller<T>  {

    Optional<T> findById(long id);
    Optional<T> findByIdContact(long idContact);
    Set<T> findByIdContactList(long idContact);
    //trouver tout les contacts qui appartiennent à un groupe
    Set<T> findByIdGroupContactList(long groupId);
    // pour trouver la liste des groups contacts
    Set<T> findByIdList(long idContact);
    List<T> findAll();
    void save(T entity);
    void delete(long id);
    void update(long id,T entity);
    List<Contact> search(String criteria);
    void fillDatabase();
    void updateContactGroup(ContactGroup updatedContactGroup);
    void deleteContactGroup(long id);
    void addContacts(ContactGroup contactGroup);
    void removeContacts(ContactGroup contactGroup);
}
