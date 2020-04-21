package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CreditSystem implements Persistance{

    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Production> productionList = new ArrayList<>();
    ArrayList<CrewMember> crewMemberList = new ArrayList<>();
    ArrayList<ArrayList> creditSystemList = new ArrayList<>();


    public CreditSystem() {
    }

    public void readFromFile(){

    }

    @Override
    public void addAdminToSystem(String name, String email, String password) {
        String newName = name;
        String newEmail = email;
        String newPassword = password;
        User newAdmin = new Admin(newName, newEmail, newPassword);
        userList.add(newAdmin);
    }

    @Override
    public void removeAdminFromSystem(String name) {

    }

    @Override
    public void addProducerToSystem(String name, String email, String password) {
        String newName = name;
        String newEmail = email;
        String newPassword = password;
        User newProducer = new Producer(newName,newEmail,newPassword);
        userList.add(newProducer);
    }

    @Override
    public void removeProducerFromSystem(String name) {

    }

    @Override
    public void addProductionToSystem(String title, ArrayList<CrewMember> productionCrewMList, int producerId) {
        String newTitle = title;
        int newId = producerId;
        ArrayList<CrewMember> crewMemberList = productionCrewMList;
        Production newProduction = new Production(newTitle,crewMemberList,producerId);
        productionList.add(newProduction);
    }

    @Override
    public void removeProductionFromSystem(String title, int producerId) {

    }

    @Override
    public void addCrewMember(String name, String email, int castCrewId) {
        CrewMember crewMember = new CrewMember(name, email, castCrewId);
        crewMemberList.add(crewMember);
//        System.out.println("name" + crewMemberList);
    }

    @Override
    public void removeCrewMember(String name, int castCrewId) {

    }
}
