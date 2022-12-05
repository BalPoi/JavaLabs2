package by.gsu.bal;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnection {
    private static JdbcConnectionPool cp;

    public static void init(String dbUrl, String user, String password) throws SQLException {
        if (cp != null) return;
        cp = JdbcConnectionPool.create(dbUrl, user, password);
    }

    public static Connection getConnection() throws SQLException {
        return cp.getConnection();
    }

}
