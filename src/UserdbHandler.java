import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by naveen-pt1475 on 07-02-2017.
 */

public class UserdbHandler {
    DB db = null;

    boolean saveUser(User user) {
        db = DB.getInstance();
        String[] params = new String[]{"name", "age", "email", "username", "password", "registeredAt", "user_type"};
        Object[] values = new Object[]{user.getName(), user.getAge(), user.getEmail(), user.getUsername(), user.getPassword(), new Date(), user.getType()};
        db.insert("details", params, values);
        return true;
    }

    boolean authenticate(String username, String password) {
        db = DB.getInstance();
        Map<String, ArrayList<Object>> map;
        map = db.select("details", null, "username=? and password=?", new Object[]{username, password}, null);
        return map != null;
    }

    User fetchUserDetails(String username) {
        User user = null;
        Map<String, ArrayList<Object>> map;
        db = DB.getInstance();
        map = db.select("details", null, "username=?", new Object[]{username}, null);
        if (map != null) {
            user = new User();
            user.setName(map.get("name").get(0).toString());
            user.setAge(Integer.parseInt(map.get("age").get(0).toString()));
            user.setEmail(map.get("email").get(0).toString());
            user.setUsername(map.get("username").get(0).toString());
            user.setType(map.get("user_type").get(0).toString());
        }
        return user;
    }

    ArrayList<Object> fetchUsers(String type) {
        ArrayList<Object> users = new ArrayList<>();
        User user;
        int size;

        Map<String, ArrayList<Object>> map;
        db = DB.getInstance();
        if (type == null)
            map = db.select("details", null, null, null, null);
        else
            map = db.select("details", null, "user_type=?", new Object[]{type}, null);
        if (map != null) {
            size = map.get("name").size();
            for (int i = 0; i < size; i++) {
                user = new User();
                user.setName(map.get("name").get(i).toString());
                user.setAge(Integer.parseInt(map.get("age").get(i).toString()));
                user.setEmail(map.get("email").get(i).toString());
                user.setUsername(map.get("username").get(i).toString());
                user.setType(map.get("user_type").get(i).toString());
                if (map.get("issues_resolved").get(i) != null)
                    user.setIssues_resolved(Integer.parseInt(map.get("issues_resolved").get(i).toString()));
                users.add(user);
            }
        }
        return users;
    }

    public String getAutoIncrementData() {
        db = DB.getInstance();
        return db.getAutoIncrementData("details");
    }

    public void issueResolved(String username) {
        db = DB.getInstance();
        String[] params = new String[]{"issues_resolved = issues_resolved + 1"};
        db.update("details", params, null, "username=?", new Object[]{username});
    }
}
