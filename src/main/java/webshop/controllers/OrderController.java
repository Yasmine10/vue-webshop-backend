package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webshop.entities.Order;
import webshop.repositories.OrderRepo;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    /*
     * POST method: /
     * to save a new order
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Long saveOrder(@Valid @RequestBody Order order){

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        order.setOrderDate(localDate);

        orderRepo.save(order);

        return order.getId();
    }
}
