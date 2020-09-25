package hu.otemplom.karbantarto.dao;


import hu.otemplom.karbantarto.model.Exceptions.User.*;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;

import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository("fakeUserDao")
public class fakeUserDataAccessDao implements UserDao {
    private static List <User> dummyDB;

    static {
        try {
            dummyDB = Arrays.asList(
                    new User(1,"Pekár Mihály","misike", Role.Admin,"Kiscica05"),
                    new User(2,"Stuller Istvánné","stullerine", Role.User,"Kiscica05"),
                    new User(3,"Kovács Éva","kovicse", Role.User,"Kiscica05"),
                    new User(4,"Körmendi Szilvia","koszilvi", Role.User,"Kiscica05"),
                    new User(5,"Litauszki János","lityojani", Role.User,"Kiscica05"),
                    new User(6,"Kovácsné Horváth Gyöngyi","horkovi", Role.User,"Kiscica05"),
                    new User(7,"Hajdú Ágnes","hajdua", Role.Admin,"Kiscica05"),
                    new User(8,"Kamarás Mária","kamzim", Role.Admin,"Kiscica05"),
                    new User(9,"Pekár Mihály","pekarm", Role.Janitor,"Kiscica05"),
                    new User(10,"Sárkány János","sarkanyj", Role.Janitor,"Kiscica05")
                );
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        } catch (InvalidIdException e) {
            e.printStackTrace();
        } catch (InvalidFullNameException e) {
            e.printStackTrace();
        } catch (InvalidRoleException e) {
            e.printStackTrace();
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setup() {

    }

    @Override
    public void exit() {

    }

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

    @Override
    public User login(String username, String password) {
        return dummyDB.stream().filter(u->u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst().orElse(null);
    }
}
