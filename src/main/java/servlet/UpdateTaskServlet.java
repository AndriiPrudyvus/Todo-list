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


@WebServlet("/UpdateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idTask = req.getParameter("taskId");
        String taskTitle = req.getParameter("title");
        String taskDescription = req.getParameter("description");
        Object userId = req.getSession().getAttribute("userId");
        Task task = new Task();

        if (userId != null) {
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                String sqlUpdate = "update task set title = '%s', description = '%s' where id = '%s' and user_id = %d";
                int updateResult = statement.executeUpdate(String.format(sqlUpdate,
                        taskTitle, taskDescription, idTask, (int) userId));
                ResultSet resultSet =
                        statement.executeQuery(String.format("select * from task where id = '%s' and user_id = %d",idTask, (int) userId));

                if (updateResult > 0) {
                    while (resultSet.next()) {
                        task.setId(resultSet.getInt("id"));
                        task.setTitle(resultSet.getString("title"));
                        task.setDescription(resultSet.getString("description"));
                    }
                }
                req.getRequestDispatcher("/AllTaskServlet").forward(req, resp);
            } catch (SQLException | ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
