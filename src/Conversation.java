import java.io.Serializable;
import java.util.Date;

/**
 * Created by naveen-pt1475 on 13-02-2017.
 */
public class Conversation implements Serializable {

    int id;
    String user,message;
    Date timestamp;
    public Conversation(){}
    public Conversation(int id, String user, String message) {
        this.id = id;
        this.user = user;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
