package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webshop.entities.Product;
import webshop.repositories.ProductRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    /*
     * GET method:
     * to get all products
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        productRepo.findAll().forEach(productList::add);
        return productList;
    }

    /*
     * GET method: /animal={name}
     * to get all the products for a specific animal
     */
    @RequestMapping(value = "/animal={name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProductsByAnimalName(@PathVariable(value = "name") String animalName) {
        ArrayList<Product> productList = new ArrayList<>();
        productRepo.findAllByAnimal_Name(animalName).forEach(productList::add);
        return productList;
    }
}
