package servlet;

import jdbc.JdbcConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String userLogin = request.getParameter("login");
        String passwordFromReq = request.getParameter("password");
        String passwordFromDb = "";
        int userId = 0;

        Connection connection = JdbcConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *  from user where login = '" + userLogin + "'");
            while (resultSet.next()) {
                passwordFromDb = resultSet.getString("password");
                userId = resultSet.getInt("id");
            }
            if (passwordFromReq.equals(passwordFromDb) && userId > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                response.sendRedirect("/allTask.jsp");
            } else {
                request.getSession().setAttribute("errorMessage", "Sorry, username or password error!");
                request.getSession().setAttribute("NameJsp",userLogin);
                response.sendRedirect("/login.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
