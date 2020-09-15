package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;

import java.util.Collection;

public interface UserDao {
    int AddUser(User user) throws DuplicateUserException;
    boolean ModifyUser(User user) throws UserDoesNotExistsException;
    boolean DeleteUserByUserId(int userId) throws UserDoesNotExistsException;
    Collection<User> getAllUser();
    User getUserByUserId(int userId)throws UserDoesNotExistsException;
}
