import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by naveen-pt1475 on 13-02-2017.
 */
public class ConvodbHandler {
    DB db = null;
    private ArrayList<Object> convos;

    public boolean addMessage(Conversation conv) {
            db = DB.getInstance();
            String[] params = new String[]{"id", "user", "message", "timestamp"};
            Object[] values = new Object[]{conv.getId(), conv.getUser(), conv.getMessage(), new Date()};
            db.insert("conversation", params, values);
            return true;
    }

    public ArrayList<Object> getMessages(int id) {
        Conversation conv;
        int size;
        convos = new ArrayList<>();
        try {
            Map<String, ArrayList<Object>> map;
            db = DB.getInstance();
            System.out.println("CONVERSATION");
            map = db.select("conversation", null, "id=?", new Object[]{String.valueOf(id)}, null);
            System.out.println(map.get("id"));
            size = map.get("user").size();
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                conv = new Conversation();
                conv.setId(Integer.parseInt(map.get("id").get(i).toString()));
                conv.setUser(map.get("user").get(i).toString());
                conv.setMessage(map.get("message").get(i).toString());
                conv.setTimestamp((Date) (map.get("timestamp").get(i)));
                convos.add(conv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convos;
    }
}
