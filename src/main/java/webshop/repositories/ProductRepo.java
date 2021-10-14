package webshop.repositories;

import org.springframework.data.repository.CrudRepository;
import webshop.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
}
