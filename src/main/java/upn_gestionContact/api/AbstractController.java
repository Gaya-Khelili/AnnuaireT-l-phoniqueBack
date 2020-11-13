package upn_gestionContact.api;

import org.springframework.web.bind.annotation.*;
import upn_gestionContact.services.Services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public abstract class AbstractController<T> implements Controller<T> {
    
     abstract Services<T> getService();

    @Override
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public void save(@RequestBody T t) {
        getService().save(t);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List<T> findAll() {
        return getService().findAll();
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "{id}")
    public Optional<T> findById(@PathVariable("id") long id) {
        return getService().findById(id);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "addressbycontact/{idContact}")
    public Optional<T> findByIdContact(@PathVariable("idContact") long idContact) {
        return getService().findByIdContact(idContact);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "phonesbycontact/{idContact}")
    public Set<T> findByIdContactList(@PathVariable("idContact") long idContact) {
        return getService().findByIdContactList(idContact);
    }
    //trouver un group de contact avec l'id contact
    @Override
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "contactGroupbycontact/{idContact}")
    public Set<T> findByIdList(@PathVariable("idContact") long idContact) {
        return getService().findByIdList(idContact);
    }
    //trouver tout les contacts qui appartiennent à un groupe
    @Override
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "contactbycontactGroup/{idGroupContact}")
    public List<T> findByIdGroupContactList(@PathVariable("groupId")long idGroupContact){
        return getService().findByIdGroupContactList(idGroupContact);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") long id, @RequestBody T t) {
        getService().update(id,t);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") long id) {
        getService().delete(id);
    }
}
