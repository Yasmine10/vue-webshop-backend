package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import webshop.entities.Animal;
import webshop.repositories.AnimalRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/animal")
public class AnimalController {

    @Autowired
    private AnimalRepo animalRepo;

    /*
     * GET method:
     * to get all animals
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Animal> getAllAnimals() {
        ArrayList<Animal> animalList = new ArrayList<>();
        animalRepo.findAll().forEach(animalList::add);
        return animalList;
    }
}
