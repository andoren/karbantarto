package hu.otemplom.karbantarto.service.impl;
import hu.otemplom.karbantarto.dao.UserDao;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
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
    public UserServiceImpl(@Qualifier("mysqlUserDao") UserDao dao) {

        this.dao = dao;

    }

    private UserDao dao;

    @Override
    public int addUser(User user) throws DuplicateUserException, InvalidIdException {

        int newId =  dao.addUser(user);

        return newId;
    }

    @Override
    public boolean modifyUser(User user) throws UserDoesNotExistsException, DuplicateUserException {

        boolean result =  dao.modifyUser(user);

        return result;
    }

    @Override
    public boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException {

        boolean success = dao.deleteUserByUserId(userId);

        return success;
    }

    @Override
    public Collection<User> getAllUser() {

        Collection<User> users = dao.getAllUser();

        return users;
    }

    @Override
    public User getUserByUserId(int userId) throws UserDoesNotExistsException {

        User user = dao.getUserByUserId(userId);


        return user;
    }

    @Override
    public User login(String username, String password) {


        User user = dao.login(username,password);

        return user;
    }



}
