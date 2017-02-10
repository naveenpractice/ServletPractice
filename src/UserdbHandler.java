import java.util.*;

/**
 * Created by naveen-pt1475 on 07-02-2017.
 */

public class UserdbHandler {
    DB db = null;
    boolean saveUser(User user) {
        try {
            db = DB.getInstance();
            String[] params = new String[]{"name", "age", "email", "username", "password", "registeredAt", "user_type"};
            Object[] values = new Object[]{user.getName(), user.getAge(), user.getEmail(), user.getUsername(), user.getPassword(), new Date(), user.getType()};
            db.insert("details", params, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    boolean authenticate(String username, String password) {
        try {
            db = DB.getInstance();
            System.out.println("Authenticate: " + db.con.toString());
            Map<String, ArrayList<Object>> map;
            map = db.select("details", null, "username=? and password=?", new Object[]{username, password});
            return map != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    User fetchUserDetails(String username) {
        User user = null;
        try {
            Map<String, ArrayList<Object>> map;
            db = DB.getInstance();
            map = db.select("details", null, "username=?", new Object[]{username});
            if(map!=null){
                user = new User();
                user.setName(map.get("name").get(0).toString());
                user.setAge(Integer.parseInt(map.get("age").get(0).toString()));
                user.setEmail(map.get("email").get(0).toString());
                user.setUsername(map.get("username").get(0).toString());
                user.setType(map.get("user_type").get(0).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


}
