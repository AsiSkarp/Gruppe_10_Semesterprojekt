package Persistance;

import Domain.CrewMember;
import Domain.Production;
import Interfaces.CrewMemberInterface;
import Interfaces.ProductionInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CreditSystemDatabaseRepository implements CrewMemberInterface, ProductionInterface {
    private static CreditSystemDatabaseRepository csdio = null;
    private Connection connection = DatabaseConn.getConnection();

    private CreditSystemDatabaseRepository(){ }

    public static CreditSystemDatabaseRepository getCsdio(){
        if(csdio == null){
            csdio = new CreditSystemDatabaseRepository();
        }
        return csdio;
    }

    //CREWMEMBER METHODS:
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


    public int getCMIdFromDatabse() throws SQLException {
        CrewMember temp = null;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM CrewMember ORDER BY id DESC LIMIT 1");
        while(resultSet.next()) {
            temp = new CrewMember(
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("role"),
                    resultSet.getInt("id"));
        }
        return temp.getCastCrewId();
    }

    //PRODUCTION METHODS:
    @Override
    public void addProduction(String title, String owner, Date date, int productionId) {
        String sql = "insert into Production(title, owner, created) values('" + title + "', '" + owner + "', NOW());";
        connectToDatabase(sql);
    }

    @Override
    public ArrayList<Production> getProductionList() {
        ArrayList<Production> productionList = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from Production");
            while (resultSet.next()) {
                productionList.add(new
                        Production(resultSet.getString("title"),
                        resultSet.getString("owner"),
                        resultSet.getDate("created"),
                        resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productionList;
    }

    @Override
    public void updateProduction(String title, String owner, Date date, int productionId) {
        String sql = "update Production set title = '" + title + "' where id = '" + productionId + "' ";
        connectToDatabase(sql);
        sql = "update Production set owner = '" + owner + "' where id = '" + productionId + "' ";
        connectToDatabase(sql);
    }

    @Override
    public void removeProduction(int id) {
        String sql = "delete from Production where id = '" + id + "'";
        connectToDatabase(sql);
    }

    public int getProductionIdFromDatabase() throws SQLException {
        Production temp = null;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Production ORDER BY id DESC LIMIT 1");
        while(resultSet.next()) {
            temp = new Production(
                    resultSet.getString("title"),
                    resultSet.getString("owner"),
                    resultSet.getDate("created"),
                    resultSet.getInt("id"));
        }
        return temp.getProductionId();
    }

    public Date getProductionDateFromDatabase() throws SQLException {
        Production temp = null;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Production ORDER BY id DESC LIMIT 1");
        while(resultSet.next()) {
            temp = new Production(
                    resultSet.getString("title"),
                    resultSet.getString("owner"),
                    resultSet.getDate("created"),
                    resultSet.getInt("id"));
        }
        return temp.getDate();
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
}