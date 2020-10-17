package upn_gestionContact.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upn_gestionContact.entities.PhoneNumber;
import upn_gestionContact.services.PhoneNumberServiceImpl;
import upn_gestionContact.services.Services;

@RequestMapping("api/phoneNumber")
@RestController
public class PhoneNumberControllerImpl extends AbstractController<PhoneNumber>{

    @Autowired
    private PhoneNumberServiceImpl phoneNumberService;

    @Override
    public Services<PhoneNumber> getService(){
        return phoneNumberService;
    }
}
