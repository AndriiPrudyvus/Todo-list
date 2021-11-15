package servlet;

import com.google.gson.Gson;
import jdbc.JdbcConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTaskServlet extends HttpServlet {
    private Gson gson = new Gson();
    // POST /tasks
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        // get task's "title" and "description" from request (in Postman send them in body params)
        String taskTitle = req.getParameter("title");
        String taskDescription = req.getParameter("description");
        Object userId = req.getSession().getAttribute("userId");
        String status = "Error";
        if (userId != null) {
//
//        // put received Task from request to AbstractTaskServlet.tasks map
//        Task task = new Task(taskTitle, taskDescription);
//        StartingTasks.tasks.put(taskTitle, task);
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();


                int createResult = statement.executeUpdate("insert into task(title,description,user_id) values ('" + taskTitle + "','" + taskDescription + "', '" + (int) userId + "')");

                if (createResult > 0) {
                    status = "Ok";
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        // set received Task to response (JSON)
        String jsonTask = this.gson.toJson(status);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(status);
        out.flush();
    }
}
