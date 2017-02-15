import java.io.Serializable;

/**
 * Created by naveen-pt1475 on 07-02-2017.
 */
public class User implements Serializable {

    String name, email, username, password, type;

    public int getIssues_resolved() {
        return issues_resolved;
    }

    public void setIssues_resolved(int issues_resolved) {
        this.issues_resolved = issues_resolved;
    }

    int age,issues_resolved;

    public User() {

    }

    public User(String name, int age, String email, String username, String password, String type) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.age = age;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
