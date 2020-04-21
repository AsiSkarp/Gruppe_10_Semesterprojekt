package Domain;

public class Producer extends User{
    public Producer(String name, String email, String password){
        super(name, email, password);
    }

    @Override
    public boolean getIsSuperAdmin() {
        return false;
    }

    @Override
    public boolean getIsAdmin() {
        return false;
    }

    @Override
    public boolean getIsProducer() {
        return true;
    }
}
