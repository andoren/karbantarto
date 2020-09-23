package hu.otemplom.karbantarto.controller;

import hu.otemplom.karbantarto.controller.exceptions.InvalidTokenException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.User;
import org.springframework.stereotype.Repository;


public interface IUserAuthenticator {
    String generateTokenFromUser(User user);
    User getUserFromRawToken(String rawToken) throws InvalidIdException, InvalidFullNameException, InvalidRoleException, InvalidUsernameException, InvalidTokenException;
    boolean userIsAdmin(String rawToken) throws InvalidIdException, InvalidUsernameException, InvalidFullNameException, InvalidRoleException, InvalidTokenException;
    boolean userIsJanitor(String rawToken) throws InvalidIdException, InvalidUsernameException, InvalidFullNameException, InvalidRoleException, InvalidTokenException;
    boolean userIsUserOrAdmin(String rawToken) throws InvalidIdException, InvalidUsernameException, InvalidFullNameException, InvalidRoleException, InvalidTokenException;
}
