package org.example;

import java.util.HashMap;

public class CrewMember {
    private String name;
    private HashMap<Production, String> role;
    private int castCrewId;

    public CrewMember(String name, HashMap<Production, String> role, int castCrewId) {
        this.name = name;
        this.role = role;
        this.castCrewId = castCrewId;
        System.out.println("HamidTest");
    }
}
