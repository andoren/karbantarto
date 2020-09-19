package hu.otemplom.karbantarto.controller;


import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.service.AreaService;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.InvalidAreaException;
import hu.otemplom.karbantarto.service.impl.AreaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;
@RequestMapping("api/v1/area")
@RestController
public class AreaController {
    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    AreaService areaService;
    @GetMapping
    public Collection<Area> getAllArea(){
        return areaService.getAllArea();
    }
    @PostMapping
    public void addArea(@RequestBody Area area) throws InvalidIdException, InvalidAreaException, AreaAlreadyExistsException {
        areaService.addArea(area);
    }
    @PutMapping
    public void modifyArea(@RequestBody Area area) throws InvalidAreaException, AreaDoesNotExistsException {
        areaService.modifyArea(area);
    }

    @DeleteMapping(path="{id}")
    public void deleteArea(@PathVariable("id") int id) throws AreaDoesNotExistsException {
        areaService.deleteAreaById(id);
    }
    @GetMapping(path="{id}")
    public Area getAreaById(@PathVariable("id")int id) throws AreaDoesNotExistsException {
        return areaService.getAreaById(id);
    }
    @GetMapping(path="userId={userid}")
    public Collection<Area> getAreasByUserId(@PathVariable("userid")int id){
        return areaService.getAreasByUserId(id);
    }
}
