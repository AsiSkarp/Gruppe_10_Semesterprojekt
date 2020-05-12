package Interfaces;

import Domain.Production;

import java.util.ArrayList;
import java.util.Date;

public interface ProductionInterface {
    //Create
    void addProduction(String title, String owner, Date date, int productionId);

    //Read
    ArrayList<Production> getProductionList();

    //Update
    void updateProduction(String title, String owner, Date date, int productionId);

    //Delete
    void removeProduction(int id);

}
