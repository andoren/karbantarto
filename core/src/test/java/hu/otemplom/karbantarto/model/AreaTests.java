package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.Area.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AreaTests {
    private Area area;
    @Before
    public void init(){
        area = new Area();
    }
    @Test
    public void testParamConstructorArea(){
        User tempUser = new User();
        area  = new Area(1,"Demens",tempUser);
        boolean expected = false;
        expected = 1 == area.getId();
        expected = "Demens".equals(area.getName());
        expected = tempUser == area.getBoss();
        Assert.assertTrue(expected);
    }
    @Test
    public void setValidId() throws InvalidIdException {
        int expected = 1;
        area.setId(expected);
        Assert.assertTrue(area.getId() == expected);
    }
    @Test(expected = InvalidIdException.class)
    public void setNegativID() throws InvalidIdException {
        int expected = -1;
        area.setId(expected);
        Assert.assertTrue(area.getId() == expected);
    }
    @Test
    public void setValidName() throws InvalidNameException {
        String expected = "Name";
        area.setName(expected);
        Assert.assertTrue(area.getName() == expected);
    }
    @Test(expected = InvalidNameException.class)
    public void setNullName() throws InvalidNameException {
        String expected = null;
        area.setName(expected);

    }
    @Test(expected = InvalidNameException.class)
    public void setlNameTooShort() throws InvalidNameException {
        String expected = "123";
        area.setName(expected);

    }
    @Test(expected = InvalidNameException.class)
    public void setNameTooLong() throws InvalidNameException {
        String expected = "1234567891234567890012345678912345678900";
        area.setName(expected);

    }
    @Test
    public void setValidBoss() throws InvalidBossException {
        User expected = new User();
        area.setBoss(expected);
        Assert.assertTrue(area.getBoss() == expected);
    }
    @Test(expected = InvalidBossException.class)
    public void setNullBoss() throws InvalidBossException {
        User expected = null;
        area.setBoss(expected);

    }
}
