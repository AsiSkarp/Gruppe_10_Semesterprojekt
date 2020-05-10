package Data;

import java.sql.SQLException;

public class CrewMemberData {
    public static void addbtnhandler(String name, String email, String role) throws SQLException, ClassNotFoundException {
        String sqlInsert = "insert into CrewMember(name, email, role) values('" + name + "', '" + email + "', '"+role+"');";
        try {
            DatabaseConn.dataExeQuery(sqlInsert);
        } catch (SQLException e) {
            System.out.println("Exception while inserting data" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void updateOnActionName(int castCrewId, String name) throws SQLException, ClassNotFoundException {
        String sql = "update CrewMember set name = '" +name+ "' where id = '" + castCrewId + "' ";
        //String sql = "update CrewMember set email = '" + email + "', name = '" +name+ "' where castCrewId = '" + castCrewId + "' ";
        try {
            DatabaseConn.dataExeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error! while update info");
            e.printStackTrace();
            throw e;
        }
    }
    public static void updateOnActionEmail(int castCrewId, String email) throws SQLException, ClassNotFoundException {
        String sql = "update CrewMember set email = '" + email + "' where id = '" + castCrewId + "' ";
        try {
            DatabaseConn.dataExeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error! while update info");
            e.printStackTrace();
            throw e;
        }
    }
    public static void updateOnActionRole(int castCrewId, String role) throws SQLException, ClassNotFoundException {
        String sql = "update CrewMember set role = '" + role + "' where id = '" + castCrewId + "' ";

        try {
            DatabaseConn.dataExeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error! while update info");
            e.printStackTrace();
            throw e;
        }
    }
    public static void deletebtnHandler(int castCrewId) throws SQLException, ClassNotFoundException {
        String sql = "delete from CrewMember where id = '" + castCrewId + "'";
        try {
            DatabaseConn.dataExeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error! while delete  data ");
            e.printStackTrace();
            throw e;
        }
    }
}
