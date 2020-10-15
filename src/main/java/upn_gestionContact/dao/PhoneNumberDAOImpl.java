package upn_gestionContact.dao;

import org.springframework.stereotype.Repository;
import upn_gestionContact.entities.PhoneNumber;


import java.util.Optional;

@Repository
public class PhoneNumberDAOImpl extends AbstractDao<PhoneNumber> {

    private PhoneNumberDAOImpl(){
        super();
    }

    @Override
    public void update(long id, PhoneNumber updatedPhoneNumber){
        super.getEntityManager().getTransaction().begin();

        Optional<PhoneNumber> actualPhoneNumberOpt = super.findById(id);

        if (actualPhoneNumberOpt.isPresent()){

            PhoneNumber actualPhoneNumber = actualPhoneNumberOpt.get();
            actualPhoneNumber.setPhoneKind(updatedPhoneNumber.getPhoneKind());
            actualPhoneNumber.setPhoneNumber(updatedPhoneNumber.getPhoneNumber());
            actualPhoneNumber.setContact(updatedPhoneNumber.getContact());

            getEntityManager().getTransaction().commit();
        }
    }
}
