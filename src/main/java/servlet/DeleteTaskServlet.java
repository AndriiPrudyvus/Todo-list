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

public class DeleteTaskServlet extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Object userId = req.getSession().getAttribute("userId");
        String status = "Error";

        if (userId != null) {
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                int deleteResult = statement.executeUpdate("delete FROM task WHERE id = " + id + " and user_id = " + (int) userId);
                if (deleteResult > 0) {
                    status = "Ok";
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