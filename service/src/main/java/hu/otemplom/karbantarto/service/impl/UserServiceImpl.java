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
        dao.setup();
        int newId =  dao.addUser(user);
        dao.exit();
        return newId;
    }

    @Override
    public boolean modifyUser(User user) throws UserDoesNotExistsException {
        return dao.modifyUser(user);
    }

    @Override
    public boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException {
        dao.setup();
        boolean success = dao.deleteUserByUserId(userId);
        dao.exit();
        return success;
    }

    @Override
    public Collection<User> getAllUser() {
        dao.setup();
        Collection<User> users = dao.getAllUser();
        dao.exit();
        return users;
    }

    @Override
    public User getUserByUserId(int userId) throws UserDoesNotExistsException {
        dao.setup();
        User user = dao.getUserByUserId(userId);
        dao.exit();

        return user;
    }

    @Override
    public User login(String username, String password) {
        System.out.println("meow");
        dao.setup();
        User user = dao.login(username,password);
        dao.exit();
        return user;
    }



}
