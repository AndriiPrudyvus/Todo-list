package servlet;

import utils.SqlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("taskId");
        Object userId = req.getSession().getAttribute("userId");

        if (userId != null) {
            try {
                SqlUtils.doUpdate(String.format("delete FROM task WHERE id = %s and user_id = %d",
                        id, (int) userId));
                req.getRequestDispatcher("/AllTaskServlet").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}