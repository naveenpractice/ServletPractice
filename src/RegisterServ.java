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
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        User user = new User(name, age, email, username, password,type);

        UserdbHandler handler = new UserdbHandler();
        boolean success = handler.saveUser(user);
        if (success) {
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        } else
            out.println("failure");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
