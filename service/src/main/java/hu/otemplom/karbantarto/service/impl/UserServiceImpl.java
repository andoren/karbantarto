package hu.otemplom.karbantarto.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import hu.otemplom.karbantarto.dao.UserDao;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@ComponentScan(basePackages = {"hu.otemplom.karbantarto.dao"})
public class UserServiceImpl implements UserService {


    @Autowired
    public UserServiceImpl(@Qualifier("fakeUserDao") UserDao dao) {
        this.dao = dao;
    }

    private UserDao dao;

    @Override
    public int addUser(User user) throws DuplicateUserException, InvalidIdException {
        return dao.addUser(user);
    }

    @Override
    public boolean modifyUser(User user) throws UserDoesNotExistsException {
        return dao.modifyUser(user);
    }

    @Override
    public boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException {
        return dao.deleteUserByUserId(userId);
    }

    @Override
    public Collection<User> getAllUser() {
        return dao.getAllUser();
    }

    @Override
    public User getUserByUserId(int userId) throws UserDoesNotExistsException {
        return dao.getUserByUserId(userId);
    }

    @Override
    public User login(String username, String password) {
        User user = dao.login(username,password);

        return user;
    }



}
