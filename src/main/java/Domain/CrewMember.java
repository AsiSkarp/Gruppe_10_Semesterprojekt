package Domain;

import java.io.Serializable;

public class CrewMember implements Serializable {

    private String name;
    private String email;
    private String role;
    private int castCrewId;


    public CrewMember(String name, String email, String role, int castCrewId) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.castCrewId = castCrewId;

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        return name + " " + email + " " + role + " " + castCrewId;
    }
}
