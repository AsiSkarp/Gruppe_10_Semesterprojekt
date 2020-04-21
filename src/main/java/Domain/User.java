package Domain;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {

    private String name;
    private int id;
    private static int idCounter = 1;
    private String email;
    private String password;
    private CreditSystem creditSystem = new CreditSystem();

    //STRING SO I DON'T HAVE TO WRITE THE SAME PIECE OF TEXT MULTIPLE TIMES
    private String txt = "You can't do that!";

    public User(String name, String email, String password) {
        this.name = name;
        this.id = idCounter++;
        this.email = email;
        this.password = password;
    }

    //SUPERADMIN METHODS:
    //Still don't know how to add a reference to the new object created
    public void addAdmin(String name, String email, String password){
    /*    if(getIsSuperAdmin()){
            Admin a = new Admin(name, email, password);
        } else {
            System.out.println(txt);
        }*/
        creditSystem.addAdminToSystem(name, email, password);
    }

    public String getName() {
        return name;
    }

    public void removeAdmin(Admin admin) {
        if(getIsSuperAdmin()) {
            admin = null;
        } else {
            System.out.println(txt);
        }
    }

    //PRODUCER METHODS:
    //THIS IS THE METHOD WHERE I ADDED A REFERENCE VARIABLE BUT ASSIGN NULL IF ACCESS IS DENIED
    public Production addProduction(String title, ArrayList<CrewMember> productionCrewMList, int producerId){
        Production a = null;
        if(getIsProducer()){
            a = new Production(title, productionCrewMList, producerId);
        } else {
            System.out.println(txt);
        }
        return a;

    }

    public void removeProduction(Production production) {
        if(getIsProducer()) {
            production = null;
        } else {
            System.out.println(txt);
        }
    }

    //ADMIN METHODS:
    public void addProducer(String name, String email, String password){
        if(getIsAdmin()){
            Producer a = new Producer(name, email, password);
        } else {
            System.out.println(txt);
        }
    }

    public void removeProducer(Producer producer){
        if(getIsAdmin()){
            producer = null;
        } else {
            System.out.println(txt);
        }
    }

    @Override
    public String toString(){
        return "id: " + id + ", name: " + name + ", email: " + email + ", password: " + password + ", type: " + this.getClass();
    }




    //TO TEST THE AUTO ID ASSIGN
    public int getId(){
        return id;
    }


    //Access Restriction:
    public abstract boolean getIsSuperAdmin();
    public abstract boolean getIsAdmin();
    public abstract boolean getIsProducer();
}
