import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by naveen-pt1475 on 06-02-2017.
 */
@WebServlet(name = "LoginServ")
public class LoginServ extends HttpServlet {

    int count;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Timer timer = new Timer();
        UserdbHandler userhandler = new UserdbHandler();
        IssuedbHandler issuehandler = new IssuedbHandler();
        HttpSession session = request.getSession();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                User user;
                count++;
                List<Issue> issues;
                boolean valid = userhandler.authenticate(username, password);
                if (valid) {
                    user = userhandler.fetchUserDetails(username);
                    if (session.getAttribute("user") == null && count > 3) {
                        System.out.println("SESSION IS NULL");
                        this.cancel();
                    }
                    System.out.println(request.getSession(false));
                    session.setAttribute("user", user);
                    System.out.println(user.getName());
                    if (user.getType().equals("Customer"))
                        issues = (ArrayList<Issue>) issuehandler.fetchIssues(username);
                    else {
                        issues = (ArrayList<Issue>) issuehandler.fetchIssues(null);
                    }
                    session.setAttribute("issues", issues);
                    session.setAttribute("username", username);
                    session.setAttribute("usertype", user.getType());
                    System.out.println("SIZE " + issues.size());
                    System.out.println(count);
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 2000);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/staff.jsp");

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
