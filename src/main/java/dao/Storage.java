package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

class Storage {
    void execute(String sql) throws SQLException {
        Connection connection = openConnection();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            connection.close();
        }
    }

    ResultSet executeQuery(String sql) throws SQLException {
        ResultSet resultSet = null;
        Connection connection = openConnection();
        if(connection != null) {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            connection.close();
        }
        return resultSet;
    }

    private Connection openConnection() throws SQLException {
        Connection connection = null;
        try {
            InitialContext context = new InitialContext();
            String resourceName = "jdbc/postgres";
            DataSource ds = (DataSource) context.lookup("java:/comp/env/".concat(resourceName));
            context.close();
            if(ds != null) {
                connection = ds.getConnection();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
