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

public class CreateTaskServlet extends HttpServlet {
    private Gson gson = new Gson();
    // POST /tasks
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get task's "title" and "description" from request (in Postman send them in body params)
        String taskTitle = req.getParameter("title");
        String taskDescription = req.getParameter("description");

        // put received Task from request to AbstractTaskServlet.tasks map
        Task task = new Task(taskTitle, taskDescription);
        StartingTasks.tasks.put(taskTitle, task);

        // set received Task to response (JSON)
        String jsonTask = this.gson.toJson(task);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonTask);
        out.flush();
    }
}
