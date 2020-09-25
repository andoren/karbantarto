package hu.otemplom.karbantarto.dao.mysql;

import hu.otemplom.karbantarto.dao.UserDao;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;

import java.util.Collection;

public class MysqlUserDataAccessDao implements UserDao {
    @Override
    public int addUser(User user) throws DuplicateUserException, InvalidIdException {
        return 0;
    }

    @Override
    public boolean modifyUser(User user) throws UserDoesNotExistsException {
        return false;
    }

    @Override
    public boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException {
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

    @Override
    public User login(String username, String password) {
        return null;
    }
}
