package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Storage {
    private static final Logger LOGGER = LoggerFactory.getLogger(Storage.class);
    private SessionFactory sessionFactory;

    public Storage() {
        try {
            this.sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Session openSession() {
        Session session = null;
        try {
            if (sessionFactory != null) {
                session = sessionFactory.openSession();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return session;
    }
}
