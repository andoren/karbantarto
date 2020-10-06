package hu.otemplom.karbantarto.dao.mysql;

import hu.otemplom.karbantarto.dao.UserDao;
import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository("mysqlUserDao")
public class  MysqlUserDataAccessDao implements UserDao {


    @Override
    public int addUser(User user) throws DuplicateUserException, InvalidIdException {

        Session session = SessionSingleton.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    try {
        session.persist(user);
        session.getTransaction().commit();

    }catch (Exception e){
        if(e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException){
            transaction.rollback();
            if(e.getCause().getCause().getMessage().contains("username"))
            throw new DuplicateUserException("username");
            else throw new DuplicateUserException("email");
        }
        e.printStackTrace();
    }finally {
        session.close();

    }
        return user.getId();
    }

    @Override
    public boolean modifyUser(User user) throws UserDoesNotExistsException, DuplicateUserException {

        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.update(user);
            session.getTransaction().commit();

        }catch (Exception e) {
            if (e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                session.getTransaction().rollback();
                if (e.getCause().getCause().getMessage().contains("username"))
                    throw new DuplicateUserException("username");
                else throw new DuplicateUserException("email");
            }
            e.printStackTrace();
        }finally {
            session.close();

        }



        return true;
    }

    @Override
    public boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException {

        User user = getUserByUserId(userId);

        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public Collection<User> getAllUser() {

        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        Collection <User> users = query.getResultList();
        session.close();

        return users;

    }

    @Override
    public User getUserByUserId(int userId) throws UserDoesNotExistsException {

        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class,userId);
        session.close();

        return user;
    }

    @Override
    public User login(String username, String password)  {

        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        User user ;
        user = (User) session.createQuery("FROM User U WHERE U.username = :userName and U.password = :passWord").setParameter("userName", username).setParameter("passWord", password)
                    .uniqueResult();
        session.close();

        return user;
    }

    @Override
    public List<User> getJanitors() {
        return getAllUser().stream().filter(u -> u.getRole() == Role.Janitor).collect(Collectors.toList());
    }


}
