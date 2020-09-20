package hu.otemplom.karbantarto.service;

import hu.otemplom.karbantarto.model.Work;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.*;

import java.util.Collection;

public interface WorkService {
    int addWork(Work work);
    boolean modifyWork(Work work) throws WorkDoesNotExistsException;
    boolean deleteWorkById(int workId)throws WorkDoesNotExistsException;
    Work getWorkById(int workId) throws WorkDoesNotExistsException;
    Collection<Work> getNewWorks();
    Collection<Work> getWorksByUserId(int userId);
    Collection<Work> getStartedWorks();
    Collection<Work> getNeedToCheckWorks();
    Collection<Work> getThisMonthDoneWorks();
}
