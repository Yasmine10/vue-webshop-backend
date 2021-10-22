package webshop.repositories;

import org.springframework.data.repository.CrudRepository;
import webshop.entities.Order;

public interface OrderRepo extends CrudRepository<Order, Long> {
}
