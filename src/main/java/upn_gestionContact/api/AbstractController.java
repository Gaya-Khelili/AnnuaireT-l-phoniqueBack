package upn_gestionContact.api;

import org.springframework.web.bind.annotation.*;
import upn_gestionContact.services.Services;

import java.util.List;
import java.util.Optional;

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
    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") long id, @RequestBody T t) {
        getService().update(id,t);
    }

    @Override
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") long id) {
        getService().delete(id);
    }
}
