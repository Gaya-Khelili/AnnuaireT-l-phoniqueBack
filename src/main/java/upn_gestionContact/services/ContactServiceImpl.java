package upn_gestionContact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import upn_gestionContact.dao.ContactGroupDAOImpl;
import upn_gestionContact.entities.Address;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;
import upn_gestionContact.entities.Phone;

import java.util.*;

@Service
public class ContactServiceImpl extends AbstractService<Contact> {

    private ContactServiceImpl() {
        super();
    }
    @Autowired
    private ContactGroupDAOImpl contactGroupDao;
    @Override
    public void saveFullContact(Contact contact){

        contact.getPhones().forEach(phone -> phone.setContact(contact));

        getDao().save(contact);
    }


    //trouver tout les contacts qui appartiennent à un groupe
    @Override
  public Set<Contact> findByIdGroupContactList(long groupId){
        Optional<ContactGroup> optionalGroup = contactGroupDao.findById(groupId);
        ContactGroup cg = optionalGroup.get();
        if (optionalGroup.isPresent()) {
            Set<Contact> contacts = new HashSet<>();
            cg.getContacts()
                    .forEach(contact -> {
                        Optional<Contact> optionalContact = super.getDao().findById(contact.getidContact());
                        optionalContact.ifPresent(contacts::add);
                    });
            return contacts;
        }
            return null;
    }

    @Override
    public  List<Contact> search(String criteria){
        return super.getDao().search(criteria);
    }

    @Override
    public void fillDatabase(){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Contact contact = (Contact) context.getBean("contact1");
      //  Address address = (Address) context.getBean("address1");

     //   Set<Phone> phones = new HashSet<>();
        Set<Contact> contacts = new HashSet<>();
        Set<ContactGroup> groups = new HashSet<>();

    /*    Phone phone1 = (Phone) context.getBean("phone1");
        Phone phone2 = (Phone) context.getBean("phone2");
        phones.add(phone1);
        phones.add(phone2);

        contact.setAddress(address);
        contact.setPhones(phones);
        phones.forEach(phone -> phone.setContact(contact));
*/
        ContactGroup group = (ContactGroup) context.getBean("group1");
        groups.add(group);
        contacts.add(contact);
        contact.setContactGroups(groups);
        group.setContacts(contacts);

        super.getDao().save(contact);
    }


}


