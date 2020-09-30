package hu.otemplom.karbantarto.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.otemplom.karbantarto.controller.exceptions.InvalidTokenException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
@RequestMapping("api/v1/user")
@RestController
public class UserController {
    private UserService service;
    private IUserAuthenticator authenticator;
    @Autowired
    public UserController(UserService service, IUserAuthenticator authenticator) throws InvalidUsernameException, InvalidIdException, InvalidFullNameException, InvalidRoleException {
        this.service = service;
        this.authenticator = authenticator;
        System.out.print("Valid token: ");
        System.out.println(" "+authenticator.generateTokenFromUser(new User(1,"Pekár Mihály","misike", Role.Admin)));
    }
    @GetMapping
    public Collection<User> getAllUser(@RequestHeader("authorization") String rawToken) throws InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {
        if(authenticator.userIsAdmin(rawToken)){
            return service.getAllUser();
        }else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Meow2",new InvalidTokenException(""));

    }
    @PostMapping
    public int addUser(@RequestBody User user) throws InvalidIdException, DuplicateUserException {

           return  service.addUser(user);



    }
    @PutMapping
    public void modifyUser(@RequestBody User user) throws UserDoesNotExistsException {
        service.modifyUser(user);
    }
    @DeleteMapping(path="{id}")
    public void deleteUserByUserId(@PathVariable("id") int id) throws UserDoesNotExistsException {
        service.deleteUserByUserId(id);
    }
    @GetMapping(path="{id}")
    public User getUserByUserId(@PathVariable("id")int id) throws UserDoesNotExistsException {
        return service.getUserByUserId(id);
    }
    @PostMapping(path = "/login")
    public User loginUser(@RequestBody ObjectNode data){
        User user = service.login(data.get("username").asText(),data.get("password").asText());
        if(user == null)throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Hibás felhasználónév vagy jelszó!");
        System.out.println(user.getId());
        user.setToken(authenticator.generateTokenFromUser(user));
        return user;
    }
}
