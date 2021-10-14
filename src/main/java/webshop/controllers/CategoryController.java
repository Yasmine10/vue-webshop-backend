package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
        ArrayList<Category> categoriesList = new ArrayList<>();
        categoryRepo.findAll().forEach(categoriesList::add);
        return categoriesList;
    }
}
