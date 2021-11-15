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
import java.util.stream.Collectors;

public class AllTaskServlet extends HttpServlet {
    private Gson gson = new Gson();

    // GET /tasks
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // get all values from AbstractTaskServlet.tasks (try to use Java8 StreamApi)
        //List<Task> listValues = StartingTasks.tasks.values().stream()
              //  .collect(Collectors.toList());
        // listValues.forEach(System.out::println);
        // convert all received values to json format (google it or use existing logic from other your classes)


        List<Task> taskList = new ArrayList<>();
        Object userId = req.getSession().getAttribute("userId");
        if (userId != null) {
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from task where user_id= " + (int) userId);
                while (resultSet.next()) {
                    // take id tasks from resultSet
                    int id = resultSet.getInt("id");
                    // take title tasks from resultSet
                    String title = resultSet.getString("title");
                    // take description tasks from resultSet
                    String description = resultSet.getString("description");
                    //    taskList.add(new Task(id, title, description));
                    taskList.add(new Task(id, title, description));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String jsonValues = this.gson.toJson(taskList);
        // set all received values to response (JSON)
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonValues);
        out.flush();


    }
}
