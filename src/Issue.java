import java.io.Serializable;
import java.util.Date;

/**
 * Created by naveen-pt1475 on 07-02-2017.
 */
public class Issue implements Serializable {
    String id,title, type, resolved, status, user,priority,product;
    Date issuetime,resolvedtime,due_time;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Date getResolvedtime() {
        return resolvedtime;
    }

    public void setResolvedtime(Date resolvedtime) {
        this.resolvedtime = resolvedtime;
    }

    public Date getDue_time() {
        return due_time;
    }

    public void setDue_time(Date due_time) {
        this.due_time = due_time;
    }

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

    public Date getIssuetime() {
        return issuetime;
    }

    public void setIssuetime(Date issuetime) {
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

}
