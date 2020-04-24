package Interfaces;

import Domain.CrewMember;
import Domain.Production;
import Domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public interface CreditSystemPersistance {

    public void writeData(ArrayList<ArrayList> arrayLists);

    public ArrayList<ArrayList> readData();

}
