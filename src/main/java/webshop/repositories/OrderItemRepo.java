package webshop.repositories;

import org.springframework.data.repository.CrudRepository;
import webshop.entities.OrderItem;

import java.util.List;

public interface OrderItemRepo extends CrudRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrder_Id(Long id);
}
