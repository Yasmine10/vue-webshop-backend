package webshop.repositories;

import org.springframework.data.repository.CrudRepository;
import webshop.entities.Animal;

public interface AnimalRepo extends CrudRepository<Animal, Long> {
}
