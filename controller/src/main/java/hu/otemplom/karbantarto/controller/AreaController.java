package hu.otemplom.karbantarto.controller;


import hu.otemplom.karbantarto.controller.exceptions.InvalidTokenException;
import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.service.AreaService;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.InvalidAreaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collection;
@RequestMapping("api/v1/area")
@RestController
public class AreaController {
    @Autowired
    public AreaController(AreaService areaService, IUserAuthenticator authenticator) {

        this.areaService = areaService;
        this.authenticator =  authenticator;
    }

    AreaService areaService;
    IUserAuthenticator authenticator;
    @GetMapping
    public Collection<Area> getAllArea(@RequestHeader("authorization") String rawToken) throws hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {
        if(authenticator.userIsAdmin(rawToken)){
            return areaService.getAllArea();
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"",new InvalidTokenException("meow"));

    }
    @PostMapping
    public void addArea(@RequestHeader("authorization") String rawToken, @RequestBody Area area) throws InvalidIdException, InvalidAreaException, AreaAlreadyExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {
        if(authenticator.userIsAdmin(rawToken)){
            areaService.addArea(area);
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"",new InvalidTokenException("meow"));
    }
    @PutMapping
    public void modifyArea(@RequestHeader("authorization") String rawToken,@RequestBody Area area) throws InvalidAreaException, AreaDoesNotExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {
        if(authenticator.userIsAdmin(rawToken)){
            areaService.modifyArea(area);
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"",new InvalidTokenException("meow"));
    }

    @DeleteMapping(path="{id}")
    public void deleteArea(@RequestHeader("authorization") String rawToken,@PathVariable("id") int id) throws AreaDoesNotExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {
        if(authenticator.userIsAdmin(rawToken)){
            areaService.deleteAreaById(id);
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"",new InvalidTokenException("meow"));

    }
    @GetMapping(path="{id}")
    public Area getAreaById(@RequestHeader("authorization") String rawToken,@PathVariable("id")int id) throws AreaDoesNotExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {
        if(authenticator.userIsAdmin(rawToken)){
            return areaService.getAreaById(id);
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"",new InvalidTokenException("meow"));

    }
    @GetMapping(path="userId={userid}")
    public Collection<Area> getAreasByUserId(@PathVariable("userid")int id){
        return areaService.getAreasByUserId(id);
    }
}
