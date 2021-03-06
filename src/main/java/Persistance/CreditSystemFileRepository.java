package Persistance;


import Domain.*;
import Interfaces.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

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
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getEmail().equals(email)) {
                userList.get(i).setName(name);
                userList.get(i).setPassword(password);
                break;
            }
        }
        writeToFile(userFileName, userList);

    }

    @Override
    public void addCrewMember(String name, String email) {
        crewMemberList.add(new CrewMember(name, email));
        writeToFile(crewMemberFileName, crewMemberList);
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
    public void updateCrewMember(String name, String email) {
        CrewMember updatedCrewMember = new CrewMember(name, email);
        for (CrewMember c : crewMemberList) {
            if (c.getName().equals(updatedCrewMember.getName())){
                crewMemberList.set(crewMemberList.indexOf(c), updatedCrewMember);
                writeToFile(crewMemberFileName, crewMemberList);
            }
        }
    }

    //For Loops are NICER!
    @Override
    public void removeCrewMember(String email) {
        // THIS METHOD NEEDS NEW IMPLEMENTATION
//        int removeIndex = -1;
//        for (int i = 0; i < crewMemberList.size(); i++) {
//            if (crewMemberList.get(i).getCastCrewId() == id) {
//                removeIndex = i;
//                break;
//            }
//        }
//        if (removeIndex != -1) {
//           crewMemberList.remove(removeIndex);
//        } else {
//            System.out.println("Element not found.");
//        }
//        writeToFile(crewMemberFileName, crewMemberList);

    }

    @Override
    public void addProducer(String name, String email, String password) {
        userList.add(new Producer(name, email, password));
        writeToFile(userFileName, userList);
    }

    @Override
    public void updateProducer(String name, String email, String password) {
        Producer updatedProducer = new Producer(name, email, password);
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getEmail().equals(email)) {
                userList.get(i).setName(name);
                userList.get(i).setPassword(password);
                break;
            }
        }
        writeToFile(userFileName, userList);

    }

    @Override
    public void removeUser(String email) {
        int removeIndex = -1;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            userList.remove(removeIndex);
        } else {
            System.out.println("Element not found.");
        }
        writeToFile(userFileName, userList);

    }

    @Override
    public void addProduction(String title, String owner, Date date, int productionId) {
        productionList.add(new Production(title, owner, date, productionId));
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
    public void updateProduction(String title, String owner, Date date, int productionId) {
        Production updatedProduction = new Production(title, owner, date, productionId);
        for (Production p : productionList){
            if (p.getTitle().equals(title)){
                productionList.set(productionList.indexOf(p), updatedProduction);
                writeToFile(productionFileName, productionList);
            }
        }
    }

    @Override
    public void removeProduction(int id) {
        int removeIndex = -1;
        for (int i = 0; i < productionList.size(); i++) {
            if (productionList.get(i).getProductionId() == id) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            productionList.remove(removeIndex);
        } else {
            System.out.println("Element not found.");
        }
        writeToFile(productionFileName, productionList);
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

