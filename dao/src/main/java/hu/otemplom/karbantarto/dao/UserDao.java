package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;

import java.util.Collection;
import java.util.List;

public interface UserDao {

    int addUser(User user) throws DuplicateUserException, InvalidIdException;
    boolean modifyUser(User user) throws UserDoesNotExistsException, DuplicateUserException;
    boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException;
    Collection<User> getAllUser();
    User getUserByUserId(int userId)throws UserDoesNotExistsException;
    User login(String username, String password);
    List<User> getJanitors();

}
