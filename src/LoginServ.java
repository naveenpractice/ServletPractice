import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naveen-pt1475 on 06-02-2017.
 */
@WebServlet(name = "LoginServ")
public class LoginServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<Issue> issues;
        User user;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserdbHandler userhandler = new UserdbHandler();
        IssuedbHandler issuehandler = new IssuedbHandler();
        boolean valid = userhandler.authenticate(username, password);
        if (valid) {
            user = userhandler.fetchUserDetails(username);
            System.out.println(user.getName());
            if (user.getType().equals("Customer"))
                issues = (ArrayList<Issue>) issuehandler.fetchIssues(username);
            else
                issues = (ArrayList<Issue>) issuehandler.fetchIssues(null);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("issues", issues);
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("usertype", user.getType());
            request.getRequestDispatcher("/staff.jsp").forward(request, response);
        } else {
            out.println("failure");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
