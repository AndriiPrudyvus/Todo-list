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

@WebServlet("/DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("taskId");
        Object userId = req.getSession().getAttribute("userId");

        if (userId != null) {
            Connection connection = JdbcConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(String.format("delete FROM task WHERE id = %s and user_id = %d",
                        id,(int) userId));
                req.getRequestDispatcher("/AllTaskServlet").forward(req,resp);
            } catch (SQLException | ServletException e) {
                e.printStackTrace();
            }
        }
    }
}