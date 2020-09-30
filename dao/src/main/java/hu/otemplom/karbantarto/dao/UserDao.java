package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;

import java.util.Collection;

public interface UserDao {
    void setup();
    void exit();
    int addUser(User user) throws DuplicateUserException, InvalidIdException;
    boolean modifyUser(User user) throws UserDoesNotExistsException;
    boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException;
    Collection<User> getAllUser();
    User getUserByUserId(int userId)throws UserDoesNotExistsException;
    User login(String username, String password);

}
