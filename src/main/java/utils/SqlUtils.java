package utils;

import jdbc.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtils {
    public static ResultSet doSelect(String sql) {
        ResultSet resultSet = null;

        try {
            Connection connection = JdbcConnectionPool.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int doUpdate(String sql) {
        int result = 0;

        try {
            Connection connection = JdbcConnectionPool.getConnection();
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
