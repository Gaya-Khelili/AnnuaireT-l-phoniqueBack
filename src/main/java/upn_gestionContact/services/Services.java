package upn_gestionContact.services;

import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Services<T> {
    Optional<T> findById(long id);
    Optional<T> findByIdContact(long id);
    Set<T> findByIdContactList(long idContact);
    List<T> findAll();
    // pour trouver la liste des groups contacts
    Set<T> findByIdList(long idContact);
    //trouver tout les contacts qui appartiennent à un groupe
    Set<T> findByIdGroupContactList(long idGroupContact);
    void save(T entity);
    void delete(long id);
    void update(long id,T entity);
    void saveFullContact(T contact);
    void addContact(long idC,long idG);
    void addContacts(ContactGroup entity);
    void removeContactFromGroup(long idC,long idG);
    List<Contact> search(String criteria);

    void fillDatabase();
    void updateContactGroup(ContactGroup updatedContactGroup);
    void deleteContactGroup(long id);
}
