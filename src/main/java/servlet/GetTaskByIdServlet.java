package servlet;

import com.google.gson.Gson;
import jdbc.JdbcConnection;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet("/GetTaskByIdServlet")
public class GetTaskByIdServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String taskId = req.getParameter("taskId");
        Object userId = req.getSession().getAttribute("userId");
        Task task = new Task();

        if (userId != null) {
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(String.format("select * from task where id = %s and user_id = %d ",
                        taskId, (int) userId));

                while (resultSet.next()) {
                    task.setId(resultSet.getInt("id"));
                    task.setTitle(resultSet.getString("title"));
                    task.setDescription(resultSet.getString("description"));
                }
                req.setAttribute("task", task);//taskId
                req.getRequestDispatcher("/getTaskById.jsp").forward(req, resp);
            } catch (SQLException | ServletException e) {
                e.printStackTrace();
            }
        }
    }
}