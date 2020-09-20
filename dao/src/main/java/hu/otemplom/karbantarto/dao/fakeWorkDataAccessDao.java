package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Work;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository("fakeWorkDao")
public class fakeWorkDataAccessDao implements WorkDao {
    @Override
    public int addWork(Work work) {
        return 0;
    }

    @Override
    public boolean modifyWork(Work work) throws WorkDoesNotExistsException {
        return false;
    }

    @Override
    public boolean deleteWorkById(int workId) throws WorkDoesNotExistsException {
        return false;
    }

    @Override
    public Work getWorkById(int workId) throws WorkDoesNotExistsException {
        return null;
    }

    @Override
    public Collection<Work> getNewWorks() {
        return null;
    }

    @Override
    public Collection<Work> getWorksByUserId(int userId) {
        return null;
    }

    @Override
    public Collection<Work> getStartedWorks() {
        return null;
    }

    @Override
    public Collection<Work> getNeedToCheckWorks() {
        return null;
    }

    @Override
    public Collection<Work> getThisMonthDoneWorks() {
        return null;
    }

    @Override
    public boolean setWorkStarted(int workId, int userId) throws WorkDoesNotExistsException {
        return false;
    }

    @Override
    public boolean setWorkProcceed(int workId) throws WorkDoesNotExistsException {
        return false;
    }

    @Override
    public boolean setWorkDone(int workId) throws WorkDoesNotExistsException {
        return false;
    }
}
