package hu.otemplom.karbantarto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import hu.otemplom.karbantarto.model.Exceptions.Work.*;
public class WorkTests {
    Work work;

    @Before
    public void init(){
        work = new Work();
    }

    @Test
    public void setValidId() throws InvalidIdException {
        work.setId(1);
        Assert.assertEquals(1,work.getId());
    }
    @Test (expected = InvalidIdException.class)
    public void setIdToZero() throws InvalidIdException {
        work.setId(0);
    }
    @Test(expected = InvalidIdException.class)
    public void setIdToNegativeNumber() throws InvalidIdException {
        work.setId(-1);
    }

    public void setValidTitle() throws InvalidTitleException {
        String expected = "Ez egy valid title";
        work.setTitle(expected);
        Assert.assertEquals(expected,work.getTitle());
    }
    @Test(expected = InvalidTitleException.class)
    public void setTitleToNull() throws InvalidTitleException {
        String expected = null;
        work.setTitle(expected);
        Assert.assertEquals(expected,work.getTitle());
    }
    @Test(expected = InvalidTitleException.class)
    public void setTitleToWhiteSpaces() throws InvalidTitleException {
        String expected = "               ";
        work.setTitle(expected);
        Assert.assertEquals(expected,work.getTitle());
    }
    @Test(expected = InvalidTitleException.class)
    public void setTooShortTitle() throws InvalidTitleException {
        work.setTitle("123456789");
    }
    @Test(expected = InvalidTitleException.class)
    public void setTooLongTitle() throws InvalidTitleException{
        work.setTitle("123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789");
    }
}
