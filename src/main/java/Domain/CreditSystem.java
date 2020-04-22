package Domain;

import java.io.*;
import java.util.ArrayList;

public class CreditSystem implements Persistance, Serializable{

    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Production> productionList = new ArrayList<>();
    ArrayList<CrewMember> crewMemberList = new ArrayList<>();
    ArrayList<ArrayList> creditSystemList = new ArrayList<>();
    String directory = System.getProperty("user.home");
    String fileName = "Credit_System.dat";
    String filePath = directory + File.separator + fileName;

    public CreditSystem() {
    }

    public void writeToFile(){

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            ObjectOutputStream outputStream = new ObjectOutputStream(bufferedOutputStream);
            outputStream.writeObject(userList);
            outputStream.flush();
                } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

     public void readFromFile(){
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            userList = (ArrayList<User>) objectInputStream.readObject();
            for(int i = 0; i < userList.size(); i++){
                System.out.println(userList.get(i).toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Admin addAdminToSystem(String name, String email, String password) {
        Admin newAdmin = new Admin(name, email, password);
        userList.add(newAdmin);
        return newAdmin;
    }

    @Override
    public void removeAdminFromSystem(String name) {

    }

    @Override
    public Producer addProducerToSystem(String name, String email, String password) {
        Producer newProducer = new Producer(name, email, password);
        userList.add(newProducer);
        return newProducer;
    }

    @Override
    public void removeProducerFromSystem(String name) {

    }

    @Override
    public Production addProductionToSystem(String title, ArrayList<CrewMember> productionCrewMList, int producerId) {
        Production newProduction = new Production(title, productionCrewMList , producerId);
        productionList.add(newProduction);
        return newProduction;
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

    public void accessRestriction(User user){
        if(user.getIsAdmin() == true) {
            System.out.println(user.getName() + " is an admin and may execute this command!");
        } else {
            System.out.println(user.getName() + " is not an admin. Access restricted.");
        }
    }
}
