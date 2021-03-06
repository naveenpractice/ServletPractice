import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by naveen-pt1475 on 06-02-2017.
 */
@WebServlet(name = "RegisterServ")
public class RegisterServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String type = request.getParameter("type");

        User user = new User(name, age, email, username, password, type);
        boolean success = new UserdbHandler().saveUser(user);
        String json = "{\"success\":\"" + success + "\"}";
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
