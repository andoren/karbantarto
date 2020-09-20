package hu.otemplom.karbantarto.service.impl;

import hu.otemplom.karbantarto.dao.WorkDao;
import hu.otemplom.karbantarto.model.Work;
import org.easymock.Mock;
import org.easymock.TestSubject;

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
}
