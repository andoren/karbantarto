package hu.otemplom.karbantarto.model;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class UserTests {
    private User user;
    @BeforeClass
    public void InitTest(){
        user = new User();
    }
    @Before
    public void Init(){

    }
    @After
    public void Reset(){
        user = new User();
    }
}
