package servlet;

import jdbc.JdbcConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String passwordFromReq = request.getParameter("password");
        String passwordFromDb = "";
        int userId = 0;
        Connection connection = JdbcConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *  from user where login = '" + name + "'");

            while (resultSet.next()) {
                passwordFromDb = resultSet.getString("password");
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (passwordFromReq.equals(passwordFromDb) && userId > 0) {
            out.print("Welcome, " + name);
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
        } else {
            out.print("Sorry, username or password error!");
        }
        out.close();
    }
}
