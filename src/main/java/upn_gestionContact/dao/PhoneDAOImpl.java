package upn_gestionContact.dao;

import org.springframework.stereotype.Repository;
import upn_gestionContact.entities.Phone;


import java.util.Optional;

@Repository
public class PhoneDAOImpl extends AbstractDao<Phone> {

    private PhoneDAOImpl(){
        super();
    }

    @Override
    public void update(long id, Phone updatedPhone){
        super.getEntityManager().getTransaction().begin();

        Optional<Phone> actualPhoneNumberOpt = super.findById(id);

        if (actualPhoneNumberOpt.isPresent()){

            Phone actualPhone = actualPhoneNumberOpt.get();
            actualPhone.setPhoneKind(updatedPhone.getPhoneKind());
            actualPhone.setPhoneNumber(updatedPhone.getPhoneNumber());
            getEntityManager().getTransaction().commit();
        }
    }
}
