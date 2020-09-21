package hu.otemplom.karbantarto.controller;


import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidCreationDateException;
import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidIdException;
import hu.otemplom.karbantarto.model.Work;
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
}
