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
    public UserServiceImpl(@Qualifier("fakeUserDao") UserDao dao) {
        this.dao = dao;
    }

    private UserDao dao;

    @Override
    public int AddUser(User user) throws DuplicateUserException, InvalidIdException {
        return dao.addUser(user);
    }

    @Override
    public boolean ModifyUser(User user) throws UserDoesNotExistsException {
        return dao.modifyUser(user);
    }

    @Override
    public boolean DeleteUserByUserId(int userId) throws UserDoesNotExistsException {
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
}
