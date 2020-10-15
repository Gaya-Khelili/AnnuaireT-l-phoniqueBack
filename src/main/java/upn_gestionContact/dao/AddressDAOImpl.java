package upn_gestionContact.dao;

import org.springframework.stereotype.Repository;
import upn_gestionContact.entities.Address;

import java.util.Optional;


@Repository
public class AddressDAOImpl  extends AbstractDao<Address> {

    private AddressDAOImpl(){
        super();
    }

    @Override
    public void update(long id, Address updatedAddress){
        super.getEntityManager().getTransaction().begin();

        Optional<Address> actualAddressOpt = super.findById(id);
        if (actualAddressOpt.isPresent()){

            Address actualAddress = actualAddressOpt.get();
            actualAddress.setCity(updatedAddress.getCity());
            actualAddress.setCountry(updatedAddress.getCountry());
            actualAddress.setStreet(updatedAddress.getStreet());
            actualAddress.setZip(updatedAddress.getZip());
            actualAddress.setContact(updatedAddress.getContact());
            getEntityManager().getTransaction().commit();
        }
    }
}
