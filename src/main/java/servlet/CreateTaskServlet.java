package servlet;

import com.google.gson.Gson;
import jdbc.JdbcConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/CreateTaskServlet")
public class CreateTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String taskTitle = req.getParameter("title");
        String taskDescription = req.getParameter("description");
        Object userId = req.getSession().getAttribute("userId");

        if (userId != null) {
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(String.format("insert into task(title,description,user_id) values ('%s', '%s', %d)",
                        taskTitle, taskDescription,(int) userId));
                req.getRequestDispatcher("/AllTaskServlet").forward(req,resp);
            } catch (SQLException | ServletException e) {
                e.printStackTrace();
            }

        }

    }
}
