package Domain;

public class CrewProduction {
    private CrewMember crewMember;
    private Production production;
    private String role;
    private String name;

    public CrewProduction(CrewMember crewMember, Production production, String role, String name) {
        this.crewMember = crewMember;
        this.production = production;
        this.role = role;
        this.name = name;
    }

    public CrewMember getCrewMember() {
        return crewMember;
    }

    public Production getProduction() {
        return production;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getProductionTitle() {
        return production.getTitle();
    }
}
