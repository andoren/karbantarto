package hu.otemplom.karbantarto.dao.mysql;

import hu.otemplom.karbantarto.dao.UserDao;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository("mysqlUserDao")
public class  MysqlUserDataAccessDao implements UserDao {

    protected SessionFactory sessionFactory;
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    public void setup() {

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void exit() {
        sessionFactory.close();
    }

    @Override
    public int addUser(User user) throws DuplicateUserException, InvalidIdException {
        return 0;
    }

    @Override
    public boolean modifyUser(User user) throws UserDoesNotExistsException {
        return false;
    }

    @Override
    public boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException {
        return false;
    }

    @Override
    public Collection<User> getAllUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByUserId(int userId) throws UserDoesNotExistsException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class,userId);
        session.close();
        return user;
    }

    @Override
    public User login(String username, String password) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user ;
        user = (User) session.createQuery("FROM User U WHERE U.username = :userName and U.password = :passWord" ).setParameter("userName", username).setParameter("passWord",password)
                .uniqueResult();

        if (user != null) {
            session.close();
            return user;
        }
        session.close();
        return new User();
    }
}
