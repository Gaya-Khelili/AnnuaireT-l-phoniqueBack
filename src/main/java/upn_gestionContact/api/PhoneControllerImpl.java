package upn_gestionContact.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upn_gestionContact.dao.Dao;
import upn_gestionContact.entities.Phone;
import upn_gestionContact.services.PhoneServiceImpl;
import upn_gestionContact.services.Services;

import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/phone")
@RestController
public class PhoneControllerImpl extends AbstractController<Phone>{

    @Autowired
    private PhoneServiceImpl phoneNumberService;

    @Override
    public Services<Phone> getService(){
        return phoneNumberService;
    }



}
