package Domain;

public class Admin extends User{

    public Admin(String name, String email, String password){
        super(name, email, password);
    }

    @Override
    public boolean getIsSuperAdmin() {
        return false;
    }

    @Override
    public boolean getIsAdmin() {
        return true;
    }

    @Override
    public boolean getIsProducer() {
        return false;
    }
}
