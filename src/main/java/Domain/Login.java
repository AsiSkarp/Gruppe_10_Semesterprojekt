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

    //I AM USING USERLIST HERE BUT IF I UNDERSTAND THE CODE RIGHT IT DOESNT GET UPDATED WHEN STARTING THE PROGRAM
    //BUT I DONT KNOW HOW TO GET ONLY THE USER OBJECTS FROM THE ARRAYLIST OF ARRAYLISTS
    public void login(String email, String password) {
        for(User user : CreditSystemFileIO.getCsfio().getUserList()) {
            if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
                if(user.getIsSuperAdmin()){
                    //DO SOMETHING
                    break;
                } else if (user.getIsAdmin()) {
                    //DO SOMETHING
                    break;
                } else if (user.getIsProducer()) {
                    //DO SOMETHING
                    break;
                }
            } else {
                //THIS IS GONNA BE PRINTED MULTIPLE TIMES ATM
                System.out.println("Username and Password doesn't match");
            }
        }

    }
}
