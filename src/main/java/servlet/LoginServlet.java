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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String userLogin = request.getParameter("login");
        String passwordFromReq = request.getParameter("password");
        String passwordFromDb = "";
        int userId = 0;
        try {
            ResultSet resultSet = SqlUtils.doSelect(String.format("select * from user where login = '%s'", userLogin));
            while (resultSet.next()) {
                passwordFromDb = resultSet.getString("password");
                userId = resultSet.getInt("id");
            }
            if (passwordFromReq.equals(passwordFromDb) && userId > 0) {
                goToAllTasks(request, response, userId);
            } else {
                goToLoginPage(request, response, userLogin);
            }
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void goToLoginPage(HttpServletRequest request, HttpServletResponse response, String userLogin) throws ServletException, IOException {
        request.getSession().setAttribute("errorMessage", "Sorry, username or password error!");
        request.getSession().setAttribute("NameJsp", userLogin);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private void goToAllTasks(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
        request.getSession().setAttribute("userId", userId);
        request.getRequestDispatcher("/AllTaskServlet").forward(request, response);
    }
}
