package hu.otemplom.karbantarto.service.impl.tests;

import hu.otemplom.karbantarto.dao.AreaDao;
import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.service.impl.AreaServiceImpl;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class AreaServiceTests {
    @Mock
    public AreaDao dao;
    @TestSubject
    private AreaServiceImpl service;
    Collection<Area> dummyDB;
    Area goodArea ;
    Area errorArea;
    @Before
    public void init(){
        dao = EasyMock.niceMock(AreaDao.class);
        service = new AreaServiceImpl(dao);

        EasyMock.replay(dao);
    }
    @Test
    public void addValidAreaTest(){

    }
}
