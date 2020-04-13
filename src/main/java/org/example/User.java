package org.example;

import java.util.ArrayList;

public abstract class User {
    private String name;
    private int id;
    private static int idCounter = 1;
    private String email;
    private String password;

    //STRING FOR TXT
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
        Admin a = new Admin(name, email, password);
    }

    public void removeAdmin(Admin admin) {
        if(getIsSuperAdmin()) {
            admin = null;
        } else {
            System.out.println(txt);
        }
    }

    //PRODUCER METHODS:
    public void addProduction(String title, int productionId, ArrayList<CrewMember> productionCrewMList, Producer producerId){
        if(getIsProducer()){
            Production a = new Production(title, productionId, productionCrewMList, producerId);
        } else {
            System.out.println("You can't do that!");
        }
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


    //TO TEST THE AUTO ID ASSIGN
    public int getId(){
        return id;
    }


    //Access Restriction:
    public abstract boolean getIsSuperAdmin();
    public abstract boolean getIsAdmin();
    public abstract boolean getIsProducer();
}
