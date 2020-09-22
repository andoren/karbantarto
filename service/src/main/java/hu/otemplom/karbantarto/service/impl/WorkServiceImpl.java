package hu.otemplom.karbantarto.service.impl;

import hu.otemplom.karbantarto.dao.WorkDao;
import hu.otemplom.karbantarto.model.Exceptions.Work.*;
import hu.otemplom.karbantarto.model.Work;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;
import hu.otemplom.karbantarto.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@ComponentScan(basePackages = {"hu.otemplom.karbantarto.dao"})
public class WorkServiceImpl implements WorkService {
    @Autowired
    public WorkServiceImpl(@Qualifier("fakeWorkDao") WorkDao dao) {
        this.dao = dao;
    }

    private WorkDao dao;

    @Override
    public int addWork(Work work) throws InvalidIdException, InvalidCreationDateException {
        return dao.addWork(work);
    }

    @Override
    public boolean modifyWork(Work work) throws WorkDoesNotExistsException {
        return dao.modifyWork(work);
    }

    @Override
    public boolean deleteWorkById(int workId) throws WorkDoesNotExistsException {
        return dao.deleteWorkById(workId);
    }

    @Override
    public Work getWorkById(int workId) throws WorkDoesNotExistsException {
        return dao.getWorkById(workId);
    }

    @Override
    public Collection<Work> getNewWorks() {
        return dao.getNewWorks();
    }

    @Override
    public Collection<Work> getWorksByUserId(int userId) {
        return dao.getWorksByUserId(userId);
    }

    @Override
    public Collection<Work> getStartedWorks() {
        return dao.getStartedWorks();
    }

    @Override
    public Collection<Work> getNeedToCheckWorks() {
        return dao.getNeedToCheckWorks();
    }

    @Override
    public Collection<Work> getThisMonthDoneWorks() {
        return dao.getThisMonthDoneWorks();
    }

    @Override
    public boolean setWorkStarted(int workId, int userId) throws WorkDoesNotExistsException, InvalidWorkerException, UserDoesNotExistsException {
        return dao.setWorkStarted(workId,userId);
    }

    @Override
    public boolean setWorkProcceed(int workId) throws WorkDoesNotExistsException, InvalidProceedDateException {
        return dao.setWorkProcceed(workId);
    }

    @Override
    public boolean setWorkDone(int workId) throws WorkDoesNotExistsException, InvalidDoneDateException {
        return dao.setWorkDone(workId);
    }

    @Override
    public boolean setWorkToRejected(int workId) throws WorkDoesNotExistsException, InvalidIdException, InvalidCreationDateException, InvalidTitleException, InvalidDescriptionException, InvalidOwnerException {
        return dao.setWorkToRejected(workId);
    }
}
