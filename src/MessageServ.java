import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naveen-pt1475 on 08-02-2017.
 */
@WebServlet(name = "MessageServ")
public class MessageServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession session = request.getSession();

        int id = Integer.parseInt(session.getAttribute("id").toString().trim());
        String message = request.getParameter("message");
        String user = session.getAttribute("username").toString();
        Conversation conversation = new Conversation(id, user, message);

        boolean success = new ConvodbHandler().addMessage(conversation);
        String json = "{\"success\":\"" + success + "\"}";
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
