package Interfaces;

import Domain.Production;

import java.util.ArrayList;

public interface ProductionInterface {
    //Create
    void addProduction(String title, int producerId);

    //Read
    ArrayList<Production> getProductionList();

    //Update
    void updateProduction(String title, int producerId);

    //Delete
    void removeProduction(String title);

}
