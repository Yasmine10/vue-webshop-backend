package webshop.controllers;

import jakarta.ws.rs.QueryParam;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webshop.entities.Product;
import webshop.entities.ProductPage;
import webshop.entities.ProductSearchCriteria;
import webshop.repositories.ProductRepo;
import webshop.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/product")
public class ProductController {

//    public final Logger logger = LoggerFactory.getLogger();

    @Autowired
    private ProductRepo productRepo;

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

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

    /*
     * GET method: /animal={name}/brands
     * to get all available brands for a specific animal
     */
    @RequestMapping(value = "/animal={name}/brands", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllBrandsByAnimalName(@PathVariable(value = "name") String name) {
        ArrayList<Product> productList = new ArrayList<>();
        productRepo.findAllBrandsByAnimalName(name).forEach(productList::add);
        return productList;
    }

    /*
     * GET method: /{id}
     * to get a specific product by id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Product> getProductById(@PathVariable(value = "id") Long id) {
        return productRepo.findById(id);
    }

    /*
     * GET method: /filter?brands={brands}&categories={categories}
     * to filter products
     */
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<Product>> filterProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {

        return new ResponseEntity<>(productService.findAllWithFilters(productPage, productSearchCriteria), HttpStatus.OK);

    }

}
