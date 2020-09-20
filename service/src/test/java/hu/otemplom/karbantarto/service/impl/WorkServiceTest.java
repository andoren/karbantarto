package hu.otemplom.karbantarto.service.impl;

import hu.otemplom.karbantarto.dao.WorkDao;
import hu.otemplom.karbantarto.model.Work;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.Arrays;
import java.util.Collection;

public class WorkServiceTest {
    @Mock
    public WorkDao dao;

    @TestSubject
    private WorkServiceImpl service;
    Collection<Work> dummyDB;
    Work goodWork ;
    Work errorwork;
    Work nullWork;

    @Before
    public void init(){
        goodWork = new Work();
        errorwork = new Work();
        EasyMock.niceMock(WorkDao.class);
        dummyDB = Arrays.asList(
          new Work(),
          new Work(),
          new Work(),
          new Work(),
          new Work(),
          new Work(),
          new Work(),
          new Work(),
          new Work(),
          new Work(),
          new Work()
        );
        EasyMock.replay(dao);

    }
}
