package hu.otemplom.karbantarto.service.impl;


import hu.otemplom.karbantarto.dao.AreaDao;
import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.service.AreaService;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.InvalidAreaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@ComponentScan(basePackages = {"hu.otemplom.karbantarto.dao"})
public class AreaServiceImpl implements AreaService {
    AreaDao dao;
    @Autowired
    public AreaServiceImpl(@Qualifier("fakeDao") AreaDao dao) {

        this.dao = dao;
    }

    public int addArea(Area area) throws AreaAlreadyExistsException, InvalidAreaException, InvalidIdException {
        return dao.addArea(area);
    }

    public boolean modifyArea(Area area) throws AreaDoesNotExistsException, InvalidAreaException {
        return dao.modifyArea(area);
    }

    public boolean deleteAreaById(int areaId) throws AreaDoesNotExistsException {
        return dao.deleteAreaById(areaId);
    }

    public Area getAreaById(int areaId) throws AreaDoesNotExistsException {

        return dao.getAreaById(areaId);
    }

    public Collection<Area> getAllArea() {

        return dao.getAllArea();

    }

    public Collection<Area> getAreasByUserId(int userId) {

        return dao.getAreasByUserId(userId);

    }
}
