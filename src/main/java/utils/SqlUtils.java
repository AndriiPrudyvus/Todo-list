package utils;

import jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtils {
    public static ResultSet doSelect(String sql) {
        ResultSet resultSet = null;
        Connection connection = JdbcConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int doUpdate(String sql) {
        int result = 0;
        Connection connection = JdbcConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
