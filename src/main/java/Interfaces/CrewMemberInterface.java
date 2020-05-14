package Interfaces;

import Domain.CrewMember;
import java.util.ArrayList;

public interface CrewMemberInterface {

    //Create
    void addCrewMember(String name, String email);

    //Read
    ArrayList<CrewMember> getCrewMemberList();

    //Update
    void updateCrewMember(String name, String email);

    //For Loops are NICER!
    void removeCrewMember(String email);
}
