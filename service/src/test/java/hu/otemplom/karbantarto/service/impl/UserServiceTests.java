package hu.otemplom.karbantarto.service.impl;

import hu.otemplom.karbantarto.dao.UserDao;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;

import java.util.Arrays;
import java.util.Collection;

public class UserServiceTests {

    @Mock
    public UserDao dao;

    @TestSubject
    private UserServiceImpl service;
    Collection<User> dummyDB;
    User goodArea ;
    User errorArea;
    User nullArea;
    @Before
    public void init() throws InvalidUsernameException, InvalidIdException, InvalidFullNameException, InvalidRoleException {
        goodArea = new User();
        errorArea = new User();
        dao = EasyMock.niceMock(User.class);
        service = new UserServiceImpl(dao);
        dummyDB = Arrays.asList(
                new User(1,"Pekár Mihály","misike", Role.Admin),
                new User(2,"Sárkány János","sarkanyj", Role.Janitor),
                new User(3,"Kovács Éva","kovicse", Role.User),
                new User(4,"Litauszki János","lityojani", Role.User),
                new User(5,"Stuller Istvánné","stullerine", Role.User)

        );

    }
}
