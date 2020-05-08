package Interfaces;

import Domain.CrewMember;

import java.util.ArrayList;

public interface CrewMemberInterface {

    //Create
    void addCrewMember(String name, String email, int castCrewId);

    //Read
    ArrayList<CrewMember> getCrewMemberList();

    //Update
    void updateCrewMember(String name, String email, int castCrewId);

    //Delete
    void removeCrewMember(String email);

}
