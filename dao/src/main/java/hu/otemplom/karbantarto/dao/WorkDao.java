package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidDoneDateException;
import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidProceedDateException;
import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidWorkerException;
import hu.otemplom.karbantarto.model.Work;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;

import java.util.Collection;

public interface WorkDao {
    int addWork(Work work) throws InvalidIdException;
    boolean modifyWork(Work work) throws WorkDoesNotExistsException;
    boolean deleteWorkById(int workId)throws WorkDoesNotExistsException;
    Work getWorkById(int workId) throws WorkDoesNotExistsException;
    Collection<Work> getNewWorks();
    Collection<Work> getWorksByUserId(int userId);
    Collection<Work> getStartedWorks();
    Collection<Work> getNeedToCheckWorks();
    Collection<Work> getThisMonthDoneWorks();
    boolean setWorkStarted(int workId,int userId) throws WorkDoesNotExistsException, InvalidWorkerException, UserDoesNotExistsException;
    boolean setWorkProcceed(int workId) throws WorkDoesNotExistsException, InvalidProceedDateException;
    boolean setWorkDone(int workId) throws WorkDoesNotExistsException, InvalidDoneDateException;
}
