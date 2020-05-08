package Domain;

import java.util.ArrayList;

public class Login {

    private static Login login;

    private Login() {};

    public static Login getLogin() {
        if(login == null) {
            login = new Login();
        } return login;
    }

    ArrayList<User> tempList = CreditSystem.getCreditSystem().getUserList();


    public void login(String email, String password) {
        ArrayList<User> fetchUser = tempList;
        boolean printText = false;

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
                    printText = true;
                }
            }
        //THERE IS ALSO A SOUT IN THE LOGINCONTROLLER SO THIS IS NOT NECESSARY ATM
//        if(printText) {
//            System.out.println("Username and Password doesn't match");
//        }

        }
}
