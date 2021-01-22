package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import upn_gestionContact.dao.ContactDAOImpl;
import upn_gestionContact.dao.ContactGroupDAOImpl;
import upn_gestionContact.dao.Dao;
import upn_gestionContact.entities.Address;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.Phone;
import upn_gestionContact.entities.ContactGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
abstract class AbstractService<T> implements Services<T> {
    @Autowired
    private ContactDAOImpl contactDao;
    @Autowired
    private ContactGroupDAOImpl contactGroupDao;
    @Autowired
    private Dao<T> dao;

    @Override
    public Optional<T> findById(long id) {
        return dao.findById(id);
    }

    @Override
    public Optional<T> findByIdContact(long id){
        //à redéfinir
        return Optional.empty();
    }

    @Override
    public Set<T> findByIdContactList(long idContact){
        //à redéfinir
        return null;
    }

    @Override
    public void addContact(long idC, long idG) {
        dao.addContact(idC,idG);
    }



    @Override
    public void removeContactFromGroup(long idC, long idG) {
        dao.removeContactFromGroup( idC, idG);
    }

    @Override
    public Set<T> findByIdList(long idContact){
        //à redéfinir
        return null;
    }
    @Override
    //trouver tout les contacts qui appartiennent à un groupe
    public Set<T> findByIdGroupContactList(long groupId){
        //à redéfinir
        return null;
    }
    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(T entity) {
        dao.save(entity);
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public void update(long id, T entity) {
        dao.update(id,entity);
    }

    @Override
    public void saveFullContact(T contact){
        //à override dans contactService
    }


    public Dao<T> getDao() {
        return dao;
    }

    public void setDao(Dao<T> dao) {
        this.dao = dao;
    }

    @Override
    public List<Contact> search(String criteria) {
        return null; // redef pour address contact et phone
    }

    @Override
    public void fillDatabase(){

    public void updateContactGroup( ContactGroup updatedContactGroup) {
        Optional<ContactGroup>  cg = contactGroupDao.findById(updatedContactGroup.getGroupId());
        cg.get().setGroupName(updatedContactGroup.getGroupName());
        updatedContactGroup.getContacts().forEach(contact -> {
            Optional<Contact>  c = contactDao.findById(contact.getidContact());
            cg.get().getContacts().add(c.get());
        });

        this.dao.updateContactGroup(cg.get());

    }
    @Override
    public void addContacts(ContactGroup entity) {
        Optional<ContactGroup>  cg = contactGroupDao.findById(entity.getGroupId());

        entity.getContacts().forEach(contact -> {
            Optional<Contact>  c = contactDao.findById(contact.getidContact());
            cg.get().getContacts().add(c.get());
        });
        this.dao.addContacts(cg.get());
    }

    @Override
    public void deleteContactGroup(long id) {


        this.dao.deleteContactGroup(id);
    }
}
