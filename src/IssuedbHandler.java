import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by naveen-pt1475 on 07-02-2017.
 */
public class IssuedbHandler {
    DB db = null;
    List<Issue> issues;

    public boolean storeIssue(Issue issue) {
        db = DB.getInstance();
        String[] params = new String[]{"type", "product", "issue_title", "issue_time", "username", "priority", "duetime", "resolved"};
        Object[] values = new Object[]{issue.getType(), issue.getProduct(), issue.getTitle(), new Date(), issue.getUser(), issue.getPriority(), issue.getDue_time(), "no"};
        db.insert("issues", params, values);
        return true;
    }


    public Object fetchIssues(String username) {
        Issue issue;
        int size;
        issues = new ArrayList<>();

        Map<String, ArrayList<Object>> map;
        db = DB.getInstance();
        if (username != null) {
            map = db.select("issues", null, "username=?", new Object[]{username}, "duetime");
        } else {
            map = db.select("issues", null, null, null, "duetime");
        }
        if (map != null) {
            size = map.get("username").size();
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                issue = new Issue();
                issue.setId(map.get("id").get(i).toString());
                issue.setUser(map.get("username").get(i).toString());
                issue.setType(map.get("type").get(i).toString());
                issue.setProduct(map.get("product").get(i).toString());
                issue.setTitle(map.get("issue_title").get(i).toString());
                issue.setIssuetime((Date) map.get("issue_time").get(i));
                issue.setResolvedtime((Date) map.get("resolvedtime").get(i));
                issue.setDue_time((Date) map.get("duetime").get(i));
                if (map.get("priority").get(i) != null)
                    issue.setPriority(map.get("priority").get(i).toString());
                if (map.get("resolved").get(i) != null)
                    issue.setResolved(map.get("resolved").get(i).toString());
                issues.add(issue);
            }
        }


        return issues;
    }


    public String getAutoIncrementData() {
        db = DB.getInstance();
        return db.getAutoIncrementData("issues");
    }

    public boolean resolved(int id) {
        db = DB.getInstance();
        String[] params = new String[]{"resolved", "resolvedtime"};
        Object[] values = new Object[]{"yes", new Date()};
        db.update("issues", params, values, "id=?", new Object[]{id});
        return true;
    }

    public Issue fetchIssueDetails(int id) {
        Issue issue = null;
        int size;
        issues = new ArrayList<>();
        Map<String, ArrayList<Object>> map;
        db = DB.getInstance();
        map = db.select("issues", null, "id=?", new Object[]{id}, null);
        size = map.get("id").size();
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            issue = new Issue();
            issue.setId(map.get("id").get(i).toString());
            issue.setUser(map.get("username").get(i).toString());
            issue.setType(map.get("type").get(i).toString());
            issue.setTitle(map.get("issue_title").get(i).toString());
            issue.setProduct(map.get("product").get(i).toString());
            issue.setIssuetime((Date) map.get("issue_time").get(i));
            issue.setDue_time((Date) map.get("duetime").get(i));
            if (map.get("priority").get(i) != null)
                issue.setPriority(map.get("priority").get(i).toString());
            if (map.get("resolved").get(i) != null)
                issue.setResolved(map.get("resolved").get(i).toString());
            if (map.get("resolvedtime").get(i) != null)
                issue.setResolvedtime((Date) map.get("resolvedtime").get(i));
        }
        return issue;
    }
}
