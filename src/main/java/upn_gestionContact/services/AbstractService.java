package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import upn_gestionContact.dao.Dao;
import upn_gestionContact.entities.Address;
import upn_gestionContact.entities.PhoneNumber;

import java.util.List;
import java.util.Optional;

@Service
abstract class AbstractService<T> implements Services<T> {

    @Autowired
    private Dao<T> dao;

    @Override
    public Optional<T> findById(long id) {
        return dao.findById(id);
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
    public void saveFullContact(T contact, Address address, PhoneNumber phoneNumber){
        //à override dans contactService
    }

    public Dao<T> getDao() {
        return dao;
    }

    public void setDao(Dao<T> dao) {
        this.dao = dao;
    }
}
