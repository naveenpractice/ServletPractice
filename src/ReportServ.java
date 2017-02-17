import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by naveen-pt1475 on 14-02-2017.
 */
@WebServlet(name = "ReportServ")
public class ReportServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String lowmean = "", midmean = "", highmean = "";
        long totallow = 0, totalmid = 0, totalhigh = 0;
        int lowcount = 0, midcount = 0, highcount = 0, mail = 0, people = 0, desk = 0, sdp = 0, issuecount = 0, resolved = 0;
        Date issuetime, resolvedtime;
        Gson gson = new Gson();
        Map map = new HashMap();
        ArrayList<Object> issues, users;
        IssuedbHandler issuedbHandler = new IssuedbHandler();
        issues = (ArrayList<Object>) issuedbHandler.fetchIssues(null);
        UserdbHandler userdbHandler = new UserdbHandler();
        users = userdbHandler.fetchUsers("Staff");
        for (int i = 0; i < issues.size(); i++) {
            issuetime = ((Issue) issues.get(i)).getIssuetime();
            Calendar c = Calendar.getInstance();
            Calendar c1 = Calendar.getInstance();
            c.setTime(new Date());
            c1.setTime(issuetime);
            c.add(Calendar.DATE, -7);
            int diff = c1.compareTo(c);
            resolvedtime = ((Issue) issues.get(i)).getResolvedtime();
            if (diff == 1) {
                issuecount++;
                if (((Issue) issues.get(i)).getResolved().equals("yes")) {
                    resolved++;
                }
            }

            if (issuetime != null && resolvedtime != null) {
                switch (((Issue) issues.get(i)).getPriority()) {
                    case "low":
                        lowcount++;
                        totallow += Math.abs(resolvedtime.getTime() - issuetime.getTime());
                        break;
                    case "medium":
                        midcount++;
                        totalmid += Math.abs(resolvedtime.getTime() - issuetime.getTime());
                        break;
                    case "high":
                        highcount++;
                        totalhigh += Math.abs(resolvedtime.getTime() - issuetime.getTime());
                        break;
                }

            }
            switch (((Issue) issues.get(i)).getProduct()) {
                case "Zoho Mail":
                    mail++;
                    break;
                case "Zoho People":
                    people++;
                    break;
                case "Zoho Desk":
                    desk++;
                    break;
                case "SDP":
                    sdp++;
                    break;
            }
        }
        if (lowcount != 0)
            lowmean = String.format("%02dhrs %02dmins %02dsec",
                    TimeUnit.MILLISECONDS.toHours(totallow / lowcount),
                    TimeUnit.MILLISECONDS.toMinutes(totallow / lowcount) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totallow / lowcount)),
                    TimeUnit.MILLISECONDS.toSeconds(totallow / lowcount) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totallow / lowcount)));

        if (midcount != 0)
            midmean = String.format("%02dhrs %02dmins %02dsec",
                    TimeUnit.MILLISECONDS.toHours(totalmid / midcount),
                    TimeUnit.MILLISECONDS.toMinutes(totalmid / midcount) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totalmid / midcount)),
                    TimeUnit.MILLISECONDS.toSeconds(totalmid / midcount) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalmid / midcount)));

        if (highcount != 0)
            highmean = String.format("%02dhrs %02dmins %02dsec",
                    TimeUnit.MILLISECONDS.toHours(totalhigh / highcount),
                    TimeUnit.MILLISECONDS.toMinutes(totalhigh / highcount) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totalhigh / highcount)),
                    TimeUnit.MILLISECONDS.toSeconds(totalhigh / highcount) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalhigh / highcount)));
        map.put("lowmean", lowmean);
        map.put("midmean", midmean);
        map.put("highmean", highmean);
        map.put("lowcount", lowcount);
        map.put("midcount", midcount);
        map.put("highcount", highcount);
        map.put("mail", mail);
        map.put("desk", desk);
        map.put("people", people);
        map.put("sdp", sdp);
        map.put("users", users);
        map.put("issues", issues);
        map.put("issuecount", issuecount);
        map.put("resolved", resolved);
        map.put("unresolved", issuecount - resolved);
        System.out.println(gson.toJson(map));
        response.getWriter().write(gson.toJson(map));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
