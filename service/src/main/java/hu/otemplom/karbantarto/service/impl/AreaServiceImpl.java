package hu.otemplom.karbantarto.service.impl;

import hu.otemplom.karbantarto.dao.AreaDao;
import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.service.AreaService;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.InvalidAreaException;

import java.util.Collection;

public class AreaServiceImpl implements AreaService {
    AreaDao dao;

    public AreaServiceImpl(AreaDao dao) {
        this.dao = dao;
    }

    public int addArea(Area area) throws AreaAlreadyExistsException, InvalidAreaException {
        return dao.addArea(area);
    }

    public boolean modifyArea(Area area) throws AreaDoesNotExistsException, InvalidAreaException {
        return dao.modifyArea(area);
    }

    public boolean deleteAreaById(int areaId) throws AreaDoesNotExistsException {
        return false;
    }

    public Area getAreaById(int areaId) {
        return null;
    }

    public Collection<Area> getAllArea() {
        return null;
    }

    public Collection<Area> getAreasByUserId(int userId) {
        return null;
    }
}
