package upn_gestionContact.services;

import org.springframework.stereotype.Service;
import upn_gestionContact.entities.Contact;

@Service
public class ContactServiceImpl extends AbstractService<Contact> {

    private ContactServiceImpl() {
        super();
    }

    @Override
    public void saveFullContact(Contact contact){

        contact.getPhones().forEach(phone -> phone.setContact(contact));

        getDao().save(contact);

    }

}
