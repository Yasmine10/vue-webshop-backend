package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webshop.entities.Address;
import webshop.entities.User;
import webshop.repositories.AddressRepo;
import webshop.repositories.UserRepo;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/address")
public class AddressController {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    /*
     * GET method: /{id}
     * to get a specific address
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Address> getAddressById(@PathVariable(value = "id") Long id) {
        return addressRepo.findById(id);  // if no address => returns null
    }

    /*
     * POST method: /
     * to save a new address
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Address saveAddress(@Valid @RequestBody Address address) throws Exception {

        // check if the user exists
        Optional<User> existingUser = userRepo.findById(address.getUser().getId());
        if(existingUser.isPresent()) {
            // get all addresses from the user
            List<Address> addressesUser = addressRepo.findAllByUser(existingUser.get());
            // check if the address is in the list, if yes => exception, else add address
            if (addressesUser.contains(address)) {
                throw new Exception("Address already exists.");
            } else {
                addressRepo.save(address);
            }
        }
        return address;
    }
}
