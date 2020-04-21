package Domain;

import java.util.HashMap;

public class CrewMember {

    private String name;
    private HashMap<String, Production> role;
    private int castCrewId;
    private String email;

    public CrewMember(String name, String email, int castCrewId) {
        this.name = name;
        this.castCrewId = castCrewId;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getCastCrewId() {
        return castCrewId;
    }

    public String getEmail() {
        return email;
    }
}
