package webshop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import webshop.entities.Product;
import webshop.entities.Category;
import webshop.entities.Animal;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findAllByAnimal_Name(String animalName);

    @Query("select distinct new Product(p.brand) from Product p where p.animal.name = ?1")
    List<Product> findAllBrandsByAnimalName(String name);

    @Query("select new Product(p.id, p.name, p.brand, p.price, p.imageUrl, p.category, p.animal) from Product as p, Animal as a, Category as c where p.animal = a.id AND a.name = ?1 AND p.brand IN ?2 AND c.name IN ?3 order by ?4")
    List<Product> filterProducts(String animal, List<String> brands, List<String> categories, String order);
}
