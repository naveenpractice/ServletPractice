import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DB {
    private static DB db;
    Connection con = null;
    private ResultSet rs;
    private PreparedStatement ps = null;

    public DB() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DB getInstance() throws SQLException {
        if (db == null) {
            db = new DB();
        }
        return db;
    }


    public void insert(String table, String[] params, Object[] values) {
        String placeholder = "";
        String parameters = "";
        for (int i = 0; i < params.length; i++) {
            if (parameters.equals("")) {
                parameters += params[i];
                placeholder += "?";
            } else {
                parameters = parameters + "," + params[i];
                placeholder = placeholder + "," + "?";
            }
        }
        try {
            ps = con.prepareStatement("INSERT INTO " + table
                    + "(" + parameters + ") " + "values(" + placeholder + ")");
            ps = bindParams(ps, values);
            ps.execute();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {

            }
        }

    }

    public void update(String table, String[] params, Object[] values, String condition, Object[] conditionvalues) {
        String parameter = "";
        int length = params.length;
        for (String param : params) {
            if (parameter.equals("")) {
                parameter += param + "=?";
            } else {
                parameter = parameter + "," + param + "=?";
            }
        }
        try {
            ps = con.prepareStatement("UPDATE " + table
                    + " SET " + parameter + " WHERE " + condition);
            ps = bindParams(ps, values);
            for (int i = 0; i < conditionvalues.length; i++) {
                String type = conditionvalues[i].getClass().getSimpleName();
                if (type.equals("String")) {
                    ps.setString(length + i + 1, String.valueOf(conditionvalues[i]));
                }
                if (type.equals("Integer")) {
                    ps.setInt(length + i + 1, Integer.parseInt(String.valueOf(conditionvalues[i])));
                }
                if (type.equals("Date")) {
                    ps.setObject(length + +1, conditionvalues[i]);
                }
            }
            ps.executeUpdate();

        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public Map select(String table, String[] params, String condition, Object[] values) {
        Map<String, ArrayList<Object>> map = new LinkedHashMap<>();
        ArrayList<Object> list;
        String parameter = "";
        if (params != null) {
            for (String param : params) {
                if (parameter.equals(""))
                    parameter += param;
                else
                    parameter = parameter + "," + param;
            }
        } else
            parameter = "*";
        try {
            if (condition == null)
                ps = con.prepareStatement("SELECT " + parameter + " FROM " + table);
            else {
                ps = con.prepareStatement("SELECT " + parameter + " FROM " + table + " WHERE " + condition);
                ps = bindParams(ps, values);
            }
            rs = ps.executeQuery();

            if (!rs.next())
                return null;

            rs.beforeFirst();

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String name = rsmd.getColumnName(i);
                list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rs.getString(i));
                }
                rs.beforeFirst();
                map.put(name, list);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (Exception e) {

            }
        }
        return map;
    }

    public PreparedStatement bindParams(PreparedStatement ps, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            String type = values[i].getClass().getSimpleName();
            if (type.equals("String")) {
                ps.setString(i + 1, String.valueOf(values[i]));
            }
            if (type.equals("Integer")) {
                ps.setInt(i + 1, (Integer) (values[i]));
            }
            if (type.equals("Date")) {
                ps.setObject(i + 1, values[i]);
            }
        }
        return ps;
    }

    public void close() throws SQLException {
        this.con.close();
    }

}