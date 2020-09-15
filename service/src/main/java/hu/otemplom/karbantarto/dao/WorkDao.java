package hu.otemplom.karbantarto.dao;

import hu.otemplom.karbantarto.model.Work;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;

import java.util.Collection;

public interface WorkDao {
    int AddWork(Work work);
    boolean ModifyWork(Work work) throws WorkDoesNotExistsException;
    boolean DeleteWorkById(int workId)throws WorkDoesNotExistsException;
    Work getWorkById(int workId) throws WorkDoesNotExistsException;
    Collection<Work> getNewWorks();
    Collection<Work> getWorksByUserId(int userId);
    Collection<Work> getStartedWorks();
    Collection<Work> getNeedToCheckWorks();
    Collection<Work> getThisMonthDoneWorks();
}
