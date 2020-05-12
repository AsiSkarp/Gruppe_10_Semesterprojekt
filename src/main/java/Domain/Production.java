package Domain;

import Persistance.ProductionSystemDatabaseRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Production implements Serializable {
    private String title;
    private String owner;
    private int productionId;
    private Date date;
    //private static int productionIdCounter = 1;
    private ArrayList<CrewMember> productionCrewMList;
    private User currentUser;

    private static Production production = null;
    public Production() {
    }

    public static Production getProductionSystem(){
        if(production == null){
            production = new Production();
        }
        return production;
    }

    public Production(String title, String owner, int productionId) {
        this.title = title;
        this.owner = owner;
        this.productionId = productionId;
    }

    public int getProductionId() {
        return productionId;
    }

    public void setProductionId(int productionId) {
        this.productionId = productionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Production{" +
                "title='" + title + '\'' +
                ", owner='" + owner + '\'' +
                ", productionId=" + productionId +
                ", date=" + date +
                ", currentUser=" + currentUser +
                '}';
    }

    public ArrayList<Production> getProductionDatabase() {
        return ProductionSystemDatabaseRepository.getCsdio().getProductionList();
    }

    public void addProduction(String title, String owner, int productionId) {
        if(currentUser.getIsProducer()) {
            ProductionSystemDatabaseRepository.getCsdio().addProduction(title, owner, productionId);
            ProductionSystemDatabaseRepository.getCsdio().addProduction(title, owner, productionId);
        } else {
            System.out.println("Access Restricted!");
        }
    }
}

