package servlet;

import constanse.ButtonAction;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/GetTaskByIdServlet").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ButtonAction action = ButtonAction.valueOf(req.getParameter("action").toUpperCase()) ;
        // View Edit Delete
        switch (action) {
            case CREATE:
                req.getRequestDispatcher("/CreateTaskServlet").forward(req, resp);
                break;
            case EDIT:
                String taskId = req.getParameter("taskId");
                String title = req.getParameter("title");
                String description = req.getParameter("description");
                Task task = new Task(Integer.parseInt(taskId), title, description);
                req.setAttribute("task",task);
//                req.setAttribute("taskId",taskId);
//                req.setAttribute("title",title);
//                req.setAttribute("description",description);
                req.getRequestDispatcher("/editUpdate.jsp").forward(req, resp);
                break;
            case SAVE:
                req.getRequestDispatcher("/UpdateTaskServlet").forward(req, resp);
                break;
            case DELETE:
                req.getRequestDispatcher("/DeleteTaskServlet").forward(req,resp);
                break;
            default:
        }
    }
}
