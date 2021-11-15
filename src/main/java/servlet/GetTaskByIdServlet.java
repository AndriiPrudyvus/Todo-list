package servlet;

import com.google.gson.Gson;
import jdbc.JdbcConnection;
import model.Task;
import startingdata.StartingTasks;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetTaskByIdServlet extends HttpServlet {
    private Gson gson = new Gson();

    // GET /tasks/{title})
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        // get task's "title" from request (in Postman send it in params) req.getParameter())
        String taskId = req.getParameter("id");
        Object userId = req.getSession().getAttribute("userId");
        Task task = new Task();
        if (userId != null) {

            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from task where id = " + taskId + " and user_id ="+ (int) userId );
                while (resultSet.next()) {
                    task.setId(resultSet.getInt("id"));
                    task.setTitle(resultSet.getString("title"));
                    task.setDescription(resultSet.getString("description"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // set Task to response (JSON)
        String jsonTask = this.gson.toJson(task);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonTask);
        out.flush();
    }

}