import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by naveen-pt1475 on 16-02-2017.
 */
@WebServlet(name = "DetailsServ")
public class DetailsServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        List<Issue> issues;
        String username = request.getSession().getAttribute("username").toString();
        String type = new UserdbHandler().fetchUserDetails(username).getType();
        IssuedbHandler issuedbHandler = new IssuedbHandler();
        if(type.equals("Staff")){
            issues = (List<Issue>) issuedbHandler.fetchIssues(null);
        } else
            issues = (List<Issue>) issuedbHandler.fetchIssues(username);
        request.getSession().setAttribute("usertype" , type);

        Map map = new HashMap();
        map.put("issues",issues);
        map.put("userType" , type);

        response.getWriter().write(new Gson().toJson(map));
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String id = request.getParameter("id");
        request.getSession().setAttribute("id" , id);
        response.getWriter().write("{\"id\":\"" + id +"\"}");
    }
}
