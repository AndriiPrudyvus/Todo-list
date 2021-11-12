package servlet;

import jdbc.JdbcConnection;
import model.Task;
import startingdata.StartingTasks;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteTaskServlet extends HttpServlet {
    DataSource ds = null;

   //  DELETE /tasks/{title})
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        // get task's "title" from request (in Postman send them in params)
        String title = req.getParameter("title");


        // remove received Task from AbstractTaskServlet.tasks map (tasks.remove("RECEIVED_TITLE"))
        StartingTasks.tasks.remove(title);

        // send "OK" in response
        PrintWriter out = resp.getWriter();
        out.print("REMOVED");
        out.flush();




//        try (Connection connection = ds.getConnection()) {
//            Statement st = connection.createStatement();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        void setResponseParams(HttpServletResponse resp)
    }
}
