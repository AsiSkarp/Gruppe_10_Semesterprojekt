package Domain;

public class SuperAdmin extends User{
    public SuperAdmin(String name, String email, String password){
        super(name, email, password);
    }

    @Override
    public boolean getIsSuperAdmin() {
        return true;
    }

    @Override
    public boolean getIsAdmin() {
        return true;
    }

    @Override
    public boolean getIsProducer() {
        return true;
    }

    @Override
    public String toString() {
        return "SuperAdmin";
    }
}
