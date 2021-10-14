package webshop.repositories;

import org.springframework.data.repository.CrudRepository;
import webshop.entities.Product;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findAllByAnimal_Name(String animalName);
}
