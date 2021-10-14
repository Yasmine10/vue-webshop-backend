package webshop.repositories;

import org.springframework.data.repository.CrudRepository;
import webshop.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, Long> {
}
