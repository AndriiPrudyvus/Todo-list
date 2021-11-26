package servlet;

import jdbc.JdbcConnection;

import javax.servlet.RequestDispatcher;
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
            ResultSet resultSet = statement.executeQuery(String.format("select * from user where login = '%s'", userLogin));
            while (resultSet.next()) {
                passwordFromDb = resultSet.getString("password");
                userId = resultSet.getInt("id");
            }
            if (passwordFromReq.equals(passwordFromDb) && userId > 0) {
                request.getSession().setAttribute("userId", userId);
                request.getRequestDispatcher("/AllTaskServlet").forward(request, response);
            } else {
                request.getSession().setAttribute("errorMessage", "Sorry, username or password error!");
                request.getSession().setAttribute("NameJsp", userLogin);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
    }
}
