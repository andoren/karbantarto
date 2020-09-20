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
    public void SetNullFullName() throws InvalidFullNameException {
        user.setFullName(null);
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
    @Test
    public void SetValidUsername() throws InvalidUsernameException {
        user.setUsername("misike");
        Assert.assertEquals("misike",user.getUsername());
    }
    @Test  (expected = InvalidUsernameException.class)
    public void SetNullUsername() throws InvalidUsernameException {
        user.setUsername(null);

    }
    @Test(expected = InvalidUsernameException.class)
    public void SetTooShortUsername() throws InvalidUsernameException {
        user.setUsername("v");
    }
    @Test(expected = InvalidUsernameException.class)
    public void SetTooLongUsername() throws InvalidUsernameException {
        user.setUsername("Misike05Misike05Misike05Misike05Misike05Misike05");
    }
    @Test(expected = InvalidUsernameException.class)
    public void SetInvalidUsernameWithSpecialChar() throws InvalidUsernameException {
        user.setUsername("*/.:%!;");
    }
    @Test
    public void SetValidRole() throws InvalidRoleException {
        user.setRole(Role.Janitor);
        Assert.assertEquals(Role.Janitor,user.getRole());
    }
    @Test
    public void SetValidRole2() throws InvalidRoleException {
        user.setRole(Role.Admin);
        Assert.assertEquals(Role.Admin,user.getRole());
    }
    @Test(expected = InvalidRoleException.class)
    public void SetNullToRole() throws InvalidRoleException {
        user.setRole(null);

    }
    @Test
    public void SetValidPassword() throws InvalidPasswordException {
        user.setPassword("Misike05");
        Assert.assertEquals("Misike05",user.getPassword());
    }
    @Test(expected = InvalidPasswordException.class)
    public void SetNullPassword() throws InvalidPasswordException {
        user.setPassword(null);
    }
    @Test(expected = InvalidPasswordException.class)
    public void SetTooShorPassword() throws InvalidPasswordException {
        user.setPassword("m");

    }
    @Test(expected = InvalidPasswordException.class)
    public void SetTooLongPassword() throws InvalidPasswordException {
        user.setPassword("Misike05Misike05Misike05Misike05Misike05Misike05");

    }
    @Test(expected = InvalidPasswordException.class)
    public void SetTooFullWhiteSpacePassword() throws InvalidPasswordException {
        user.setPassword("      ");

    }
}
