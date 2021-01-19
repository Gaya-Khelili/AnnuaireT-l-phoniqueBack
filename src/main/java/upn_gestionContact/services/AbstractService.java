package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import upn_gestionContact.dao.Dao;
import upn_gestionContact.entities.Contact;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
abstract class AbstractService<T> implements Services<T> {

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
    @Override
    public void saveFullGroupContact(T contactGroup ){
        //à override dans contactGroupService
    }

    public Dao<T> getDao() {
        return dao;
    }

    public void setDao(Dao<T> dao) {
        this.dao = dao;
    }

    public  List<T> search(String criteria){
        return dao.search(criteria);
    }
}
