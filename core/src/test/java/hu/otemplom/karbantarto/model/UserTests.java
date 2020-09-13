package hu.otemplom.karbantarto.model;


import hu.otemplom.karbantarto.model.Exceptions.User.*;
import org.junit.*;


public class UserTests {
    private User user;

    @Before
    public void Init(){
        user = new User();
    }
    @Test
    public void SetValidId() throws InvalidIdException {
        user.setId(1);
        Assert.assertEquals(1,user.getId());
    }
    @Test(expected = InvalidIdException.class)
    public void SetIdToZero() throws InvalidIdException {
        user.setId(0);
    }
    @Test(expected = InvalidIdException.class)
    public void SetIdToNegativNumber() throws InvalidIdException {
        user.setId(-1);
    }
    @Test
    public void SetValidFullName() throws InvalidFullNameException {
        user.setFullName("John Doe");
        Assert.assertEquals("John Doe",user.getFullName());
    }
    @Test(expected = InvalidFullNameException.class)
    public void SetNoSpaceFullName() throws InvalidFullNameException {
        user.setFullName("JohnDoe");
    }
    @Test(expected = InvalidFullNameException.class)
    public void SetTooShortFullName() throws InvalidFullNameException {
        user.setFullName("John");
    }
    @Test(expected = InvalidFullNameException.class)
    public void SetOnlyWhiteSpaceFullName() throws InvalidFullNameException {
        user.setFullName("         ");
    }
    @Test(expected = InvalidFullNameException.class)
    public void SetTooLongFullName() throws InvalidFullNameException {
        user.setFullName("123456789 123456789 123456789 123456789  123456789 1");
    }
}