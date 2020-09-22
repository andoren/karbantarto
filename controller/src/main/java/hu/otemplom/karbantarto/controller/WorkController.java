package hu.otemplom.karbantarto.controller;


import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.otemplom.karbantarto.model.Exceptions.Work.*;
import hu.otemplom.karbantarto.model.Work;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;
import hu.otemplom.karbantarto.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("api/v1/work")
@RestController
public class WorkController {
    @Autowired
    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    WorkService workService;
    @GetMapping(path="/getnewworks")
    public Collection<Work> getAllNewWork(){
        return workService.getNewWorks();
    }
    @PostMapping
    public void addWork(@RequestBody Work work) throws InvalidIdException, InvalidCreationDateException {
        workService.addWork(work);
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
    public Work getWorkById(@PathVariable("id")int id) throws WorkDoesNotExistsException {
        return workService.getWorkById(id);
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
