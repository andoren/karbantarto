package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.InvalidAreaException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository("fakeDao")
public class fakeAreaDataAccessDao implements AreaDao{
    private static List<Area> dummyDB = new ArrayList<>();
    public int addArea(Area area) throws AreaAlreadyExistsException, InvalidIdException {
        int exists = dummyDB.indexOf(area);
        if(exists > 0){
            throw new AreaAlreadyExistsException("");
        }
        else{
            area.setId(dummyDB.size()+1);
            dummyDB.add(area);
            return area.getId();
        }
    }

    public boolean modifyArea(Area area) throws AreaDoesNotExistsException {
        Area foundArea = getAreaById(area.getId());
        int index = dummyDB.indexOf(foundArea);
        dummyDB.set(index,area);
        return true;
    }

    public boolean deleteAreaById(int areaId) throws AreaDoesNotExistsException {
        Area foundArea = getAreaById(areaId);
        dummyDB.remove(foundArea);
        return true;
    }

    public Area getAreaById(int areaId) throws AreaDoesNotExistsException {
        return  dummyDB.stream().filter(p -> p.getId() == areaId).findFirst()
                .orElseThrow(() -> new AreaDoesNotExistsException(""));

    }

    public Collection<Area> getAllArea() {
        return dummyDB;
    }

    public Collection<Area> getAreasByUserId(int userId) {
        return dummyDB;
    }
}
