package Interfaces;



import java.util.ArrayList;


public interface CreditSystemPersistance {

    public void writeData(ArrayList<ArrayList> arrayLists);

    public ArrayList<ArrayList> readData();

}
