package servlet;

import com.google.gson.Gson;
import jdbc.JdbcConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/RegistrationServlet")
public class CreateUserServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
        String status = "";
        if (userLogin != null) {
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        String.format("select * from user where login = '%s'", userLogin));
                if (resultSet.next()) {
                    status = "User already Exists";
                    req.getSession().setAttribute("status",status);
                    req.getRequestDispatcher("/registration.jsp").forward(req, resp);
                    resp.sendRedirect("/registration.jsp");
                } else {
                    int createResult = statement.executeUpdate("insert into user(login,password) values ('"
                            + userLogin + "','"
                            + userPassword + "')");
                    if (createResult > 0) {
                        req.getSession().setAttribute("NameJsp",userLogin);
                        req.getRequestDispatcher("/login.jsp").forward(req,resp);
                    }
                }
            } catch (SQLException | ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
