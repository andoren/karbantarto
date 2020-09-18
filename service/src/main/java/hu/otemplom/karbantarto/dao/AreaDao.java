package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.*;

import java.util.Collection;

public interface AreaDao {
    int addArea(Area area) throws AreaAlreadyExistsException, InvalidAreaException, InvalidIdException;
    boolean modifyArea(Area area) throws AreaDoesNotExistsException,InvalidAreaException;
    boolean deleteAreaById(int areaId) throws AreaDoesNotExistsException;
    Area getAreaById(int areaId) throws AreaDoesNotExistsException;
    Collection<Area> getAllArea();
    Collection<Area> getAreasByUserId(int userId);
}
