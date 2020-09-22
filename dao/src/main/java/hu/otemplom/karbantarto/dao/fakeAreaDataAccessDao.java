package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidPasswordException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.InvalidAreaException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository("fakeDao")
public class fakeAreaDataAccessDao implements AreaDao{
    private static List<Area> dummyDB;

    static {
        try {
            dummyDB = Arrays.asList(
                    new Area(1,"Iroda",new User(1,"Pekár Mihály","misike", Role.Admin,"Kiscica05")),
                    new Area(2,"Földszint",new User(2,"Stuller Istvánné","stullerine", Role.User,"Kiscica05")),
                    new Area(3,"Emelet",new User(3,"Kovács Éva","kovicse", Role.User,"Kiscica05")),
                    new Area(4,"Demens",new User(4,"Körmendi Szilvia","koszilvi", Role.User,"Kiscica05")),
                    new Area(5,"Szenvedély",new User(5,"Litauszki János","lityojani", Role.User,"Kiscica05")),
                    new Area(6,"Hajléktalan",new User(5,"Litauszki János","lityojani", Role.User,"Kiscica05")),
                    new Area(7,"Konyha",new User(6,"Kovácsné Horváth Gyöngyi","horkovi", Role.User,"Kiscica05")),
                    new Area(8,"Rákóczi",new User(7,"Hajdú Ágnes","hajdua", Role.Admin,"Kiscica05"))
                );
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        } catch (hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException e) {
            e.printStackTrace();
        } catch (InvalidFullNameException e) {
            e.printStackTrace();
        } catch (InvalidRoleException e) {
            e.printStackTrace();
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        }
    }

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

        return dummyDB.stream().filter(p -> p.getBoss().getId() == userId).collect(Collectors.toList());
    }
}
