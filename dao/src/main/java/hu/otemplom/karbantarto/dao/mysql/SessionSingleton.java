package hu.otemplom.karbantarto.dao.mysql;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionSingleton {
    private static SessionFactory sessionFactory;
    private static Object lockObject = new Object();
    final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            synchronized (lockObject){
                if(sessionFactory == null){
                    setup();

                }
            }
        }
        return sessionFactory;
    }
    private static void setup() {

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void exit() {
        sessionFactory.close();
    }
}
