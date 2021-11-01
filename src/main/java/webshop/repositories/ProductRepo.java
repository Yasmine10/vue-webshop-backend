package webshop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import webshop.entities.Product;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findAllByAnimal_Name(String animalName);

    @Query("select distinct new Product(p.brand) from Product p where p.animal.name = ?1")
    List<Product> findAllBrandsByAnimalName(String name);
}
