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
 * Created by naveen-pt1475 on 07-02-2017.
 */
@WebServlet(name = "IssueServ")
public class IssueServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<Issue> issues;

        Issue issue = new Issue();
        String type = request.getParameter("type");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String priority = request.getParameter("priority");

        issue.setTitle(title);
        issue.setType(type);
        issue.setPriority(priority);
        issue.setDescription(description);
        issue.setUser(request.getSession().getAttribute("username").toString());

        IssuedbHandler handler = new IssuedbHandler();
        boolean success = handler.storeIssue(issue);
        if (success) {
            if (request.getSession().getAttribute("usertype").equals("Customer"))
                issues = (ArrayList<Issue>) handler.fetchIssues(request.getSession().getAttribute("username").toString());
            else
                issues = (ArrayList<Issue>) handler.fetchIssues(null);
            request.getSession().setAttribute("issues", issues);
            request.getRequestDispatcher("/staff.jsp").forward(request,response);
            out.print("Success");
        } else
            out.print("failed");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
