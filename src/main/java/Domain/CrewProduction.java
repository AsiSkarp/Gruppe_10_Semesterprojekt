package Domain;

public class CrewProduction {
    private CrewMember crewMember;
    private Production production;
    private String role;

    public CrewProduction(CrewMember crewMember, Production production, String role) {
        this.crewMember = crewMember;
        this.production = production;
        this.role = role;
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
        return crewMember.getName();
    }

    public String getProductionTitle() {
        return production.getTitle();
    }
}
