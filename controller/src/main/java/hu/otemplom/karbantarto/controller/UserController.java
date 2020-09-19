package hu.otemplom.karbantarto.controller;

import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RequestMapping("api/v1/user")
@RestController
public class UserController {
    private UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }
    @GetMapping
    public Collection<User> getAllUser(){
        return service.getAllUser();
    }
    @PostMapping
    public void addUser(@RequestBody User user) throws InvalidIdException, DuplicateUserException {
        service.AddUser(user);
    }
    @PutMapping
    public void modifyUser(@RequestBody User user) throws UserDoesNotExistsException {
        service.ModifyUser(user);
    }
    @DeleteMapping(path="{id}")
    public void deleteUserByUserId(@PathVariable("id") int id) throws UserDoesNotExistsException {
        service.DeleteUserByUserId(id);
    }
    @GetMapping(path="{id}")
    public User getUserByUserId(@PathVariable("id")int id) throws UserDoesNotExistsException {
        return service.getUserByUserId(id);
    }
}
