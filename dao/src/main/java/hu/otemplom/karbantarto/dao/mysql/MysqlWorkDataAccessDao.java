package hu.otemplom.karbantarto.dao.mysql;

import hu.otemplom.karbantarto.dao.WorkDao;
import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Work.*;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.model.Work;

import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("mysqlDao")
public class  MysqlWorkDataAccessDao implements WorkDao {


    @Override
    public void setup() {

    }

    @Override
    public void exit() {

    }

    public int addWork(Work work) throws InvalidIdException, InvalidCreationDateException {
        return 0;
    }


    public boolean modifyWork(Work work) throws WorkDoesNotExistsException {
        return false;
    }


    public boolean deleteWorkById(int workId) throws WorkDoesNotExistsException {
        return false;
    }

    public Work getWorkById(int workId) throws WorkDoesNotExistsException {
        return null;
    }


    public Collection<Work> getNewWorks() {
        return null;
    }


    public Collection<Work> getWorksByUserId(int userId) {
        return null;
    }


    public Collection<Work> getStartedWorks() {
        return null;
    }


    public Collection<Work> getNeedToCheckWorks() {
        return null;
    }


    public Collection<Work> getThisMonthDoneWorks() {
        return null;
    }

    public boolean setWorkStarted(int workId, int userId) throws WorkDoesNotExistsException, InvalidWorkerException, UserDoesNotExistsException {
        return false;
    }


    public boolean setWorkProcceed(int workId) throws WorkDoesNotExistsException, InvalidProceedDateException {
        return false;
    }


    public boolean setWorkDone(int workId) throws WorkDoesNotExistsException, InvalidDoneDateException {
        return false;
    }


    public boolean setWorkToRejected(int workId) throws WorkDoesNotExistsException, InvalidTitleException, InvalidIdException, InvalidOwnerException, InvalidDescriptionException, InvalidCreationDateException {
        return false;
    }
}
