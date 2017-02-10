
import java.util.*;

/**
 * Created by naveen-pt1475 on 07-02-2017.
 */
public class IssuedbHandler {
    DB db = null;
    List<Issue> issues = new ArrayList<>();

    public boolean storeIssue(Issue issue) {
        try {
            db = DB.getInstance();
            String[] params = new String[]{"type", "issue_title", "issue_description", "issue_time", "username", "priority"};
            Object[] values = new Object[]{issue.getType(), issue.getTitle(), issue.getDescription(), new Date(), issue.getUser(), issue.getPriority()};
            db.insert("issues", params, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object fetchIssues(String username) {
        Issue issue;
        try {
            Map<String, ArrayList<Object>> map;
            Map.Entry me;
            db = DB.getInstance();
            if (username != null)
                map = db.select("issues", null, "username=?", new Object[]{username});
            else
                map = db.select("issues", null, null, null);
            int size = map.get("username").size();
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                issue = new Issue();
                issue.setId(map.get("id").get(i).toString());
                issue.setUser(map.get("username").get(i).toString());
                issue.setType(map.get("type").get(i).toString());
                issue.setTitle(map.get("issue_title").get(i).toString());
                issue.setDescription(map.get("issue_description").get(i).toString());
                issue.setIssuetime(map.get("issue_time").get(i).toString());
                if(map.get("priority").get(i)!=null)
                issue.setPriority(map.get("priority").get(i).toString());
                if(map.get("resolved").get(i)!=null)
                issue.setResolved(map.get("resolved").get(i).toString());
                if(map.get("solution").get(i)!=null)
                issue.setResponse(map.get("solution").get(i).toString());
                if(map.get("resolvetime").get(i)!=null)
                issue.setResponsetime(map.get("resolvetime").get(i).toString());
                issues.add(issue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return issues;
    }


    public boolean setSolution(Issue issue) {
        try {
            db = DB.getInstance();
            String[] params = new String[]{"solution", "resolvetime", "resolved"};
            Object[] values = new Object[]{issue.getResponse(), new Date(), "yes"};
            db.update("issues", params, values, "id=?", new Object[]{issue.getId()});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
