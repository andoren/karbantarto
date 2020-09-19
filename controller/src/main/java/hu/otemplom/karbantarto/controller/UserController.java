package hu.otemplom.karbantarto.controller;

import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
