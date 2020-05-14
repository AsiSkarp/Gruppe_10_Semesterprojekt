package Domain;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {

    private String name;
    private int id;
    private static int idCounter = 1;
    private String email;
    private String password;
    private String usertype;

    //STRING SO I DON'T HAVE TO WRITE THE SAME PIECE OF TEXT MULTIPLE TIMES
    private String txt = "You can't do that!";

    public User(String name, String email, String password) {
        this.name = name;
        this.id = idCounter++;
        this.email = email;
        this.password = password;
        this.usertype = this.toString();
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString(){
        return "id: " + id + ", name: " + name + ", email: " + email + ", password: " + password + ", type: " + this.getClass();
    }


    //TO TEST THE AUTO ID ASSIGN
    public int getId(){
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUsertype() {
        return usertype;
    }

    //Access Restriction:
    public abstract boolean getIsSuperAdmin();
    public abstract boolean getIsAdmin();
    public abstract boolean getIsProducer();
}
