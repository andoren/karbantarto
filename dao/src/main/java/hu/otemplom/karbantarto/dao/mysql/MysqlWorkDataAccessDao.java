package hu.otemplom.karbantarto.dao.mysql;

import hu.otemplom.karbantarto.dao.WorkDao;
import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Work.*;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.model.Work;

import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.WorkService.WorkDoesNotExistsException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Repository("mysqlWorkDao")
public class  MysqlWorkDataAccessDao implements WorkDao {



    public int addWork(Work work) throws InvalidIdException, InvalidCreationDateException {
        Session session = SessionSingleton.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(work);
            session.getTransaction().commit();

        }catch (Exception e){


            e.printStackTrace();
        }finally {
            session.close();

        }
        return work.getId();
    }


    public boolean modifyWork(Work work) throws WorkDoesNotExistsException {
        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(work);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return true;
    }


    public boolean deleteWorkById(int workId) throws WorkDoesNotExistsException {
        Work work = getWorkById(workId);

        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(work);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();

        }

        return true;
    }

    public Work getWorkById(int workId) throws WorkDoesNotExistsException {
        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        Work work;
        try{
            work = session.get(Work.class,workId);
        }catch (Exception e){
            e.printStackTrace();
            throw new WorkDoesNotExistsException("Hibás munka id. Kérem próbálja újra.");
        }finally {
            session.close();
        }
        return work;
    }


    public Collection<Work> getNewWorks() {
        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        Collection<Work> newWorks = new ArrayList<>();
        Query query = session.createQuery("from Work w where w.Worker is null ");
        try {
            newWorks  = query.getResultList();
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        session.close();

        return newWorks;
    }


    public Collection<Work> getWorksByUserId(int userId) {
        return null;
    }


    public Collection<Work> getStartedWorks() {
        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        Collection<Work> startedWorks = new ArrayList<>();
        Query query = session.createQuery("from Work w where w.Worker is not null and ProceedDate is null ");
        try {
            startedWorks  = query.getResultList();
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        session.close();

        return startedWorks;
    }


    public Collection<Work> getNeedToCheckWorks() {
        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        Collection<Work> checkNeededWorks = new ArrayList<>();
        Query query = session.createQuery("from Work w where w.Worker is not null and ProceedDate is not null and DoneDate is null ");
        try {
            checkNeededWorks  = query.getResultList();
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        session.close();

        return checkNeededWorks;
    }


    public Collection<Work> getThisMonthDoneWorks() {
        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        Collection<Work> thisMonthDonwWorks = new ArrayList<>();
        Query query = session.createQuery("from Work w where DoneDate is not null  ");
        try {
            thisMonthDonwWorks  = query.getResultList();
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        session.close();

        return thisMonthDonwWorks;
    }

    public boolean setWorkStarted(int workId, int userId) throws WorkDoesNotExistsException, InvalidWorkerException, UserDoesNotExistsException, hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException {
        Work work = getWorkById(workId);
        MysqlUserDataAccessDao userdao = new MysqlUserDataAccessDao();
        User user = userdao.getUserByUserId(userId);
        Session session = SessionSingleton.getSessionFactory().openSession();
        work.setWorker(user);
        session.beginTransaction();
        try {
            session.update(work);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();

        }

        return true;
    }


    public boolean setWorkProcceed(int workId) throws WorkDoesNotExistsException, InvalidProceedDateException {
        Work work = getWorkById(workId);
        work.setProceedDate(new Date());
        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(work);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();

        }

        return true;

    }


    public boolean setWorkDone(int workId) throws WorkDoesNotExistsException, InvalidDoneDateException {
        Work work = getWorkById(workId);
        work.setDoneDate(new Date());
        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(work);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();

        }

        return true;
    }


    public boolean setWorkToRejected(int workId) throws WorkDoesNotExistsException, InvalidTitleException, InvalidIdException, InvalidOwnerException, InvalidDescriptionException, InvalidCreationDateException {
        Work work = getWorkById(workId);

        work.setProceedDateToNull();
        work.setWorkerToNull();
        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(work);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();

        }

        return true;
    }
}
