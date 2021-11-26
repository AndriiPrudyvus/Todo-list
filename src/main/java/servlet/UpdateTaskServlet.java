package servlet;

import utils.SqlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/UpdateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idTask = req.getParameter("taskId");
        String taskTitle = req.getParameter("title");
        String taskDescription = req.getParameter("description");
        Object userId = req.getSession().getAttribute("userId");

        if (userId != null) {
            try {
                String sqlUpdate = "update task set title = '%s', description = '%s' where id = '%s' and user_id = %d";
                SqlUtils.doUpdate(String.format(sqlUpdate,taskTitle, taskDescription, idTask, (int) userId));

                SqlUtils.doSelect(String.format("select * from task where id = '%s' and user_id = %d",
                        idTask, (int) userId));

                req.getRequestDispatcher("/AllTaskServlet").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
