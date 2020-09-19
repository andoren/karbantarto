package hu.otemplom.karbantarto.service;

import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.*;

import java.util.Collection;

public interface UserService {
    int AddUser(User user) throws DuplicateUserException, InvalidIdException;
    boolean ModifyUser(User user) throws UserDoesNotExistsException;
    boolean DeleteUserByUserId(int userId) throws UserDoesNotExistsException;
    Collection<User> getAllUser();
    User getUserByUserId(int userId)throws UserDoesNotExistsException;
}
