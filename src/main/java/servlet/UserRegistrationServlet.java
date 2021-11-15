package servlet;

import com.google.gson.Gson;
import jdbc.JdbcConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRegistrationServlet extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp) throws IOException {
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
        Connection connection = JdbcConnection.getConnection();
        String status = "Error";
        try {
            Statement statement = connection.createStatement();
            int createResult = statement.executeUpdate("insert into user(login,password) values ('" + userLogin + "','" + userPassword + "')");

            if (createResult > 0) status = "Ok";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String jsonTask = this.gson.toJson(status);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonTask);
        out.flush();
    }
}