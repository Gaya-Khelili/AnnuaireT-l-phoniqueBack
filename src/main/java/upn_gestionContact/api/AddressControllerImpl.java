package upn_gestionContact.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upn_gestionContact.entities.Address;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.services.AddressServiceImpl;
import upn_gestionContact.services.Services;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/address")
@RestController

public class AddressControllerImpl extends AbstractController<Address> {

    @Autowired
    private AddressServiceImpl addressService;

    @Override
    public Services<Address> getService(){
        return addressService;
    }

}
