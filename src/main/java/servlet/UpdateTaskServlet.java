package servlet;

import com.google.gson.Gson;
import model.Task;
import startingdata.StartingTasks;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateTaskServlet extends HttpServlet {
    private Gson gson = new Gson();

    // PUT /tasks/{title})
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        // step 1 - get task's "title" from request (in Postman send it in params)
        String taskTitle = req.getParameter("title");
        String newTaskDescription = req.getParameter("newDescription");
        String newTitle = req.getParameter("newTitle");


        Task task;
        if (newTitle != null) {
            task = new Task(newTitle, newTaskDescription);
            StartingTasks.tasks.remove(taskTitle);
            StartingTasks.tasks.put(newTitle, task);
        } else {
            task = new Task(taskTitle, newTaskDescription);
            StartingTasks.tasks.put(taskTitle, task);
        }
        String jsonTask = this.gson.toJson(task);

        // get needed Task by "title" from previous step from AbstractTaskServlet.tasks map

        // change task's description (task.setDescription("newDescription"))

        // put Task to AbstractTaskServlet.tasks by the same "title" from step 1

        // set Task to response (JSON)

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonTask);
        out.flush();


    }
}
