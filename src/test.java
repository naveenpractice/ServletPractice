import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



/**
 * Created by naveen-pt1475 on 06-02-2017.
 */
public class test {

    public static void main(String[] args) throws SQLException {
        DB db = new DB();
        int size=1;
        Map<String , ArrayList<Object>> map = db.select("details" , null , "age>?" , new Object[]{20},null);
        Set entries  = map.entrySet();
        Iterator iterator;
        for(int i=0;i<size;i++){
        iterator = entries.iterator();
        while(iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            size = ((ArrayList) me.getValue()).size();
            System.out.println(((ArrayList)me.getValue()).get(i));
        }
        }
    }
}
