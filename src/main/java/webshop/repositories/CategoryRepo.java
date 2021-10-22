package webshop.repositories;

import org.springframework.data.repository.CrudRepository;
import webshop.entities.Category;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Long> {

    List<Category> findAllByAnimal_Name(String animalName);
}
