package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webshop.entities.Order;
import webshop.entities.OrderItem;
import webshop.repositories.OrderItemRepo;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemRepo orderItemRepo;

    /*
     * POST method: /
     * to save new order-items
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public void saveOrder(@Valid @RequestBody List<OrderItem> orderItems){

        orderItemRepo.saveAll(orderItems);
//        return orderItems;
    }
}
