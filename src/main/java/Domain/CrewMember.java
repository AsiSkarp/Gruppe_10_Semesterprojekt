package Domain;

//import java.util.HashMap;

public class CrewMember {

    private String name;
    private String email;
    private int castCrewId;
//    private HashMap<String, Production> role;


    public CrewMember(String name, String email, int castCrewId) {
        this.name = name;
        this.castCrewId = castCrewId;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCastCrewId() {
        return castCrewId;
    }

    public void setCastCrewId(int castCrewId) {
        this.castCrewId = castCrewId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " " + email + " " + castCrewId;
    }
}
