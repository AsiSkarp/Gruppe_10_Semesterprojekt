package Persistance;

import Domain.CrewMember;
import Domain.Production;
import Domain.User;
import Interfaces.CreditSystemPersistance;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CreditSystemFileIO implements CreditSystemPersistance {

    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Production> productionList = new ArrayList<>();
    ArrayList<CrewMember> crewMemberList = new ArrayList<>();
    ArrayList<ArrayList> creditSystemList = new ArrayList<ArrayList>(Arrays.asList(userList,productionList,crewMemberList));
    String directory = System.getProperty("user.home");
    String fileName = "Credit_System.dat";
    String filePath = directory + File.separator + fileName;

    //Referance to single instance of CreditSystemFileIO class
    private static CreditSystemFileIO csfio = null;

    //Private constructor for Singleton object
    private CreditSystemFileIO(){

    }

    //Getter method for Singleton Object
    public static CreditSystemFileIO getCsfio(){
        if(csfio == null){
            csfio = new CreditSystemFileIO();
        }
        return csfio;
    }

    //TO DO : Make ArrayList<ArrayList> an input parameter
    public void writeData(ArrayList<ArrayList> arrayList){
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

    //TO DO: Make method return ArrayList<ArryList>
    public ArrayList<ArrayList> readData(){
        ArrayList<ArrayList> tempArrayList = new ArrayList<>();
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
        return tempArrayList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
}
