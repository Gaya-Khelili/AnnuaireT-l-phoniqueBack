package upn_gestionContact.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upn_gestionContact.entities.Address;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.PhoneNumber;
import upn_gestionContact.services.ContactServiceImpl;
import upn_gestionContact.services.Services;

@RequestMapping("api/contact")
@RestController
public class ContactControllerImpl extends AbstractController<Contact> {

    @Autowired
    private ContactServiceImpl contactService;

    @Override
    public Services<Contact> getService(){
        return contactService;
    }

    @PostMapping(path="/fullcontact")
    public void saveFullContact(@RequestBody Contact contact,
                                 Address address,
                                 PhoneNumber phone){
        getService().saveFullContact(contact,address,phone);
    }
}
