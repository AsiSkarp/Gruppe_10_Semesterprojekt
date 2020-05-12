package Persistance;

import Domain.Production;
import Interfaces.ProductionInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductionSystemDatabaseRepository implements ProductionInterface {
    private static ProductionSystemDatabaseRepository csdio = null;
    private Connection connection = DatabaseConn.getConnection();

    private ProductionSystemDatabaseRepository() {}

    public static ProductionSystemDatabaseRepository getCsdio(){
        if(csdio == null){
            csdio = new ProductionSystemDatabaseRepository();
        }
        return csdio;
    }

    @Override
    public void addProduction(String title, String owner, int productionId) {
        String sql = "insert into Production(title, owner) values('" + title + "', '" + owner + "');";
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
                        resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productionList;
    }

    @Override
    public void updateProduction(String title, String owner, int productionId) {
        String sql = "update Production set title = '" + title + "' where productionId = '" + productionId + "' ";
        connectToDatabase(sql);
        sql = "update Production set owner = '" + owner + "' where productionId = '" + productionId + "' ";
        connectToDatabase(sql);

    }

    @Override
    public void removeProduction(String title) {
        String sql = "delete from Production where title = '" + title + "'";
        connectToDatabase(sql);
    }

    private void connectToDatabase(String sql) {
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
