package jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcConnectionPool {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/TodoBD";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final BasicDataSource ds = new BasicDataSource();

    private JdbcConnectionPool() {
    }

    static {
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USERNAME);
        ds.setPassword(JDBC_PASSWORD);
        ds.setDriverClassName(JDBC_DRIVER);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}