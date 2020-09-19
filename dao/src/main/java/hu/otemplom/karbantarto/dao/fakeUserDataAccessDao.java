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
    @Override
    public int AddUser(User user) throws DuplicateUserException {
        return 0;
    }

    @Override
    public boolean ModifyUser(User user) throws UserDoesNotExistsException {
        return false;
    }

    @Override
    public boolean DeleteUserByUserId(int userId) throws UserDoesNotExistsException {
        return false;
    }

    @Override
    public Collection<User> getAllUser() {
        return null;
    }

    @Override
    public User getUserByUserId(int userId) throws UserDoesNotExistsException {
        return null;
    }
}
