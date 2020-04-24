package Domain;

import Interfaces.Persistance;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import Persistance.CreditSystemFileIO;

public class CreditSystem implements Persistance, Serializable {

    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Production> productionList = new ArrayList<>();
    ArrayList<CrewMember> crewMemberList = new ArrayList<>();
    ArrayList<ArrayList> creditSystemList = new ArrayList<ArrayList>(Arrays.asList(userList,productionList,crewMemberList));

    //Dummy user to avoid null exeption
    User currentUser = new SuperAdmin("john", "lort", "123");


    //Reference to single instance of CreditSystem class
    private static CreditSystem creditSystem = null;

    //Creates private contstructor for Singleton Pattern
    private CreditSystem() {
    }

    //Creates new instance of CreditSystem. If an instance exists, that single instance is returned.
    public static CreditSystem getCreditSystem(){
        if(creditSystem == null){
            creditSystem = new CreditSystem();
        }
        return creditSystem;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    //Creates a binary file and writes the arraylist to it. NEEDS IF STATEMENT TO AVOID OVERWRITE
    public void writeToPersistance(){

        CreditSystemFileIO.getCsfio().writeData(creditSystemList);
    }

    //Reads the object from binary file and assigns to the arraylist. FOR LOOP ONLY FOR TESTING
    public ArrayList<ArrayList> readFromPersistance(){
//        creditSystemList.clear();
        creditSystemList = CreditSystemFileIO.getCsfio().readData();
//        System.out.println(creditSystemList);
        return creditSystemList;
    }

    //TO DO
    @Override
    public void addAdminToSystem(String name, String email, String password) {
        if(currentUser.getIsSuperAdmin()) {
            setUserList();
            Admin newAdmin = new Admin(name, email, password);
            userList.add(newAdmin);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //NEEDS AUTHENTICATION RESTRICTION
    @Override
    public void removeAdminFromSystem(String name) {
        if(currentUser.getIsSuperAdmin()) {
            for (User u : userList) {
                if (u.getName().equals(name)) {
                    userList.remove(u);
                }
            }
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //THIS METHOD IS REDUNDANT
    @Override
    public void addProducerToSystem(String name, String email, String password) {
        if(currentUser.getIsAdmin()) {
            setUserList();
            Producer newProducer = new Producer(name, email, password);
            userList.add(newProducer);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //NEEDS AUTHENTICATION RESTRICTION
    @Override
    public void removeProducerFromSystem(String name) {
        if(currentUser.getIsAdmin()) {
            for (User u : userList) {
                if (u.getName().equals(name)) {
                    userList.remove(u);
                }
            }
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //TO DO
    @Override
    public void addProductionToSystem(String title, int producerId) {
        if(currentUser.getIsProducer()) {
            setProductionList();
            Production newProduction = new Production(title, producerId);
            productionList.add(newProduction);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //NEEDS AUTHENTICATION. NEEDS VALID PRODUCER ID IMPLEMENTATION
    @Override
    public void removeProductionFromSystem(String title) {
        if(currentUser.getIsProducer()) {
            for (Production production : productionList) {
                if (production.getTitle().equals(title)) {
                    userList.remove(title);
                }
            }
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //TO DO
    @Override
    public void addCrewMember(String name, String email, int castCrewId) {
        if(currentUser.getIsProducer()) {
            setCrewMemberList();
            CrewMember crewMember = new CrewMember(name, email, castCrewId);
            crewMemberList.add(crewMember);
            writeToPersistance();
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //TO DO
    @Override
    public void removeCrewMember(String email) {
        if(currentUser.getIsProducer()) {
            setCrewMemberList();
            for (CrewMember crewMember : crewMemberList) {
                if (crewMember.getEmail().equals(email)) {
                    crewMemberList.remove(crewMember);
                    setCreditSystemList(userList,productionList,crewMemberList);
                    writeToPersistance();
                }
            }
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //TEST CLASS FOR ACCESS CONTROL
    public void accessRestriction(User user){
        if(user.getIsAdmin()) {
            System.out.println(user.getName() + " is an admin and may execute this command!");
        } else {
            System.out.println(user.getName() + " is not an admin. Access restricted.");
        }
    }

    public ArrayList<Production> getProductionList() {
        return productionList;
    }

    //TEST CLASS FOR ARRAYLISTS
    public void printList(){
        System.out.println(creditSystemList.toString());
    }

    public ArrayList<CrewMember> getCrewMemberList() {
        return crewMemberList;
    }

    public void setCreditSystemList(ArrayList<User> userList, ArrayList<Production> productionList, ArrayList<CrewMember>crewMemberList) {
        this.creditSystemList = new ArrayList<ArrayList>(Arrays.asList(userList,productionList,crewMemberList));
    }

    public void setUserList() {
        userList = creditSystemList.get(0);
    }

    public void setProductionList() {
        productionList = creditSystemList.get(1);
    }

    public void setCrewMemberList() {
        crewMemberList = creditSystemList.get(2);
    }

}
