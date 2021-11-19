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
import java.util.ArrayList;
import java.util.List;

public class AllTaskServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Task> taskList = new ArrayList<>();
        Object userId = req.getSession().getAttribute("userId");

        if (userId != null) {
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from task where user_id= " + (int) userId);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    taskList.add(new Task(id, title, description));
                }
                ///// ??? 1)add TaskList to Session
//                2) sendRedirect no taskservlet
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
