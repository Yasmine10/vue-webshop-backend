package webshop.repositories;

import org.springframework.data.repository.CrudRepository;
import webshop.entities.User;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
}
