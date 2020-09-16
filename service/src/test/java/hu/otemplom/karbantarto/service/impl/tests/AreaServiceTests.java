package hu.otemplom.karbantarto.service.impl.tests;

import hu.otemplom.karbantarto.dao.AreaDao;
import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidBossException;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidNameException;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.InvalidAreaException;
import hu.otemplom.karbantarto.service.impl.AreaServiceImpl;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.easymock.EasyMock.same;

public class AreaServiceTests {
    @Mock
    public AreaDao dao;

    @TestSubject
    private AreaServiceImpl service;
    Collection<Area> dummyDB;
    Area goodArea ;
    Area errorArea;
    Area nullArea;
    @Before
    public void init() throws AreaAlreadyExistsException, InvalidAreaException, AreaDoesNotExistsException {
        goodArea = new Area();
        errorArea = new Area();
        dao = EasyMock.niceMock(AreaDao.class);
        service = new AreaServiceImpl(dao);
        dummyDB = Arrays.asList(
                new Area(1,"Demens",new User()),
                new Area(2,"Szenvedély",new User()),
                new Area(3,"Földszint",new User()),
                new Area(4,"Emelet",new User())
        );
        EasyMock.expect(dao.addArea(same(goodArea))).andReturn(5).anyTimes();
        EasyMock.expect(dao.addArea(same(errorArea))).andThrow(new AreaAlreadyExistsException("")).anyTimes();
        EasyMock.expect(dao.addArea(same(nullArea))).andThrow(new InvalidAreaException("")).anyTimes();
        EasyMock.expect(dao.modifyArea(same(goodArea))).andReturn(true).anyTimes();
        EasyMock.expect(dao.modifyArea(same(errorArea))).andThrow(new AreaDoesNotExistsException("")).anyTimes();
        EasyMock.expect(dao.modifyArea(same(nullArea))).andThrow(new InvalidAreaException("")).anyTimes();
        EasyMock.replay(dao);
    }
    @Test
    public void addValidAreaTest() throws InvalidNameException, InvalidBossException, AreaAlreadyExistsException, InvalidAreaException {
        goodArea.setName("Iroda");
        goodArea.setBoss(new User());
        int expected = 5;
        int actual = service.addArea(goodArea);
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = AreaAlreadyExistsException.class)
    public void addDuplicatedAreaTest() throws InvalidBossException, InvalidIdException, InvalidNameException, AreaAlreadyExistsException, InvalidAreaException {
        errorArea.setName("Demens");
        errorArea.setId(1);
        errorArea.setBoss(new User());
        service.addArea(errorArea);
    }
    @Test(expected = InvalidAreaException.class)
    public void addNullAreaTest() throws AreaAlreadyExistsException, InvalidAreaException {
        service.addArea(nullArea);
    }
    @Test
    public void modifyAreaTest() throws AreaDoesNotExistsException, InvalidAreaException {
        boolean expected = true;
        boolean actual = service.modifyArea(goodArea);
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = AreaDoesNotExistsException.class)
    public void modifyAreaWhichNotExistsTest() throws AreaDoesNotExistsException, InvalidAreaException {

        service.modifyArea(errorArea);

    }
    @Test(expected = InvalidAreaException.class)
    public void modifyAreaDoesNotExists() throws InvalidAreaException, AreaDoesNotExistsException {
        service.modifyArea(nullArea);
    }
}