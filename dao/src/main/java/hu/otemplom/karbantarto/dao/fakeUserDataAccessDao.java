package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.InvalidAreaException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository("fakeUserDao")
public class fakeUserDataAccessDao implements UserDao {
    private static Collection <Area> dummyDB = new ArrayList<>();



    public int addUser(User user) throws DuplicateUserException {
        return 0;
    }


    public boolean modifyUser(User user) throws UserDoesNotExistsException {
        return false;
    }


    public boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException {
        return false;
    }

    public Collection<User> getAllUser() {
        return null;
    }


    public User getUserByUserId(int userId) throws UserDoesNotExistsException {
        return null;
    }
}
