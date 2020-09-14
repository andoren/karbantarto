package hu.otemplom.karbantarto.service;

import hu.otemplom.karbantarto.service.Exceptions.Area.*;
import hu.otemplom.karbantarto.model.Area;

import java.util.Collection;

public interface AreaService {
    int addArea(Area area) throws AreaAlreadyExistsException;
    boolean modifyArea(Area area) throws AreaDoesNotExistsException;
    boolean deleteAreaById(int areaId) throws AreaDoesNotExistsException;
    Area getAreaById(int areaId);
    Collection<Area> getAllArea();
    Collection<Area> getAreasByUserId(int userId);

}
