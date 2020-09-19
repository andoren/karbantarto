package hu.otemplom.karbantarto.service.impl;

import hu.otemplom.karbantarto.dao.UserDao;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.easymock.EasyMock.getEasyMockProperty;
import static org.easymock.EasyMock.same;

public class UserServiceTests {

    @Mock
    public UserDao dao;

    @TestSubject
    private UserServiceImpl service;
    Collection<User> dummyDB;
    User goodUser ;
    User errorUser;
    User nullUser;
    @Before
    public void init() throws InvalidUsernameException, InvalidIdException, InvalidFullNameException, InvalidRoleException, DuplicateUserException {
        goodUser = new User();
        errorUser = new User();
        dao = EasyMock.niceMock(UserDao.class);
        service = new UserServiceImpl(dao);
        dummyDB = Arrays.asList(
                new User(1,"Pekár Mihály","misike", Role.Admin),
                new User(2,"Sárkány János","sarkanyj", Role.Janitor),
                new User(3,"Kovács Éva","kovicse", Role.User),
                new User(4,"Litauszki János","lityojani", Role.User),
                new User(5,"Stuller Istvánné","stullerine", Role.User)

        );
        EasyMock.expect(dao.addUser(same(goodUser))).andReturn(5).anyTimes();
        EasyMock.expect(dao.addUser(same(errorUser))).andThrow(new DuplicateUserException()).anyTimes();
        EasyMock.replay(dao);
    }
    @Test
    public void addValidUserTest() throws DuplicateUserException {
        int expected = 5;
        int actual = service.AddUser(goodUser);
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = DuplicateUserException.class)
    public void addDuplicateUser() throws DuplicateUserException {
        service.AddUser(errorUser);
    }
}
