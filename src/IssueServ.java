import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by naveen-pt1475 on 07-02-2017.
 */
@WebServlet(name = "IssueServ")
public class IssueServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        Issue issue = new Issue();

        String type = request.getParameter("type");
        String title = request.getParameter("title");
        String priority = request.getParameter("priority");
        String product = request.getParameter("product");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        switch (priority) {
            case "low":
                c.add(Calendar.DATE, 7);
                break;
            case "medium":
                c.add(Calendar.DATE, 3);
                break;
            case "high":
                c.add(Calendar.DATE, 1);
                break;
        }
        issue.setDue_time(c.getTime());
        issue.setTitle(title);
        issue.setType(type);
        issue.setProduct(product);
        issue.setPriority(priority);
        issue.setUser(session.getAttribute("username").toString());

        IssuedbHandler handler = new IssuedbHandler();
        String auto = handler.getAutoIncrementData();
        boolean success = handler.storeIssue(issue);
        Conversation conv = new Conversation(Integer.parseInt(auto), request.getParameter("description"),
                session.getAttribute("username").toString());
        new ConvodbHandler().addMessage(conv);

        String json = "{\"success\":\"" + success + "\"}";
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
