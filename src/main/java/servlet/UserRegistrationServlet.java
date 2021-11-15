package servlet;

import com.google.gson.Gson;
import jdbc.JdbcConnection;
import model.Task;
import model.User;

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

public class UserRegistrationServlet extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp) throws IOException {

        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
//
//        // put received Task from request to AbstractTaskServlet.tasks map
//        Task task = new Task(taskTitle, taskDescription);
//        StartingTasks.tasks.put(taskTitle, task);
        Connection connection = JdbcConnection.getConnection();
        String status = "";
        try {
            Statement statement = connection.createStatement();


            int createResult = statement.executeUpdate("insert into user(login,password) values ('"+ userLogin +"','"+ userPassword+"')");

            if (createResult > 0) {
                status = "Ok";
            } else status = "Error";

        } catch (SQLException e) {
            e.printStackTrace();
        }


        // set received Task to response (JSON)
        String jsonTask = this.gson.toJson(status);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonTask);
        out.flush();
        }
    }