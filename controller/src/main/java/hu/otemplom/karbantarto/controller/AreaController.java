package hu.otemplom.karbantarto.controller;


import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.service.AreaService;
import hu.otemplom.karbantarto.service.impl.AreaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
