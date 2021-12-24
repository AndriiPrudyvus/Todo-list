package servlet;

import utils.SqlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
        if (userLogin != null) {
            try {
                if (userLogin.isEmpty() && userPassword.isEmpty()) {
                    goToRegistrationPage(req, resp, "Login or password are missing, must be filled !");
                } else {
                    ResultSet resultSet = SqlUtils.doSelect(
                            String.format("select * from user where login = '%s'", userLogin));
                    if (resultSet.next()) {
                        goToRegistrationPage(req, resp, "User already exist !");
                    } else goToLoginPage(req, resp, userLogin, userPassword);
                }
            } catch (SQLException | ServletException e) {
                e.printStackTrace();
            }
        }
    }

    private void goToLoginPage(HttpServletRequest req, HttpServletResponse resp, String userLogin, String userPassword) throws ServletException, IOException {
        int createResult = SqlUtils.doUpdate(String.format("insert into user(login,password) values ('%s','%s')",
                userLogin, userPassword));
        if (createResult > 0) {
            req.setAttribute("NameJsp", userLogin);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    private void goToRegistrationPage(HttpServletRequest req, HttpServletResponse resp, String status) throws ServletException, IOException {
        req.setAttribute("status", status);
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }
}
