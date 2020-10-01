package hu.otemplom.karbantarto.dao.mysql;

import hu.otemplom.karbantarto.dao.AreaDao;
import hu.otemplom.karbantarto.model.Area;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaAlreadyExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.AreaDoesNotExistsException;
import hu.otemplom.karbantarto.service.Exceptions.AreaService.InvalidAreaException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository("mysqlAreaDao")
public class MysqlAreaDataAccessDao implements AreaDao {

    @Override
    public int addArea(Area area) throws AreaAlreadyExistsException, InvalidAreaException, InvalidIdException {

        Session session =  SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(area);
        session.getTransaction().commit();
        session.close();
        return area.getId();
    }

    @Override
    public boolean modifyArea(Area area) throws AreaDoesNotExistsException, InvalidAreaException {

        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(area);

        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean deleteAreaById(int areaId) throws AreaDoesNotExistsException {
        Area  area = getAreaById(areaId);
        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(area);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public Area getAreaById(int areaId) throws AreaDoesNotExistsException {

        Session session =SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        Area area = session.get(Area.class,areaId);
        session.getTransaction().commit();
        session.close();

        return area;
    }

    @Override
    public Collection<Area> getAllArea() {

        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Area");
        Collection<Area> areas = query.getResultList();
        session.close();

        return areas;
    }

    @Override
    public Collection<Area> getAreasByUserId(int userId) {

        Session session = SessionSingleton.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery<Area> typedQuery = session.createSQLQuery("select a.id,a.name,boss.id as 'boss' from userareas ua inner join area a on a.id = ua.areaId inner join user boss on a.boss = boss.id where userId = :givenId").addEntity(Area.class);
        typedQuery.setParameter("givenId",userId);
        Collection<Area> areas=  typedQuery.getResultList();
        session.close();

        return areas;

    }
}
