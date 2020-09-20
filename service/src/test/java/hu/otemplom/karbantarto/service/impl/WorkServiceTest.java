package hu.otemplom.karbantarto.service.impl;

import hu.otemplom.karbantarto.dao.WorkDao;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.Work.*;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.model.Work;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;

import static org.easymock.EasyMock.same;

public class WorkServiceTest {
    @Mock
    public WorkDao dao;

    @TestSubject
    private WorkServiceImpl service;
    Collection<Work> dummyDB;
    User janitor ;
    User userTwo;
    User userThree;
    Work goodWork ;
    Work errorwork;
    Work nullWork;

    @Before
    public void init() throws ParseException, InvalidOwnerException, InvalidIdException, InvalidTitleException, InvalidProceedDateException, InvalidCreationDateException, InvalidDoneDateException, InvalidDescriptionException, InvalidWorkerException, InvalidRoleException {
        goodWork = new Work();
        errorwork = new Work();
        janitor = new User();
        janitor.setRole(Role.Janitor);

        userTwo = new User();
        userTwo.setRole(Role.User);
        userThree = new User();
        userThree.setRole(Role.Admin);
        dao = EasyMock.niceMock(WorkDao.class);
        service = new WorkServiceImpl(dao);
        dummyDB = Arrays.asList(
                new Work(1,"Done Work1","This is a doneWork i hope its gonna work.",janitor,userTwo,new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24"),new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24"),new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24")),
                new Work(2,"Done Work2","This is a doneWork i hope its gonna work.",janitor,userTwo,new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24"),new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24"),new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24")),
                new Work(3,"Done Work3","This is a doneWork i hope its gonna work.",janitor,userThree,new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24"),new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24"),new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24")),
                new Work(4,"NeededToCheck Work","This is a NeededToCheck i hope its gonna work.",janitor,userTwo,new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24"),new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24")),
                new Work(5,"NeededToCheck Work2","This is a NeededToCheck i hope its gonna work.",janitor,userTwo,new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24"),new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24")),
                new Work(6,"InProccedWork","This is a proceedWork i hope its gonna work.",janitor,userThree,new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24")),
                new Work(7,"New Work01","This is a new work i hope its gonna work",userTwo,new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24")),
                new Work(8,"New Work02","This is a new work i hope its gonna work",userTwo,new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24")),
                new Work(9,"New Work03","This is a new work i hope its gonna work",userTwo,new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24")),
                new Work(10,"New Work04","This is a new work i hope its gonna work",userTwo,new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24"))
        );
        EasyMock.expect(dao.addWork(same(goodWork))).andReturn(11).anyTimes();
        EasyMock.replay(dao);

    }
    @Test
    public void addValidWorkTest()
    {
        int expected = 11;
        int actual = service.addWork(goodWork);
        Assert.assertEquals(expected,actual);
    }
}

