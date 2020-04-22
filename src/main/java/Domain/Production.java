package Domain;

import java.util.ArrayList;

public class Production {
    private String title;
    private static int productionIdCounter = 1;
    private int productionId;
    private ArrayList<CrewMember> productionCrewMList;
    private int producerId;

    public Production(String title,  int producerId) {
        this.title = title;
        this.productionId = productionIdCounter++;
        this.producerId = producerId;

    }

    public String getTitle() {
        return title;
    }
}
