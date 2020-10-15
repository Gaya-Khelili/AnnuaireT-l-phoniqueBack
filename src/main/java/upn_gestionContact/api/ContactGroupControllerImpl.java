package upn_gestionContact.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upn_gestionContact.entities.ContactGroup;
import upn_gestionContact.services.ContactGroupServiceImpl;
import upn_gestionContact.services.Services;

@RequestMapping("api/groupContact")
@RestController
public class ContactGroupControllerImpl  extends AbstractController<ContactGroup> {

    @Autowired
    private ContactGroupServiceImpl contactGroupService;

    @Override
    public Services<ContactGroup> getService(){
        return contactGroupService;
    }
}
