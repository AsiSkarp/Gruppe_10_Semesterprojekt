package Persistance;

import Domain.CrewMember;
import Interfaces.CrewMemberInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditSystemDatabaseRepository implements CrewMemberInterface {
    private static CreditSystemDatabaseRepository csdio = null;
    private Connection connection = DatabaseConn.getConnection();

    private CreditSystemDatabaseRepository(){ }

    public static CreditSystemDatabaseRepository getCsdio(){
        if(csdio == null){
            csdio = new CreditSystemDatabaseRepository();
        }
        return csdio;
    }

    @Override
    public void addCrewMember(String name, String email, String role, int castCrewId) {
        String sql = "insert into CrewMember(name, email, role) values('" + name + "', '" + email + "', '"+role+"');";
        connectToDatabase(sql);

    }

    @Override
    public ArrayList<CrewMember> getCrewMemberList() {
        ArrayList<CrewMember> crewMem = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from CrewMember");
            while (resultSet.next()) {
                crewMem.add(new CrewMember(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return crewMem;
    }

    @Override
    public void updateCrewMember(String name, String email, String role, int castCrewId) {
        String sql = "update CrewMember set role = '" + role + "' where id = '" + castCrewId + "' ";
        connectToDatabase(sql);
        sql = "update CrewMember set name = '" +name+ "' where id = '" + castCrewId + "' ";
        connectToDatabase(sql);
        sql = "update CrewMember set email = '" + email + "' where id = '" + castCrewId + "' ";
        connectToDatabase(sql);

    }

    @Override
    public void removeCrewMember(int id) {
        String sql = "delete from CrewMember where id = '" + id + "'";
        connectToDatabase(sql);
        }


    public void connectToDatabase(String sql) {
        try {
            DatabaseConn.dataExeQuery(sql);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error! while delete  data ");
            e.printStackTrace();
            try {
                throw e;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }

    public int getIdFromDatabase() throws SQLException {
        CrewMember temp = null;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM CrewMember ORDER BY id DESC LIMIT 1");
        while(resultSet.next()) {
            temp = new CrewMember(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("role"), resultSet.getInt("id"));
        }
        return temp.getCastCrewId();
    }
}
