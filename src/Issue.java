import java.io.Serializable;

/**
 * Created by naveen-pt1475 on 07-02-2017.
 */
public class Issue implements Serializable {
    String id,title, type, description, issuetime, resolved, status, user, response, responsetime,priority;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIssuetime() {
        return issuetime;
    }

    public void setIssuetime(String issuetime) {
        this.issuetime = issuetime;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponsetime() {
        return responsetime;
    }

    public void setResponsetime(String responsetime) {
        this.responsetime = responsetime;
    }
}
