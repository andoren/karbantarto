package hu.otemplom.karbantarto.model;


import hu.otemplom.karbantarto.model.Exceptions.*;
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
}
