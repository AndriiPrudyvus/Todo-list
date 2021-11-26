package servlet;

import constanse.ButtonAction;
import utils.TaskUtils;

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
        ButtonAction action = ButtonAction.valueOf(req.getParameter("action").toUpperCase());
        switch (action) {
            case CREATE:
                req.getRequestDispatcher("/CreateTaskServlet").forward(req, resp);
                break;
            case EDIT:
                req.setAttribute("task", TaskUtils.getTaskFromRequest(req));
                req.getRequestDispatcher("/editUpdate.jsp").forward(req, resp);
                break;
            case SAVE:
                req.getRequestDispatcher("/UpdateTaskServlet").forward(req, resp);
                break;
            case DELETE:
                req.getRequestDispatcher("/DeleteTaskServlet").forward(req, resp);
                break;
            default:
        }
    }
}
