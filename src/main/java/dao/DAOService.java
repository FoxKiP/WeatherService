package dao;

import model.Forecast;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class DAOService implements Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(DAOService.class);
    private static DAOService instance;
    private Storage storage = new Storage();

    private DAOService() {
    }

    public static Service getInstance() {
        if(instance == null) instance = new DAOService();
        return instance;
    }

    @Override
    public Forecast getForecast(String request, String resource) {
        Forecast forecast = null;
        try(Session session = storage.openSession()) {
            if(session != null) {
                LocalDateTime date = LocalDateTime.now().minusHours(1);
                String queryForm = "FROM Forecast WHERE date > '%s' AND request = '%s' AND resource = '%s' ORDER BY id DESC";
                Query<Forecast> query = session.createQuery(String.format(queryForm, date, request, resource));
                query.setMaxResults(1);
                if (!query.list().isEmpty()) {
                    forecast = query.list().get(0);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return forecast;
    }

    @Override
    public void addForecast(Forecast forecast) {
        try(Session session = storage.openSession()) {
            if(session != null) {
                Transaction transaction = session.beginTransaction();
                forecast.setDate(LocalDateTime.now().withNano(0));
                session.save(forecast);
                transaction.commit();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
