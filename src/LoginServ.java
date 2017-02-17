import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by naveen-pt1475 on 06-02-2017.
 */
@WebServlet(name = "LoginServ")
public class LoginServ extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean valid = new UserdbHandler().authenticate(username, password);

        if (valid)
            session.setAttribute("username", username);

        Map map;
        map = new HashMap<>();
        map.put("user", username);
        map.put("pass", password);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(map);
        jsonObject.put("valid", valid);
        System.out.println("JSON" + jsonObject);
        String json = "{\"valid\":\"" + valid + "\"}";
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
