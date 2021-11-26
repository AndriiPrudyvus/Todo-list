package servlet;

import utils.SqlUtils;
import utils.TaskUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;


@WebServlet("/GetTaskByIdServlet")
public class GetTaskByIdServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String taskId = req.getParameter("taskId");
        Object userId = req.getSession().getAttribute("userId");

        if (userId != null) {
            ResultSet resultSet = SqlUtils.doSelect(String.format("select * from task where id = %s and user_id = %d ",
                    taskId, (int) userId));
            try {
                req.setAttribute("task", TaskUtils.fillTaskFields(resultSet));
                req.getRequestDispatcher("/getTaskById.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}