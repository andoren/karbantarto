package hu.otemplom.karbantarto.service.impl;

import hu.otemplom.karbantarto.dao.UserDao;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
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
    public void init() throws InvalidUsernameException, InvalidIdException, InvalidFullNameException, InvalidRoleException, DuplicateUserException, UserDoesNotExistsException {
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
        EasyMock.expect(dao.deleteUserByUserId(1)).andReturn(true).anyTimes();
        EasyMock.expect(dao.deleteUserByUserId(999)).andThrow(new UserDoesNotExistsException("")).anyTimes();
        EasyMock.expect(dao.getAllUser()).andReturn(dummyDB).anyTimes();
        EasyMock.expect(dao.getUserByUserId(1)).andReturn(goodUser).anyTimes();
        EasyMock.expect(dao.getUserByUserId(999)).andThrow(new UserDoesNotExistsException("")).anyTimes();
        EasyMock.expect(dao.modifyUser(goodUser)).andReturn(true).anyTimes();
        EasyMock.expect(dao.modifyUser(errorUser)).andThrow(new UserDoesNotExistsException("")).anyTimes();
        EasyMock.replay(dao);
    }
    @Test
    public void getAllUserTest(){
        int expected = dummyDB.size();
        Collection<User> actual = service.getAllUser();
        Assert.assertEquals(expected,actual.size());
    }
    @Test
    public void addValidUserTest() throws DuplicateUserException, InvalidIdException {
        int expected = 5;
        int actual = service.addUser(goodUser);
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = DuplicateUserException.class)
    public void addDuplicateUserTest() throws DuplicateUserException, InvalidIdException {
        service.addUser(errorUser);
    }
    @Test
    public void deleteUserTest() throws UserDoesNotExistsException {
        boolean expected = true;
        boolean actual = service.deleteUserByUserId(1);
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = UserDoesNotExistsException.class)
    public void deleteInvalidUserTest() throws UserDoesNotExistsException {
        service.deleteUserByUserId(999);
    }
    @Test
    public void getUserByUserIdTest() throws UserDoesNotExistsException {
        User testUser = service.getUserByUserId(1);
        Assert.assertEquals(testUser,goodUser);
    }
    @Test(expected = UserDoesNotExistsException.class)
    public void getUserByUserIdTestInvalidId() throws UserDoesNotExistsException {
        User pleaseThorwException = service.getUserByUserId(999);
    }
    @Test
    public void modifyUserTest() throws UserDoesNotExistsException, DuplicateUserException {
        boolean expected = true;
        boolean actual = service.modifyUser(goodUser);
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = UserDoesNotExistsException.class)
    public void modifyUserInvalidUserTest() throws UserDoesNotExistsException, DuplicateUserException {
        service.modifyUser(errorUser);
    }
}
