package hu.otemplom.karbantarto.service.impl;

import hu.otemplom.karbantarto.dao.WorkDao;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.Work.*;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.model.Work;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;
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
import java.util.stream.Collectors;

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
    public void init() throws ParseException, InvalidOwnerException, InvalidIdException, InvalidTitleException, InvalidProceedDateException, InvalidCreationDateException, InvalidDoneDateException, InvalidDescriptionException, InvalidWorkerException, InvalidRoleException, WorkDoesNotExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException, UserDoesNotExistsException {
        goodWork = new Work();
        errorwork = new Work();
        janitor = new User();
        janitor.setRole(Role.Janitor);
        janitor.setId(4);
        userTwo = new User();
        userTwo.setId(2);
        userTwo.setRole(Role.User);
        userThree = new User();
        userThree.setId(3);
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
        EasyMock.expect(dao.deleteWorkById(1)).andReturn(true).anyTimes();
        EasyMock.expect(dao.deleteWorkById(999)).andThrow(new WorkDoesNotExistsException(""));
        EasyMock.expect(dao.modifyWork(goodWork)).andReturn(true).anyTimes();
        EasyMock.expect(dao.modifyWork(errorwork)).andThrow(new WorkDoesNotExistsException(""));
        EasyMock.expect(dao.getWorkById(1)).andReturn(goodWork).anyTimes();
        EasyMock.expect(dao.getWorkById(999)).andThrow(new WorkDoesNotExistsException(""));
        EasyMock.expect(dao.getNeedToCheckWorks()).andReturn(dummyDB.stream().filter(p->p.getProceedDate() != null && !p.getIsDone()).collect(Collectors.toList())).anyTimes();
        EasyMock.expect(dao.getThisMonthDoneWorks()).andReturn(dummyDB.stream().filter(p->p.getIsDone()).collect(Collectors.toList())).anyTimes();
        EasyMock.expect(dao.getNewWorks()).andReturn(dummyDB.stream().filter(w-> !w.getIsDone() && w.getWorker() == null).collect(Collectors.toList())).anyTimes();
        EasyMock.expect(dao.getStartedWorks()).andReturn(dummyDB.stream().filter(w->!w.getIsDone()&& w.getWorker() != null && w.getProceedDate() == null).collect(Collectors.toList())).anyTimes();
        EasyMock.expect(dao.setWorkDone(1)).andReturn(true).anyTimes();
        EasyMock.expect(dao.setWorkDone(999)).andThrow(new WorkDoesNotExistsException("")).anyTimes();
        EasyMock.expect(dao.setWorkProcceed(1)).andReturn(true).anyTimes();
        EasyMock.expect(dao.setWorkProcceed(999)).andThrow(new WorkDoesNotExistsException("")).anyTimes();
        EasyMock.expect(dao.setWorkStarted(1,1)).andReturn(true).anyTimes();
        EasyMock.expect(dao.setWorkStarted(999,1)).andThrow(new WorkDoesNotExistsException("")).anyTimes();
        EasyMock.replay(dao);

    }
    @Test
    public void addValidWorkTest() throws InvalidIdException, InvalidCreationDateException {
        int expected = 11;
        int actual = service.addWork(goodWork);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void deleteValidWorkTest() throws WorkDoesNotExistsException {
        boolean expected = true;
        boolean actual = service.deleteWorkById(1);
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = WorkDoesNotExistsException.class)
    public void deleteInvalidWorkTest() throws WorkDoesNotExistsException {
        service.deleteWorkById(999);
    }
    @Test
    public void modifyValidWorkTest() throws WorkDoesNotExistsException {
        boolean expected = true;
        boolean actual = service.modifyWork(goodWork);
    }
    @Test(expected = WorkDoesNotExistsException.class)
    public void modifyInvalidWorkTest() throws WorkDoesNotExistsException {
        service.modifyWork(errorwork);
    }
    @Test
    public void validGetWorkByIdTest() throws WorkDoesNotExistsException {
        Work actual = service.getWorkById(1);
        Assert.assertEquals(goodWork,actual);
    }
    @Test(expected = WorkDoesNotExistsException.class)
    public void invalidGetWorkByIdTest() throws WorkDoesNotExistsException {
        service.getWorkById(999);
    }
    @Test
    public void getCheckNeededWorksTest(){
        int expected = 2;
        int actual = service.getNeedToCheckWorks().size();
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void getThisMonthDoneWorksTest(){
        int expected = 3;
        int actual = service.getThisMonthDoneWorks().size();
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void getNewWorksTest(){
        int expected = 4;
        int actual = service.getNewWorks().size();
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void getStartedWorksTest(){
        int expected = 1;
        int actual = service.getStartedWorks().size();
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void setValidStartedWorkTest() throws WorkDoesNotExistsException, InvalidWorkerException, UserDoesNotExistsException {
        boolean expected = true;
        boolean actual = service.setWorkStarted(1,1);

    }
    @Test(expected = WorkDoesNotExistsException.class)
    public void setInvalidStartedWorkTest() throws WorkDoesNotExistsException, InvalidWorkerException, UserDoesNotExistsException {
        service.setWorkStarted(999,1);
    }
    @Test
    public void setValidProccedWork() throws WorkDoesNotExistsException, InvalidProceedDateException {
        boolean expected = true;
        boolean actual =  service.setWorkProcceed(1);
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = WorkDoesNotExistsException.class)
    public void setInvalidProceedWork() throws WorkDoesNotExistsException, InvalidProceedDateException {
        service.setWorkProcceed(999);
    }
    @Test
    public void setValidWorkDoneTest() throws WorkDoesNotExistsException, InvalidDoneDateException {
        boolean expected = true;
        boolean actual = service.setWorkDone(1);
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = WorkDoesNotExistsException.class)
    public void setInvalidWorkDoneTest() throws WorkDoesNotExistsException, InvalidDoneDateException {
        service.setWorkDone(999);
    }
}

