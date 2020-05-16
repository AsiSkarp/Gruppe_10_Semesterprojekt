package Domain;

import java.io.Serializable;

public class CrewMember implements Serializable {

    private String name;
    private String email;
    private int castCrewId;


    public CrewMember(String name, String email, int castCrewId) {
        this.name = name;
        this.email = email;
        this.castCrewId = castCrewId;
    }

    public int getCastCrewId() {
        return castCrewId;
    }

    public void setCastCrewId(int castCrewId) {
        this.castCrewId = castCrewId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
