package servlet;

import utils.SqlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateTaskServlet")
public class CreateTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String taskTitle = req.getParameter("title");
        String taskDescription = req.getParameter("description");
        Object userId = req.getSession().getAttribute("userId");

        if (userId != null) {
            try {
                SqlUtils.doUpdate(String.format("insert into task(title,description,user_id) values ('%s', '%s', %d)",
                        taskTitle, taskDescription, (int) userId));
                req.getRequestDispatcher("/AllTaskServlet").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }

        }

    }
}
