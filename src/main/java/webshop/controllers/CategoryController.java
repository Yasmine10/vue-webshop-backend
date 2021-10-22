package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webshop.entities.Category;
import webshop.repositories.CategoryRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/category")
public class CategoryController {

    @Autowired
    private CategoryRepo categoryRepo;

    /*
     * GET method:
     * to get all categories
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAllCategories() {
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryRepo.findAll().forEach(categoryList::add);
        return categoryList;
    }

    /*
     * GET method:
     * to get all categories for a specific animal
     */
    @RequestMapping(value = "/animal={name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAllCategoriesByAnimalName(@PathVariable(value = "name") String name) {
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryRepo.findAllByAnimal_Name(name).forEach(categoryList::add);
        return categoryList;
    }
}
