package servlet;

import model.Task;
import utils.SqlUtils;
import utils.TaskUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AllTaskServlet")
public class AllTaskServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Task> taskList = new ArrayList<>();
        Map<String, Task> taskMap = new HashMap<>();
        Object userId = req.getSession().getAttribute("userId");

        if (userId != null) {
            try {
                ResultSet resultSet = SqlUtils.doSelect("select * from task where user_id= " + (int) userId);
                while (resultSet.next()) {
                    taskList.add(TaskUtils.getTaskFromResultSet(resultSet));
                }
                req.setAttribute("taskList",taskList);
                req.setAttribute("taskMap",taskMap);
                req.getRequestDispatcher("/allTask.jsp").forward(req, resp);
            } catch (SQLException | ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
