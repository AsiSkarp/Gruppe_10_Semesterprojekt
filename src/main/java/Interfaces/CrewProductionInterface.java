package Interfaces;

import Domain.CrewProduction;
import Persistance.CreditSystemDatabaseRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrewProductionInterface {


    public ArrayList<CrewProduction> getCrewProduction(int id);

    public void addCrewToProduction(String name, String email, String role, int productionId) throws SQLException;

    public ArrayList<CrewProduction> getPersonalRecord(int id);

}
