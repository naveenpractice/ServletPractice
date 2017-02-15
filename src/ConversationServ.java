import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by naveen-pt1475 on 14-02-2017.
 */
@WebServlet(name = "ConversationServ")
public class ConversationServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user,type,title,priority,status;
        int id;
        Issue issue = new Issue();
        ArrayList<Object> convos;
        user = request.getParameter("user");
        type = request.getParameter("type");
        title = request.getParameter("title");
        id =  Integer.parseInt(request.getParameter("id"));
        priority = request.getParameter("priority");
        status = request.getParameter("status");
        issue.setUser(user);
        issue.setType(type);
        issue.setTitle(title);;
        issue.setPriority(priority);
        issue.setId(String.valueOf(id));
        issue.setStatus(status);
        request.getSession().setAttribute("issue" , issue);
        request.getSession().setAttribute("issueid" , id);
        ConvodbHandler convodbHandler = new ConvodbHandler();
        convos = convodbHandler.getMessages(id);
        request.getSession().setAttribute("convos" , convos);
        response.sendRedirect("/issuedetails.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
