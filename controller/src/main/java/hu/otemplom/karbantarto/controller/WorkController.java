package hu.otemplom.karbantarto.controller;


import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.otemplom.karbantarto.controller.email.EmailService;
import hu.otemplom.karbantarto.controller.exceptions.InvalidTokenException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidPasswordException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.Exceptions.Work.*;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.model.Work;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;
import hu.otemplom.karbantarto.service.UserService;
import hu.otemplom.karbantarto.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RequestMapping("api/v1/work")
@RestController
public class WorkController {
    @Autowired
    public WorkController(WorkService workService, UserService userService,IUserAuthenticator authenticator, EmailService emailService) {
        this.authenticator = authenticator;
        this.workService = workService;
        this.emailService = emailService;
        this.userService = userService;
    }

    private UserService userService;
    private EmailService emailService;
    @Autowired
            private SimpMessagingTemplate template;
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
               emailService.newWorkWasAddedNotifyJanitors(userService.getJanitors().stream().map(User::getEmail)
                       .collect(Collectors.toList()),work);
               template.convertAndSend("/work/reload","Új munkát adtak hozzá a listához.");

           }
       }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Meow2",e);
        }

    }
    @PutMapping

    public void modifyWork(@RequestHeader("authorization")String rawToken,@RequestBody Work work) throws WorkDoesNotExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {
        if(authenticator.userIsUserOrAdmin(rawToken)){
            User user = authenticator.getUserFromRawToken(rawToken);
            if(user.getId() == work.getOwner().getId() || user.getId() == work.getArea().getBoss().getId() ){
                workService.modifyWork(work);
                template.convertAndSend("/work/reload","Egy munkát módosítottak");
            }else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Meow2");
        }else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Meow2");
    }
    @DeleteMapping(path = "/{id}")

    public void deleteWork(@RequestHeader("authorization")String rawToken,@PathVariable("id")int id ) throws WorkDoesNotExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {
        if(authenticator.userIsUserOrAdmin(rawToken)){
            User user = authenticator.getUserFromRawToken(rawToken);
            Work work = workService.getWorkById(id);
            if(user.getId() == work.getOwner().getId() || user.getId() == work.getArea().getBoss().getId() ){
                workService.deleteWorkById(id);
                template.convertAndSend("/work/reload","Egy munkát töröltek.");
            }else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Meow2");
        }else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Meow2");
    }
    @GetMapping(path = "/getstartedworks")
    public Collection<Work> getWorksInProgress(@RequestHeader("authorization")String rawToken) throws hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {

            return workService.getStartedWorks();

    }
    @GetMapping(path = "/getneedtocheckworks")
    public Collection<Work> getNeedToCheckWorks(@RequestHeader("authorization")String rawToken) throws hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {

            return workService.getNeedToCheckWorks();

    }
    @GetMapping(path = "/getdoneworks")
    public Collection<Work> getThisMonthsDoneWorks(@RequestHeader("authorization")String rawToken) throws hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {

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
    public Collection<Work> getUsersWorksByUserId(@RequestHeader("authorization")String rawToken,@PathVariable int id) throws hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException {
        if(authenticator.userIsUserOrAdmin(rawToken)){
            return workService.getWorksByUserId(id);
        }else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Meow2");


    }
    @PostMapping(path="/settostarted")
    public void setWorkToStarted(@RequestHeader("authorization")String rawToken,@RequestBody ObjectNode workId) throws UserDoesNotExistsException, InvalidWorkerException, WorkDoesNotExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidUsernameException, InvalidFullNameException, InvalidRoleException, InvalidTokenException {
        if(authenticator.userIsJanitor(rawToken)){
            workService.setWorkStarted(workId.get("workId").asInt(),authenticator.getUserFromRawToken(rawToken).getId());
            template.convertAndSend("/work/reload","Egy munkát elválaltak.");
        }

    }
    @PostMapping(path = "/settoproceed")
    public void setWorkToProceed(@RequestHeader("authorization")String rawToken,@RequestBody ObjectNode workId) throws InvalidProceedDateException, WorkDoesNotExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException, InterruptedException {
        if(authenticator.userIsJanitor(rawToken)){
            workService.setWorkProcceed(workId.get("workId").asInt());
            emailService.workHasBeenMarkedAsDoneNotifyAreaBoss(workService.getWorkById(workId.get("workId").asInt()));
            template.convertAndSend("/work/reload","Egy munkát befejeztek.");
        }
    }
    @PostMapping(path="/settodone")
    public void setWorkToDone(@RequestHeader("authorization")String rawToken,@RequestBody ObjectNode workId) throws InvalidDoneDateException, WorkDoesNotExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException, InterruptedException {
        if(authenticator.userIsUserOrAdmin(rawToken)) {
            workService.setWorkDone(workId.get("workId").asInt());
            emailService.workHasBeenAccaptedNotifyTheProperJanitor(workService.getWorkById(workId.get("workId").asInt()));
            template.convertAndSend("/work/reload","Egy munkát késznek nyílvánítottak.");
        }else{
            System.out.println("Tell me Whyeeee??:(");
        }
    }
    @PostMapping(path = "rejectwork")

    public void setWorkToRejected(@RequestHeader("authorization")String rawToken,@RequestBody ObjectNode workId) throws WorkDoesNotExistsException, InvalidIdException, InvalidOwnerException, InvalidTitleException, InvalidDescriptionException, InvalidCreationDateException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, InvalidRoleException, InvalidFullNameException, InvalidUsernameException, InvalidTokenException, InterruptedException {
        if(authenticator.userIsUserOrAdmin(rawToken)) {
            workService.setWorkToRejected(workId.get("workId").asInt());
            emailService.workHasBeenRejectedNotifyTheProperJanitor(workService.getWorkById(workId.get("workId").asInt()));
            template.convertAndSend("/work/reload","Egy munkát elutasítottak.");
        }
    }

}
