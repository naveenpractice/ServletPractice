
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by naveen-pt1475 on 14-02-2017.
 */
@WebServlet(name = "ConversationServ")
public class ConversationServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        int issueId = Integer.parseInt(session.getAttribute("id").toString().trim());
        String userType = session.getAttribute("usertype").toString();
        Issue issue = new IssuedbHandler().fetchIssueDetails(issueId);
        List<Conversation> messages = new ConvodbHandler().getMessages(issueId);
        Map map = new HashMap();
        map.put("issue", issue);
        map.put("messages", messages);
        map.put("userType", userType);
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.put(map);
//        jsonArray.toString();
//        response.getWriter().write(.toJson(map));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
