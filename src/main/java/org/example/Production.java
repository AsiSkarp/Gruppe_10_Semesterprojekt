package org.example;

import java.util.ArrayList;

public class Production {
    private String title;
    private int productionId;
    private ArrayList<CrewMember> productionCrewMList;
    private Producer producerId;

    public Production(String title, int productionId, ArrayList<CrewMember> productionCrewMList, Producer producerId) {
        this.title = title;
        this.productionId = productionId;
        this.productionCrewMList = productionCrewMList;
        this.producerId = producerId;



    }
}
