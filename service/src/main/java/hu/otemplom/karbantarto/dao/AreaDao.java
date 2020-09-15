package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;

import java.util.Collection;

public interface AreaDao {
    int addArea(Area area) throws AreaAlreadyExistsException;
    boolean modifyArea(Area area) throws AreaDoesNotExistsException;
    boolean deleteAreaById(int areaId) throws AreaDoesNotExistsException;
    Area getAreaById(int areaId);
    Collection<Area> getAllArea();
    Collection<Area> getAreasByUserId(int userId);
}
