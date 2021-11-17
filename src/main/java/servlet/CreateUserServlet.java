package servlet;

import com.google.gson.Gson;
import jdbc.JdbcConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUserServlet extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
        String status = "Error";
        if (userLogin != null) {
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        String.format("select * from user where login = '%s'", userLogin));
                if (resultSet.next()) {
                    status = "User with this login already exists";
                } else {
                    int createResult = statement.executeUpdate("insert into user(login,password) values ('"
                            + userLogin + "','"
                            + userPassword + "')");
                    if (createResult > 0) {
                        status = "User created";
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String jsonTask = this.gson.toJson(status);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonTask);
        out.flush();
    }
}
