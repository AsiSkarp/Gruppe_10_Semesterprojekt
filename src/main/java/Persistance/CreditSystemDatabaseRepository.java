package Persistance;

import Domain.*;
import Interfaces.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CreditSystemDatabaseRepository implements AdminInterface, CrewMemberInterface, ProductionInterface, ProducerInterface, SuperAdminInterface, UserInterface {
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
    public void addCrewMember(String name, String email) {
        String sql = "insert into CrewMember(name, email) values('" + name + "', '" + email + "');";
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
                        resultSet.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return crewMem;
    }

    @Override
    public void updateCrewMember(String name, String email) {
        String sql = "update CrewMember set name = '" +name+ "' where email = '" + email + "' ";
        connectToDatabase(sql);
        sql = "update CrewMember set email = '" + email + "' where email = '" + email + "' ";
        connectToDatabase(sql);
    }

    @Override
    public void removeCrewMember(String email) {
        String sql = "delete from CrewMember where email = '" + email + "'";
        connectToDatabase(sql);
        }

    //???
    public int getCMIdFromDatabse(String email) throws SQLException {
        int id = 0;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM CrewMember WHERE email = '" + email + "';");
        while(resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
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

    @Override
    public void addProducer(String name, String email, String password) {
        String usertype = "Producer";
        String sql = "insert into Users(name, email, password, usertype) values('" + name + "', '" + email + "', '" + password + "' , '" + usertype + "'); ";
        connectToDatabase(sql);
    }

    @Override
    public void updateProducer(String name, String email, String password) {
        String sql = "update Users set name = '" + name + "' where password = '" + password + "' ";
        connectToDatabase(sql);
        sql = "update Users set email = '" + email + "' where password = '" + password + "' ";
        connectToDatabase(sql);
    }

    @Override
    public void addAdmin(String name, String email, String password) {
        String usertype = "Admin";
        String sql = "insert into Users(name, email, password, usertype) values('" + name + "', '" + email + "', '" + password + "' , '" + usertype + "'); ";
        connectToDatabase(sql);
    }

    @Override
    public void updateAdmin(String name, String email, String password) {
        String sql = "update Users set name = '" + name + "' where password = '" + password + "' ";
        connectToDatabase(sql);
        sql = "update Users set email = '" + email + "' where password = '" + password + "' ";
        connectToDatabase(sql);
    }


    @Override
    public ArrayList<User> getUserList() {
        ArrayList<User> users = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from Users");
            while (resultSet.next()) {
                if (resultSet.getString("usertype").equals("SuperAdmin")) {
                    SuperAdmin tempSuper = (new SuperAdmin(
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password")));
                    tempSuper.setId(resultSet.getInt("id"));
                    users.add(tempSuper);
                } else if (resultSet.getString("usertype").equals("Admin")) {
                    Admin tempAdmin = (new Admin(
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password")));
                    tempAdmin.setId(resultSet.getInt("id"));
                    users.add(tempAdmin);
                } else if (resultSet.getString("usertype").equals("Producer")) {
                    Producer tempProducer = (new Producer(
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password")));
                    tempProducer.setId(resultSet.getInt("id"));
                    users.add(tempProducer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void removeUser(String email) {
        String sql = "delete from Users where email = '" + email + "'";
        connectToDatabase(sql);
    }

    @Override
    public void addSuperAdmin(String name, String email, String password) {
        String usertype = "SuperAdmin";
        String sql = "insert into Users(name, email, password, usertype) values('" + name + "', '" + email + "', '" + password + "' , '" + usertype + "'); ";
        connectToDatabase(sql);
    }

    @Override
    public void updateSuperAdmin(String name, String email, String password) {
    }

    //CREWPRODUCTION METHODS
    public void addCrewToProduction(String name, String email, String role, int productionId) throws SQLException {
        boolean alreadyExist = false;
        boolean roleExist = false;
        ArrayList<CrewMember> cast = getCrewMemberList();
        ArrayList<String> roles = getRoles();

        for(int i = 0; i < cast.size(); i++) {
            if(cast.get(i).getEmail().equalsIgnoreCase(email)){
                alreadyExist = true;
                break;
            }
        }

        if(!alreadyExist) {
            String sql = "insert into CrewMember(name, email) values('" + name + "', '" + email + "');";
            connectToDatabase(sql);
        }

        for(int i = 0; i < roles.size(); i++) {
            if(roles.get(i).equalsIgnoreCase(role)) {
                roleExist = true;
                break;
            }
        }

        if(!roleExist) {
            String sql = "insert into roles(role) values('" + role + "');";
            connectToDatabase(sql);
        }

        int crewMemberId = getCMIdFromDatabse(email);
        int roleId = getRolesId(role);

        String sql = "insert into roletable(crewMemberId, ProductionId, roleId) values(" + crewMemberId + ", " + productionId + ", " + roleId + ");";
        connectToDatabase(sql);
    }

    public ArrayList<CrewProduction> getCrewProduction(int id) {
        ArrayList<CrewProduction> cast = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(
                    "select cm.name, cm.email, pr.title, pr.owner, pr.created, pr.id as production_id, roles.role " +
                            "from roletable, crewmember as cm, production as pr, roles " +
                            "where cm.id = roletable.crewMemberId " +
                            "and roles.id = roletable.roleId " +
                            "and pr.id = roletable.productionId " +
                            "and productionId = " + id);
            while (resultSet.next()) {
                CrewProduction temp = new CrewProduction(
                        new CrewMember(resultSet.getString("name"), resultSet.getString("email")),
                        new Production(resultSet.getString("title"), resultSet.getString("owner"), resultSet.getDate("created"),
                                resultSet.getInt("production_id")),
                        resultSet.getString("role"), resultSet.getString("name"));
                cast.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cast;
    }

    public ArrayList<String> getRoles() {
        ArrayList<String> roleList = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from roles");
            while (resultSet.next()) {
                roleList.add(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }

    public int getRolesId(String role) throws SQLException {
        int id = 0;
        ResultSet resultSet = connection.createStatement().executeQuery("select id from roles where role = '" + role + "';");
        while(resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }
}
