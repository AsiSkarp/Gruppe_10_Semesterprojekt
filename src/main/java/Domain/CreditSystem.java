package Domain;

import Persistance.CreditSystemDatabaseRepository;
import Persistance.CreditSystemFileRepository;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CreditSystem implements Serializable {


    //Dummy user to avoid null exeption
    private User currentUser;

    //Reference to single instance of CreditSystem class
    private static CreditSystem creditSystem = null;

    //Creates private contstructor for Singleton Pattern
    private CreditSystem() {
    }

    //Creates new instance of CreditSystem. If an instance exists, that single instance is returned.
    public static CreditSystem getCreditSystem() {
        if(creditSystem == null){
            creditSystem = new CreditSystem();
        }
        return creditSystem;
    }

    public User getCurrentUser() { return this.currentUser; }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addAdminToSystem(String name, String email, String password) {
        if(currentUser.getIsSuperAdmin()) {
            //CreditSystemFileRepository.getCsfio().addAdmin(name, email, password);
            CreditSystemDatabaseRepository.getCsdio().addAdmin(name, email, password);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //NEEDS AUTHENTICATION RESTRICTION

    public void removeAdminFromSystem(String email) {
        if(currentUser.getIsSuperAdmin()) {
            //CreditSystemFileRepository.getCsfio().removeUser(email);
            CreditSystemDatabaseRepository.getCsdio().removeUser(email);
        } else {
            System.out.println("Access Restricted!");
        }
    }
    public void updateAdmin(String name, String email, String password) {
        if(currentUser.getIsSuperAdmin()) {
            //CreditSystemFileRepository.getCsfio().updateAdmin(name, email, password);
            CreditSystemDatabaseRepository.getCsdio().updateAdmin(name, email, password);
        } else {
            System.out.println("Access Restricted");
        }
    }

    public void addProducerToSystem(String name, String email, String password) {
        if(currentUser.getIsAdmin()) {
            //CreditSystemFileRepository.getCsfio().addProducer(name, email, password);
            CreditSystemDatabaseRepository.getCsdio().addProducer(name, email, password);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    public void removeProducerFromSystem(String email) {
        if(currentUser.getIsAdmin()) {
            //CreditSystemFileRepository.getCsfio().removeUser(email);
            CreditSystemDatabaseRepository.getCsdio().removeUser(email);
        } else {
            System.out.println("Access Restricted!");
        }
    }
    public void updateProducer(String name, String email, String password) {
        if(currentUser.getIsAdmin()) {
            //CreditSystemFileRepository.getCsfio().updateProducer(name, email, password);
            CreditSystemDatabaseRepository.getCsdio().updateProducer(name, email, password);
        } else {
            System.out.println("Access Restricted");
        }
    }

    //PRODUCTION METHODS:
    public void addProduction(String title, String owner, Date date, int productionId) {
        if(currentUser.getIsProducer()) {
            //CreditSystemFileRepository.getCsfio().addProduction(title, owner, date, productionId);
            CreditSystemDatabaseRepository.getCsdio().addProduction(title, owner, date, productionId);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    public void removeProductionFromSystem(int id) {
        if(currentUser.getIsProducer()) {
            //CreditSystemFileRepository.getCsfio().removeProduction(id);
            CreditSystemDatabaseRepository.getCsdio().removeProduction(id);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    public void updateProduction(String title, String owner, Date date, int id){
        if(currentUser.getIsProducer()) {
            CreditSystemDatabaseRepository.getCsdio().updateProduction(title, owner, date, id);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    public ArrayList<Production> getProductionList() {
        //return CreditSystemFileRepository.getCsfio().getProductionList();
        return CreditSystemDatabaseRepository.getCsdio().getProductionList();
    }

    public int getProductionIdFromDatabse() throws SQLException {
        return CreditSystemDatabaseRepository.getCsdio().getProductionIdFromDatabase();
    }

    public Date getProductionDateFromDatabase() throws SQLException {
        return  CreditSystemDatabaseRepository.getCsdio().getProductionDateFromDatabase();
    }


    //CREWMEMBER METHODS:
    public void addCrewMember(String name, String email) {
        if(currentUser.getIsProducer()) {
            //CreditSystemFileRepository.getCsfio().addCrewMember(name, email);
            CreditSystemDatabaseRepository.getCsdio().addCrewMember(name, email);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    public void removeCrewMember(String email) {
        if (currentUser.getIsProducer()) {
            //CreditSystemFileRepository.getCsfio().removeCrewMember(id);
            CreditSystemDatabaseRepository.getCsdio().removeCrewMember(email);
        } else {
            System.out.println("Access Restricted");
        }
    }

    public void updateCrewMember(String name, String email) {
        if(currentUser.getIsProducer()) {
            //CreditSystemFileRepository.getCsfio().updateCrewMember(name, email);
            CreditSystemDatabaseRepository.getCsdio().updateCrewMember(name, email);
        } else {
            System.out.println("Access Restricted");
        }
    }

    public int getCrewMemIdFromDatabase() throws SQLException {
        return CreditSystemDatabaseRepository.getCsdio().getCMIdFromDatabse();
    }

    public ArrayList<CrewMember> getCrewMembers() {
        //return CreditSystemFileRepository.getCsfio().getCrewMemberList();
        return CreditSystemDatabaseRepository.getCsdio().getCrewMemberList();
    }

    public void addSuperAdmin(String name, String email, String password) {
        //CreditSystemFileRepository.getCsfio().addSuperAdmin(name, email, password);
        CreditSystemDatabaseRepository.getCsdio().addSuperAdmin(name, email, password);
    }

    public ArrayList<User> getUserList(){
       // return CreditSystemFileRepository.getCsfio().getUserList();
        return CreditSystemDatabaseRepository.getCsdio().getUserList();
    }

    public ArrayList<User> getUserDatabase(){
        return CreditSystemDatabaseRepository.getCsdio().getUserList();
    }

    public void logout() {
        currentUser = null;
    }
}
