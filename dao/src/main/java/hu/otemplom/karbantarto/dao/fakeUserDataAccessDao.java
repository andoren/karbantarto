package hu.otemplom.karbantarto.dao;


import hu.otemplom.karbantarto.model.User;

import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository("fakeUserDao")
public class fakeUserDataAccessDao implements UserDao {
    private static List <User> dummyDB = new ArrayList<>();



    public int addUser(User user) throws DuplicateUserException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException {
        user.setId(dummyDB.size()+1);
        dummyDB.add(user);
        return user.getId();
    }


    public boolean modifyUser(User user) throws UserDoesNotExistsException {
        User oldUser = getUserByUserId(user.getId());
        if(oldUser != null){
            int index = dummyDB.indexOf(oldUser);
            dummyDB.set(index,user);
            return true;
        }
        return false;
    }


    public boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException {
        User user = getUserByUserId(userId);
        if (user != null){
            dummyDB.remove(user);
            return true;
        }return false;
    }

    public Collection<User> getAllUser() {
        return dummyDB;
    }


    public User getUserByUserId(int userId) throws UserDoesNotExistsException {
        return dummyDB.stream().filter(p->p.getId() == userId).findFirst()
                .orElseThrow(()->new UserDoesNotExistsException("The user does not exists please try again. The userid is "+userId));
    }
}
