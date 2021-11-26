package utils;

import model.Task;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskUtils {

    public static Task fillTaskFields(ResultSet resultSet) {
        Task task = new Task();
        try {
            while (resultSet.next()) {
                task = getTaskFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public static Task getTaskFromRequest(HttpServletRequest request) {
        String taskId = request.getParameter("taskId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        return new Task(Integer.parseInt(taskId), title, description);
    }

    public static Task getTaskFromResultSet(ResultSet resultSet) {
        Task task = new Task();
        try {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            task = new Task(id, title, description);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }
}
