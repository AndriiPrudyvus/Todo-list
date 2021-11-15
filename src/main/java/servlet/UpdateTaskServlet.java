package servlet;

import com.google.gson.Gson;
import jdbc.JdbcConnection;
import model.Task;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTaskServlet extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idTask = req.getParameter("id");
        String taskTitle = req.getParameter("title");
        String taskDescription = req.getParameter("description");
        Object userId = req.getSession().getAttribute("userId");
        Task task = new Task();

        if (userId != null) {
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                int updateResult = statement.executeUpdate("update task set title = '" + taskTitle + "',description= '" + taskDescription + "' where id = " + idTask + " and user_id=" + userId);
                ResultSet resultSet = statement.executeQuery("select * from task where id = " + idTask + " and user_id=" + userId);

                if (updateResult > 0) {
                    while (resultSet.next()) {
                        task.setId(resultSet.getInt("id"));
                        task.setTitle(resultSet.getString("title"));
                        task.setDescription(resultSet.getString("description"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String jsonTask = this.gson.toJson(task);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonTask);
        out.flush();
    }
}
