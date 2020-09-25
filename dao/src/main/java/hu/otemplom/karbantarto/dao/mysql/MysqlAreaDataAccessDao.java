package hu.otemplom.karbantarto.dao.mysql;

import hu.otemplom.karbantarto.dao.AreaDao;
import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.InvalidAreaException;

import java.util.Collection;

public class MysqlAreaDataAccessDao implements AreaDao {
    @Override
    public int addArea(Area area) throws AreaAlreadyExistsException, InvalidAreaException, InvalidIdException {
        return 0;
    }

    @Override
    public boolean modifyArea(Area area) throws AreaDoesNotExistsException, InvalidAreaException {
        return false;
    }

    @Override
    public boolean deleteAreaById(int areaId) throws AreaDoesNotExistsException {
        return false;
    }

    @Override
    public Area getAreaById(int areaId) throws AreaDoesNotExistsException {
        return null;
    }

    @Override
    public Collection<Area> getAllArea() {
        return null;
    }

    @Override
    public Collection<Area> getAreasByUserId(int userId) {
        return null;
    }
}
