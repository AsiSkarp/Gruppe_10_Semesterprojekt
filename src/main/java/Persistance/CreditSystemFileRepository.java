package Persistance;


import Domain.*;

import Interfaces.*;

import java.io.*;
import java.util.ArrayList;

public class CreditSystemFileRepository implements Serializable, AdminInterface, CrewMemberInterface, ProducerInterface,
        ProductionInterface, SuperAdminInterface, UserInterface {

    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Production> productionList = new ArrayList<>();
    ArrayList<CrewMember> crewMemberList = new ArrayList<>();
    String userFileName = "User_List.dat";
    String productionFileName = "Production_List.dat";
    String crewMemberFileName = "CrewMember_List.dat";

    //Referance to single instance of CreditSystemFileRepository class
    private static CreditSystemFileRepository csfio = null;

    //Private constructor for Singleton object
    private CreditSystemFileRepository(){
    }

    //Getter method for Singleton Object
    public static CreditSystemFileRepository getCsfio(){
        if(csfio == null){
            csfio = new CreditSystemFileRepository();
        }
        return csfio;
    }

    @Override
    public void addAdmin(String name, String email, String password) {
        userList.add(new Admin(name, email, password));
        writeToFile(userFileName, userList);
    }

    // NOT DONE
    @Override
    public void updateAdmin(String name, String email, String password) {
        Admin updatedAdmin = new Admin(name, email, password);
        for (User u : userList){
            if (u.getName().equals(name)){
                userList.set(userList.indexOf(u), updatedAdmin);
            }
        }
    }

    @Override
    public boolean addCrewMember(String name, String email, int castCrewId) {
        boolean add = true;

        for(CrewMember c : crewMemberList){
            if(c.getCastCrewId() == castCrewId || c.getEmail().equals(email)){
                System.out.println("Email or ID already exist");
                add = false;
                break;
            }
        }

        if(add){
            crewMemberList.add(new CrewMember(name, email, castCrewId));
            writeToFile(crewMemberFileName, crewMemberList);
        }

        return add;
    }

    @Override
    public ArrayList<CrewMember> getCrewMemberList() {
        ArrayList<CrewMember> tempArrayList = new ArrayList<>();
        try {
            File file = new File(crewMemberFileName);
            if (!file.exists()) {
                writeToFile(crewMemberFileName, crewMemberList);
            }
            file.createNewFile();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            tempArrayList = (ArrayList<CrewMember>) objectInputStream.readObject();
            crewMemberList = (ArrayList<CrewMember>) tempArrayList.clone();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempArrayList;
    }

    @Override
    public void updateCrewMember(String name, String email, int castCrewId) {
        for(int i = 0; i < crewMemberList.size(); i++) {
            if(crewMemberList.get(i).getCastCrewId() == castCrewId) {
                crewMemberList.get(i).setName(name);
                crewMemberList.get(i).setEmail(email);
                break;
            }
        }
        writeToFile(crewMemberFileName, crewMemberList);

    }

    @Override
    public void removeCrewMember(int id) {
        int removeIndex = -1;
        for (int i = 0; i < crewMemberList.size(); i++) {
            if (crewMemberList.get(i).getCastCrewId() == id) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            crewMemberList.remove(removeIndex);
        } else {
            System.out.println("Element not found.");
        }
        writeToFile(crewMemberFileName, crewMemberList);

    }

    @Override
    public void addProducer(String name, String email, String password) {
        userList.add(new Producer(name, email, password));
        writeToFile(userFileName, userList);
    }

    @Override
    public void updateProducer(String name, String email, String password) {
        Producer updatedProducer = new Producer(name, email, password);
        for (User u : userList){
            if (u.getName().equals(name)){
                userList.set(userList.indexOf(u), updatedProducer);
                writeToFile(userFileName,userList);
            }
        }
    }

    @Override
    public void removeUser(String name) {
        for (User u : userList){
            if (u.getName().equals(name)){
                userList.remove(userList.indexOf(u));
                writeToFile(userFileName, userList);
            }
        }
    }

    @Override
    public void addProduction(String title, int producerId) {
        productionList.add(new Production(title, producerId));
        writeToFile(productionFileName, productionList);
    }

    @Override
    public ArrayList<Production> getProductionList() {
        ArrayList<Production> tempArrayList = new ArrayList<>();
        try  {
            File file = new File(productionFileName);
            file.createNewFile();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            tempArrayList = (ArrayList<Production>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempArrayList;
    }

    @Override
    public void updateProduction(String title, int producerId) {
        Production updatedProduction = new Production(title, producerId);
        for (Production p : productionList){
            if (p.getTitle().equals(title)){
                productionList.set(productionList.indexOf(p), updatedProduction);
                writeToFile(productionFileName, productionList);
            }
        }
    }

    @Override
    public void removeProduction(String title) {
        for (Production p : productionList){
            if (p.getTitle().equals(title)){
                productionList.remove(productionList.indexOf(p));
                writeToFile(productionFileName, productionList);
            }
        }
    }

    @Override
    public void addSuperAdmin(String name, String email, String password) {
        userList.add(new SuperAdmin(name, email, password));
        writeToFile(userFileName, userList);
    }

    @Override
    public void updateSuperAdmin(String name, String email, String password) {
        SuperAdmin updatedSuperAdmin = new SuperAdmin(name, email, password);
        for (User u : userList){
            if (u.getName().equals(name)){
                userList.set(userList.indexOf(u), updatedSuperAdmin);
            }
        }
    }

    public void writeToFile(String fileName, Object list){

        try {
            File file = new File(fileName);
            file.createNewFile();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, false));
            ObjectOutputStream outputStream = new ObjectOutputStream(bufferedOutputStream);
            outputStream.writeObject(list);
            outputStream.flush();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found.");
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getUserList() {
        ArrayList<User> tempArrayList = new ArrayList<>();

        try {
            File file = new File(userFileName);
            file.createNewFile();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            tempArrayList = (ArrayList<User>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempArrayList;
    }

}

