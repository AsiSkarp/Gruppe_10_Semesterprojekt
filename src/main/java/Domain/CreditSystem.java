package Domain;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CreditSystem implements Persistance, Serializable{

    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Production> productionList = new ArrayList<>();
    ArrayList<CrewMember> crewMemberList = new ArrayList<>();
    ArrayList<ArrayList> creditSystemList = new ArrayList<ArrayList>(Arrays.asList(userList,productionList,crewMemberList));
    String directory = System.getProperty("user.home");
    String fileName = "Credit_System.dat";
    String filePath = directory + File.separator + fileName;


    public CreditSystem() {
    }

    //Creates a binary file and writes the arraylist to it. NEEDS IF STATEMENT TO AVOID OVERWRITE
    public void writeToFile(){

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            ObjectOutputStream outputStream = new ObjectOutputStream(bufferedOutputStream);
            outputStream.writeObject(creditSystemList);
            outputStream.flush();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //Reads the object from binary file and assigns to the arraylist. FOR LOOP ONLY FOR TESTING
    public void readFromFile(){
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            creditSystemList = (ArrayList<ArrayList>) objectInputStream.readObject();
            for(int i = 0; i < creditSystemList.size(); i++){
                System.out.println(creditSystemList.get(i).toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //TO DO
    @Override
    public void addAdminToSystem(String name, String email, String password, User user) {
        if(user.getIsSuperAdmin() == true) {
            Admin newAdmin = new Admin(name, email, password);
            userList.add(newAdmin);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //NEEDS AUTHENTICATION RESTRICTION
    @Override
    public void removeAdminFromSystem(String name, User user) {
        if(user.getIsSuperAdmin() == true) {
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
    public void addProducerToSystem(String name, String email, String password, User user) {
        if(user.getIsAdmin() == true) {
            Producer newProducer = new Producer(name, email, password);
            userList.add(newProducer);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //NEEDS AUTHENTICATION RESTRICTION
    @Override
    public void removeProducerFromSystem(String name, User user) {
        if(user.getIsAdmin() == true) {
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
    public void addProductionToSystem(String title, int producerId, User user) {
        if(user.getIsProducer() == true) {
            Production newProduction = new Production(title, producerId);
            productionList.add(newProduction);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //NEEDS AUTHENTICATION. NEEDS VALID PRODUCER ID IMPLEMENTATION
    @Override
    public void removeProductionFromSystem(String title, User user) {
        if(user.getIsProducer() == true) {
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
    public void addCrewMember(String name, String email, int castCrewId, User user) {
        if(user.getIsProducer() == true) {
            CrewMember crewMember = new CrewMember(name, email, castCrewId);
            crewMemberList.add(crewMember);
        } else {
            System.out.println("Access Restricted!");
        }
    }

    //TO DO
    @Override
    public void removeCrewMember(String name, int castCrewId, User user) {
    /*   if(user.getIsAdmin() == true) {
       String n = name;
       int c = castCrewId;
            for()
    //    } else {
    //        System.out.println("Access Restricted!");
    //    } */
    }

    //TEST CLASS FOR ACCESS CONTROL
    public void accessRestriction(User user){
        if(user.getIsAdmin() == true) {
            System.out.println(user.getName() + " is an admin and may execute this command!");
        } else {
            System.out.println(user.getName() + " is not an admin. Access restricted.");
        }
    }

    //TEST CLASS FOR ARRAYLISTS
    public void printList(){
        System.out.println(creditSystemList.toString());
    }
}
