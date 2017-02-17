import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by naveen-pt1475 on 17-02-2017.
 */
@WebServlet(name = "CloseIssueServ")
public class CloseIssueServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(session.getAttribute("id").toString().trim());
        String user = session.getAttribute("username").toString();
        new UserdbHandler().issueResolved(user);
        boolean success = new IssuedbHandler().resolved(id);
        String json = "{\"success\":\"" + success + "\"}";
        System.out.println(json);
        response.getWriter().write(json);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
