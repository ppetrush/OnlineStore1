package appModules;

import utility.Constant;
import utility.Log;
import java.sql.*;
import java.util.Map;


public class JavaToMySql {
    // JDBC URL, username and password of MySQL server
    private static final String url = Constant.DBHost;
    private static final String user = Constant.User;
    private static final String password = Constant.Pass;

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet resultSet;

    public static void main(String args[]) {
        String query = "select count(*) from products";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println("Total number of products in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { resultSet.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
    public static void storeToDb (Map<String, String> map,String type, String page,String URL) throws SQLException {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            Log.info("Establish connection to DB -> SUCCESS");
            for (String key:map.keySet()) {
                String sql="insert into products (DATUM,TYPE_OF_ACTION,PAGE,URL,DESCRIPTION,PRICE) values(curdate(),'"+type+"','"+page+"','"+URL+"','"+key+"','"+map.get(key)+"')";
                //System.out.println(sql);
                stmt.executeUpdate(sql);
        }
            Log.info("Data stored to DB");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException con_se) {con_se.printStackTrace();}
            try { stmt.close(); } catch(SQLException stmt_se) {stmt_se.printStackTrace();}
        }
    }
    public static void clearTable (String table) throws SQLException {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            Log.info("Establish connection to DB -> SUCCESS");
            String sql="delete from "+table;
            stmt.executeUpdate(sql);
            Log.info("Data erased in table - "+table);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException con_se) {con_se.printStackTrace();}
            try { stmt.close(); } catch(SQLException stmt_se) {stmt_se.printStackTrace();}
        }
    }
    public static void loadDataToLog () throws SQLException {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            Log.info("Load data from  DB to logfile");
            Log.info("Establish connection to DB -> SUCCESS");
            String sql="select TYPE_OF_ACTION,PAGE,URL,DESCRIPTION,PRICE from products order by TYPE_OF_ACTION";
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                String TYPE_OF_ACTION = resultSet.getString("TYPE_OF_ACTION");
                String PAGE = resultSet.getString("PAGE");
                String URL = resultSet.getString("URL");
                String DESCRIPTION = resultSet.getString("DESCRIPTION");
                String PRICE = resultSet.getString("PRICE");
                String log = TYPE_OF_ACTION+" products on - "+PAGE+" URL - ("+URL+") Name -"+DESCRIPTION+" Price - "+PRICE;
                Log.info(log);
            }
            Log.info("Load data from DB -> SUCCESS");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException con_se) {con_se.printStackTrace();}
            try { stmt.close(); } catch(SQLException stmt_se) {stmt_se.printStackTrace();}
            try { resultSet.close(); } catch(SQLException rs_se) {rs_se.printStackTrace();}
        }
    }
}
