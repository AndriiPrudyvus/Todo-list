package servlet;

import com.google.gson.Gson;
import jdbc.JdbcConnection;
import model.Task;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteTaskServlet extends HttpServlet {
    private Gson gson = new Gson();

    //  DELETE /tasks/{title})
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


//        // get task's "title" from request (in Postman send them in params)
        String id = req.getParameter("id");
        Object userId = req.getSession().getAttribute("userId");
        String status = "Error";
        if (userId != null) {
//        // remove received Task from AbstractTaskServlet.tasks map (tasks.remove("RECEIVED_TITLE"))
//        StartingTasks.tasks.remove(title);
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                int deleteResult = statement.executeUpdate("delete FROM task WHERE id = " + id +" and user_id = " + (int) userId);

                if (deleteResult > 0) {
                    status = "Ok";
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // send "OK" in response


        }
        String jsonTask = this.gson.toJson(status);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonTask);
        out.flush();
    }
}