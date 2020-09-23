package hu.otemplom.karbantarto.controller;


import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.otemplom.karbantarto.controller.exceptions.InvalidTokenException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidPasswordException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.Exceptions.Work.*;
import hu.otemplom.karbantarto.model.Work;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;
import hu.otemplom.karbantarto.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Date;

@RequestMapping("api/v1/work")
@RestController
public class WorkController {
    @Autowired
    public WorkController(WorkService workService, IUserAuthenticator authenticator) {
        this.authenticator = authenticator;
        this.workService = workService;
    }

    WorkService workService;
    IUserAuthenticator authenticator;
    @GetMapping(path="/getnewworks")
    public Collection<Work> getAllNewWork(@RequestHeader("authorization") String rawToken) throws hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidUsernameException, InvalidFullNameException, InvalidRoleException, InvalidTokenException {
        if(authenticator.getUserFromRawToken(rawToken) == null)  throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Meow2",new InvalidTokenException("Meow"));
        return workService.getNewWorks();
    }
    @PostMapping
    public void addWork(@RequestHeader("authorization")String rawToken, @RequestBody Work work) throws InvalidIdException, InvalidCreationDateException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException, InvalidOwnerException {
       try{
           if(authenticator.userIsUserOrAdmin(rawToken)){
               work.setOwner(authenticator.getUserFromRawToken(rawToken));
               work.setCreatedDate(new Date());
               workService.addWork(work);
           }
       }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Meow2",e);
        }

    }
    @PutMapping
    public void modifyWork(@RequestBody Work work) throws WorkDoesNotExistsException {
        workService.modifyWork(work);
    }
    @DeleteMapping(path = "/{id}")
    public void deleteWork(@PathVariable("id")int id ) throws WorkDoesNotExistsException {
        workService.deleteWorkById(id);
    }
    @GetMapping(path = "/getstartedworks")
    public Collection<Work> getWorksInProgress(){
        return workService.getStartedWorks();
    }
    @GetMapping(path = "/getneedtocheckworks")
    public Collection<Work> getNeedToCheckWorks(){
        return workService.getNeedToCheckWorks();
    }
    @GetMapping(path = "/getdoneworks")
    public Collection<Work> getThisMonthsDoneWorks(){
        return workService.getThisMonthDoneWorks();
    }
    @GetMapping(path="{id}")
    public Work getWorkById(@RequestHeader("authorization")String rawToken,@PathVariable("id")int id) {
        try{
            if(authenticator.userIsUserOrAdmin(rawToken)){
                Work work = workService.getWorkById(id);
                work.getOwner().setPassword("Kiscica05");
                return work;
            }
            else{
                throw new Exception("");
            }
        }
        catch (WorkDoesNotExistsException|InvalidPasswordException|InvalidFullNameException|InvalidTokenException|InvalidRoleException|hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException|InvalidUsernameException|Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Meow2",e);
        }

    }
    @GetMapping(path = "/getuserworks/{id}")
    public Collection<Work> getUsersWorksByUserId(@PathVariable int id){
        return workService.getWorksByUserId(id);
    }
    @PostMapping(path="/settostarted")
    public void setWorkToStarted(@RequestBody ObjectNode workAndUserId) throws UserDoesNotExistsException, InvalidWorkerException, WorkDoesNotExistsException {
        workService.setWorkStarted(workAndUserId.get("workId").asInt(),workAndUserId.get("userId").asInt());
    }
    @PostMapping(path = "/settoproceed")
    public void setWorkToProceed(@RequestBody ObjectNode workId) throws InvalidProceedDateException, WorkDoesNotExistsException {
        workService.setWorkProcceed(workId.get("workId").asInt());
    }
    @PostMapping(path="/settodone")
    public void setWorkToDone(@RequestBody ObjectNode workId) throws InvalidDoneDateException, WorkDoesNotExistsException {
        workService.setWorkDone(workId.get("workId").asInt());
    }
    @PostMapping(path = "rejectwork")
    public void setWorkToRejected(@RequestBody ObjectNode workId) throws WorkDoesNotExistsException, InvalidIdException, InvalidOwnerException, InvalidTitleException, InvalidDescriptionException, InvalidCreationDateException {
        workService.setWorkToRejected(workId.get("workId").asInt());
    }
}
