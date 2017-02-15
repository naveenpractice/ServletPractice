import javafx.beans.binding.IntegerBinding;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        PrintWriter out = response.getWriter();
        List<Issue> issues;
        Conversation conv = new Conversation();
        Issue issue = new Issue();

        String type = request.getParameter("type");
        String title = request.getParameter("title");
        String priority = request.getParameter("priority");
        String product = request.getParameter("product");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        switch(priority){
            case "low": c.add(Calendar.DATE, 7); break;
            case "medium": c.add(Calendar.DATE, 3); break;
            case "high": c.add(Calendar.DATE, 1); break;
        }

        issue.setDue_time(c.getTime());
        issue.setTitle(title);
        issue.setType(type);
        issue.setProduct(product);
        issue.setPriority(priority);
        issue.setUser(request.getSession().getAttribute("username").toString());

        IssuedbHandler handler = new IssuedbHandler();
        String auto = handler.getAutoIncrementData();
        boolean success = handler.storeIssue(issue);

        conv.setId(Integer.parseInt(auto));
        conv.setMessage(request.getParameter("description"));
        conv.setUser(request.getSession().getAttribute("username").toString());

        ConvodbHandler convodbHandler = new ConvodbHandler();
        convodbHandler.addMessage(conv);

        if (success) {
            if (request.getSession().getAttribute("usertype").equals("Customer"))
                issues = (ArrayList<Issue>) handler.fetchIssues(request.getSession().getAttribute("username").toString());
            else
                issues = (ArrayList<Issue>) handler.fetchIssues(null);
            request.getSession().setAttribute("issues", issues);
            response.sendRedirect("/staff.jsp");
        } else
            out.print("failed");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
