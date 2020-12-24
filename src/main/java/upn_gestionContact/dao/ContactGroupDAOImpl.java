package upn_gestionContact.dao;

import org.springframework.stereotype.Repository;

import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.ContactGroup;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ContactGroupDAOImpl extends AbstractDao<ContactGroup> {

    private ContactGroupDAOImpl(){
        super();
    }
    @Override
    public Optional<ContactGroup> save(ContactGroup cg)
    {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(cg);

        getEntityManager().getTransaction().commit();

        System.out.println("tout les contacte"+cg.getContacts());
        System.out.println("Nom du group"+cg.getGroupName());
        cg.getContacts().forEach( contact ->
                System.out.println("le group des contacts"+contact.getContactGroups()));
        return Optional.ofNullable(cg);
    }
    @Override
    public void delete(long id) {
        Optional<ContactGroup> contactGroupOpt = super.findById(id);
        if(contactGroupOpt.isPresent()){
            getEntityManager().getTransaction().begin();
            ContactGroup contactGroup = contactGroupOpt.get();
            contactGroup.getContacts().forEach(contact -> contact.getContactGroups().remove(contactGroup));
            getEntityManager().remove(contactGroup);
            getEntityManager().getTransaction().commit();
        }


    }

    @Override
    public void update(long id, ContactGroup updatedContactGroup){
        super.getEntityManager().getTransaction().begin();

        Optional<ContactGroup> actualContactGroupOpt = super.findById(id);
       /** if (actualContactGroupOpt.isPresent()){
            ContactGroup actualContactGroup = actualContactGroupOpt.get();

            actualContactGroup.setGroupName(updatedContactGroup.getGroupName());
            updatedContactGroup.getContacts().forEach(contact -> {
               contact.setidContact(actualContactGroup.getContacts().iterator().next().getidContact());
                contact.getContactGroups().add(actualContactGroup);

            });**/
        if (actualContactGroupOpt.isPresent()) {
            ContactGroup actualContactGroup = actualContactGroupOpt.get();
            actualContactGroup.setContacts(updatedContactGroup.getContacts());

            actualContactGroup.getContacts()
                    .addAll(updatedContactGroup
                            .getContacts()
                            .stream()
                            .map(c -> {
                                c.getContactGroups().add(updatedContactGroup);

                                return c;
                            }).collect(Collectors.toList()));
            getEntityManager().merge(actualContactGroup);
            getEntityManager().getTransaction().commit();


        }
    }
}
