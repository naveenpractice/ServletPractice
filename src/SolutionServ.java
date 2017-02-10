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
@WebServlet(name = "SolutionServ")
public class SolutionServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<Issue> issues;
        Issue issue = new Issue();
        String solution = request.getParameter("solution");
        String id = request.getParameter("id");
        issue.setId(id);
        issue.setResponse(solution);
        IssuedbHandler handler = new IssuedbHandler();
        boolean success = handler.setSolution(issue);
        if (success) {
            issues = (ArrayList<Issue>) handler.fetchIssues(null);
            request.getSession().setAttribute("issues", issues);
            request.getRequestDispatcher("/staff.jsp").forward(request, response);
        } else
            out.println("failure");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
