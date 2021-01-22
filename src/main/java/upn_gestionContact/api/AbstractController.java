package upn_gestionContact.api;

import org.springframework.web.bind.annotation.*;
import upn_gestionContact.dao.Dao;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;
import upn_gestionContact.services.Services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public abstract class AbstractController<T> implements Controller<T> {
    
     abstract Services<T> getService();

    @Override
    @PostMapping
    public void save(@RequestBody T t) {
        getService().save(t);
    }

    @Override
    @GetMapping
    public List<T> findAll() {
        return getService().findAll();
    }

    @Override
    @GetMapping(path = "{id}")
    public Optional<T> findById(@PathVariable("id") long id) {
        return getService().findById(id);
    }

    @Override
    @GetMapping(path = "addressbycontact/{idContact}")
    public Optional<T> findByIdContact(@PathVariable("idContact") long idContact) {
        return getService().findByIdContact(idContact);
    }

    @Override
    @PostMapping("addContact/{idContact}/{idGroup}")
    public void addContact(@PathVariable("idContact")long idc,@PathVariable("idGroup") long idg) {
        getService().addContact(idc,idg);
    }

    @Override
    @DeleteMapping("removeContact/{idContact}/{idGroup}")
    public void removeContactFromGroup(@PathVariable("idContact")long idc,@PathVariable("idGroup") long idg) {
        getService().removeContactFromGroup(idc,idg);
    }

    @Override
    @GetMapping(path = "phonesbycontact/{idContact}")
    public Set<T> findByIdContactList(@PathVariable("idContact") long idContact) {
        return getService().findByIdContactList(idContact);
    }
    //trouver tout les groups qui appartiennent au contact
    @Override
    @GetMapping(path = "contactGroupbycontact/{idContact}")
    public Set<T> findByIdList(@PathVariable("idContact") long idContact) {
        return getService().findByIdList(idContact);
    }
    //trouver tout les contacts qui appartiennent à un groupe
    @Override
    @GetMapping(path = "contactbycontactGroup/{groupId}")
    public Set<T> findByIdGroupContactList(@PathVariable("groupId")long groupId){
        return getService().findByIdGroupContactList(groupId);
    }

    @Override
    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") long id, @RequestBody T t) {
        getService().update(id,t);
    }

    @Override
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") long id) {
        getService().delete(id);
    }


    @Override
    @GetMapping(path = "search/{criteria}")
    public List<Contact> search(@PathVariable("criteria") String criteria){

        return getService().search(criteria);
    }

    @Override
    @PostMapping(path = "fillDB")
    public void fillDatabase()
    {
        getService().fillDatabase();
    }
    @PutMapping(path = "/contactGroup")
    public void updateContactGroup(@RequestBody ContactGroup updatedContactGroup) {
        getService().updateContactGroup(updatedContactGroup);
    }

    @Override
    @PutMapping(path="/addContacts")
    public void addContacts(@RequestBody ContactGroup contactGroup) {
        getService().addContacts(contactGroup);
    }

    @Override
    @DeleteMapping(path = "contactGroup/{id}")
    public void deleteContactGroup(@PathVariable("id")long id) {
        getService().deleteContactGroup(id);
    }


}
