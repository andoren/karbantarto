package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidDoneDateException;
import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidProceedDateException;
import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidWorkerException;
import hu.otemplom.karbantarto.model.Work;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("fakeWorkDao")
public class fakeWorkDataAccessDao implements WorkDao {
    private static List<Work> dummyDB = new ArrayList<>();
    @Override
    public int addWork(Work work) throws InvalidIdException {
        work.setId(dummyDB.size() +1);
        dummyDB.add(work);
        return work.getId();
    }

    @Override
    public boolean modifyWork(Work work) throws WorkDoesNotExistsException {
        Work modifyThis = getWorkById(work.getId());
        int index = dummyDB.indexOf(modifyThis);
        dummyDB.set(index,work);
        return true;
    }

    @Override
    public boolean deleteWorkById(int workId) throws WorkDoesNotExistsException {
        Work deleteThis = getWorkById(workId);
        dummyDB.remove(deleteThis);
        return true;
    }

    @Override
    public Work getWorkById(int workId) throws WorkDoesNotExistsException {
        return dummyDB.stream().filter(w->w.getId() == workId).findFirst().orElseThrow(()-> new WorkDoesNotExistsException(""));
    }

    @Override
    public Collection<Work> getNewWorks() {
        return dummyDB.stream().filter(w->w.getWorker() == null).collect(Collectors.toList());
    }

    @Override
    public Collection<Work> getWorksByUserId(int userId) {
        return dummyDB.stream().filter(w->w.getOwner().getId() == userId).collect(Collectors.toList());
    }

    @Override
    public Collection<Work> getStartedWorks()
    {
        return dummyDB.stream().filter(w->w.getWorker() != null && w.getProceedDate() == null).collect(Collectors.toList());
    }

    @Override
    public Collection<Work> getNeedToCheckWorks() {

        return dummyDB.stream().filter(w->w.getProceedDate() != null && !w.getIsDone()).collect(Collectors.toList());
    }

    @Override
    public Collection<Work> getThisMonthDoneWorks()
    {
        return dummyDB.stream().filter(w->w.getIsDone()).collect(Collectors.toList());
    }

    @Override
    public boolean setWorkStarted(int workId, int userId) throws WorkDoesNotExistsException, UserDoesNotExistsException, InvalidWorkerException {
        Work work = getWorkById(workId);
        fakeUserDataAccessDao userDataAccessDao = new fakeUserDataAccessDao();
        work.setWorker(userDataAccessDao.getUserByUserId(userId));
        return true;
    }

    @Override
    public boolean setWorkProcceed(int workId) throws WorkDoesNotExistsException, InvalidProceedDateException {
        Work work = getWorkById(workId);
        work.setProceedDate(new Date());
        return true;
    }

    @Override
    public boolean setWorkDone(int workId) throws WorkDoesNotExistsException, InvalidDoneDateException {
        Work work = getWorkById(workId);
        work.setDoneDate(new Date());
        return true;
    }
}
