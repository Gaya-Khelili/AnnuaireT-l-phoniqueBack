package upn_gestionContact.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upn_gestionContact.entities.Address;
import upn_gestionContact.services.AddressServiceImpl;
import upn_gestionContact.services.Services;

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
