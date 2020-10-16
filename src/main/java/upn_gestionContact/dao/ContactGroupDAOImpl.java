package upn_gestionContact.dao;

import org.springframework.stereotype.Repository;

import upn_gestionContact.entities.ContactGroup;

import java.util.Optional;

@Repository
public class ContactGroupDAOImpl extends AbstractDao<ContactGroup> {

    private ContactGroupDAOImpl(){
        super();
    }

    @Override
    public void update(long id, ContactGroup updatedContactGroup){
        super.getEntityManager().getTransaction().begin();

        Optional<ContactGroup> actualContactGroupOpt = super.findById(id);
        if (actualContactGroupOpt.isPresent()){

            ContactGroup actualContactGroup = actualContactGroupOpt.get();
            actualContactGroup.setGroupName(updatedContactGroup.getGroupName());


            getEntityManager().getTransaction().commit();
        }
    }
}
