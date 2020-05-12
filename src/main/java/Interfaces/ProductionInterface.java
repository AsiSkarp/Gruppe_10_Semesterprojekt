package Interfaces;

import Domain.Production;

import java.util.ArrayList;

public interface ProductionInterface {
    //Create
    void addProduction(String title, String owner, int productionId);

    //Read
    ArrayList<Production> getProductionList();

    //Update
    void updateProduction(String title, String owner, int productionId);

    //Delete
    void removeProduction(String title);

}
