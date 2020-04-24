package Domain;

import Persistance.CreditSystemFileIO;

import java.util.ArrayList;

public class Login {

    private static Login login;

    private Login() {};

    public static Login getLogin() {
        if(login == null) {
            login = new Login();
        } return login;
    }

    ArrayList<ArrayList> tempList = CreditSystem.getCreditSystem().readFromPersistance();


    public void login(String email, String password) {
        ArrayList<User> fetchUser = tempList.get(0);
        for (User u : fetchUser) {
                if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                    if(u.getIsSuperAdmin()){
                        CreditSystem.getCreditSystem().setCurrentUser(u);
                        System.out.println("current user: SuperAdmin");
                        break;
                    } else if (u.getIsAdmin()) {
                        CreditSystem.getCreditSystem().setCurrentUser(u);
                        System.out.println("current user: Admin");
                        break;
                    } else if (u.getIsProducer()) {
                        CreditSystem.getCreditSystem().setCurrentUser(u);
                        System.out.println("current user: Producer");
                        break;
                    }
                } else {
                    //THIS IS GONNA BE PRINTED MULTIPLE TIMES ATM
                    System.out.println("Username and Password doesn't match");
                }
            }
        }
}
