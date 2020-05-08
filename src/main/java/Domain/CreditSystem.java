package Domain;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import Persistance.CreditSystemFileRepository;

public class CreditSystem implements Serializable {


    //Dummy user to avoid null exeption
    private User currentUser;

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

    public User getCurrentUser() { return this.currentUser; }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    public void addAdminToSystem(String name, String email, String password) {
        if(currentUser.getIsSuperAdmin()) {
            CreditSystemFileRepository.getCsfio().addAdmin(name, email, password);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //NEEDS AUTHENTICATION RESTRICTION

    public void removeAdminFromSystem(String name) {
        if(currentUser.getIsSuperAdmin()) {
            CreditSystemFileRepository.getCsfio().removeUser(name);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    public void addProducerToSystem(String name, String email, String password) {
        if(currentUser.getIsAdmin()) {
            CreditSystemFileRepository.getCsfio().addProducer(name, email, password);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //NEEDS AUTHENTICATION RESTRICTION

    public void removeProducerFromSystem(String name) {
        if(currentUser.getIsAdmin()) {
            CreditSystemFileRepository.getCsfio().removeUser(name);
        } else {
            System.out.println("Access Restricted!");
        }
    }


    public void addProductionToSystem(String title, int producerId) {
        if(currentUser.getIsProducer()) {
            CreditSystemFileRepository.getCsfio().addProduction(title,producerId);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //NEEDS AUTHENTICATION. NEEDS VALID PRODUCER ID IMPLEMENTATION

    public void removeProductionFromSystem(String title) {
        if(currentUser.getIsProducer()) {
            CreditSystemFileRepository.getCsfio().removeProduction(title);
        } else {
            System.out.println("Access Restricted!");
        }
    }


    public void addCrewMember(String name, String email, int castCrewId) {
        if(currentUser.getIsProducer()) {
            CreditSystemFileRepository.getCsfio().addCrewMember(name, email,castCrewId);
        } else {
            System.out.println("Access Restricted!");
        }
    }


    public void removeCrewMember(int id) {
        if (currentUser.getIsProducer()) {
            CreditSystemFileRepository.getCsfio().removeCrewMember(id);

        } else {
            System.out.println("Access Restricted");
        }
    }

    public void updateCrewMember(String name, String email, int id) {
        if(currentUser.getIsProducer()) {
            CreditSystemFileRepository.getCsfio().updateCrewMember(name, email, id);
        } else {
            System.out.println("Access Restricted");
        }
    }

    public ArrayList<CrewMember> getCrewMembers(){
        return CreditSystemFileRepository.getCsfio().getCrewMemberList();
    }

    public void addSuperAdmin(String name, String email, String password) {
        CreditSystemFileRepository.getCsfio().addSuperAdmin(name, email, password);
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
        return CreditSystemFileRepository.getCsfio().getProductionList();
    }


    public ArrayList<CrewMember> getCrewMemberList() {
        return CreditSystemFileRepository.getCsfio().getCrewMemberList();
    }

    public ArrayList<User> getUserList(){
        return CreditSystemFileRepository.getCsfio().getUserList();
    }


    public void logout() {
        currentUser = null;
    }
}
