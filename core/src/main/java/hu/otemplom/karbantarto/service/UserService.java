package hu.otemplom.karbantarto.service;

import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.*;

import java.util.Collection;

public interface UserService {
    int addUser(User user) throws DuplicateUserException, InvalidIdException;
    boolean modifyUser(User user) throws UserDoesNotExistsException;
    boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException;
    Collection<User> getAllUser();
    User getUserByUserId(int userId)throws UserDoesNotExistsException;
    User login(String username, String password);


}
