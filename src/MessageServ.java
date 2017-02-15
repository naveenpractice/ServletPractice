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
 * Created by naveen-pt1475 on 08-02-2017.
 */
@WebServlet(name = "MessageServ")
public class MessageServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Issue> issues;
        ArrayList<Object> convos;
        Conversation conv = new Conversation();
        Issue issue = new Issue();

        String id = request.getSession().getAttribute("issueid").toString();
        issue.setId(id);

        IssuedbHandler handler = new IssuedbHandler();
        issues = (ArrayList<Issue>) handler.fetchIssues(null);

        conv.setId(Integer.parseInt(id));
        conv.setMessage(request.getParameter("solution"));
        conv.setUser(request.getSession().getAttribute("username").toString());
        ConvodbHandler convodbHandler = new ConvodbHandler();
        convodbHandler.addMessage(conv);

        convos = convodbHandler.getMessages(Integer.parseInt(id));
        request.getSession().setAttribute("convos", convos);
        request.getSession().setAttribute("issues", issues);
        request.getRequestDispatcher("/issuedetails.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Issue issue;
        int id = (Integer) request.getSession().getAttribute("issueid");

        UserdbHandler userhandler = new UserdbHandler();
        userhandler.issueResolved(request.getSession().getAttribute("username").toString());

        IssuedbHandler handler = new IssuedbHandler();
        boolean success = handler.resolved(id);
        if(success){
            issue =  handler.fetchIssueDetails(id);
            request.getSession().setAttribute("issue", issue);
            request.getSession().setAttribute("issues", handler.fetchIssues(null));
            request.getRequestDispatcher("/issuedetails.jsp").forward(request, response);
        }
    }
}
