package webshop.repositories;

import org.springframework.data.repository.CrudRepository;
import webshop.entities.Address;
import webshop.entities.User;

import java.util.List;

public interface AddressRepo extends CrudRepository<Address, Long> {

    List<Address> findAllByUser(User user);
}
