package servlet;

import com.google.gson.Gson;
import model.Task;
import startingdata.StartingTasks;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetTaskByIdServlet extends HttpServlet {
    private Gson gson = new Gson();
    // GET /tasks/{title})
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // get task's "title" from request (in Postman send it in params) req.getParameter())
        String taskTitle = req.getParameter("title");

        // get needed Task by "title" from previous step from AbstractTaskServlet.tasks map (tasks.get("RECEIVED_TITLE"))

        Task currentTask = StartingTasks.tasks.get(taskTitle);

        // set Task to response (JSON)
        String jsonTask = this.gson.toJson(currentTask);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonTask);
        out.flush();

    }
}
