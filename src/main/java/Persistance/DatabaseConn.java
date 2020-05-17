package Persistance;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;

public class DatabaseConn {
    private static final String JDBCDriver = "org.postgresql.Driver";
    static Connection connection = null;
    private static final String connectionStr = "jdbc:postgresql://localhost:5432/dbtv2";

    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBCDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Postgresql driver is missing! ");
            e.printStackTrace();
            throw e;
        }
        System.out.println("Postgresql driver is added:");
        System.out.println("The data has been send to the database:");
        try {
            connection = DriverManager.getConnection(connectionStr, "postgres", "Ljotur.sigur123");

        } catch (SQLException e) {
            System.out.println("Connection failed " + e);
            throw e;
        }
    }
    public static void dbDisconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    //insert/delete/update methods
    public static void dataExeQuery(String sqlstmt) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        try {
            dbConnect();
            statement = connection.createStatement();
            statement.executeUpdate(sqlstmt);
        } catch (SQLException e) {
            System.out.println("Query execution problem" + e);
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            dbDisconnect();
        }
    }
    //retrieve data from database
    public static ResultSet dataExecute(String sqlQuery) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            dbConnect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            crs = new CachedRowSetImpl();
        } catch (SQLException e) {
            System.out.println("Error execute operation " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            dbDisconnect();
        }
        return crs;
    }
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/dbtv2",
                    "postgres",
                    "Ljotur.sigur123");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getConnection();
    }
}
