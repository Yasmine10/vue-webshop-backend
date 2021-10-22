package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webshop.entities.User;
import webshop.repositories.UserRepo;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    /*
     * POST method: /
     * to save a new user
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public User saveUser(@Valid @RequestBody User user) throws Exception {

        // check if the user already exists, email must be unique
        Optional<User> newUser = userRepo.findUserByEmail(user.getEmail());
        if(newUser.isPresent()) {
            throw new Exception("There already is an account for this email.");
        } else {
            userRepo.save(user);
        }

        return user;
    }
}
