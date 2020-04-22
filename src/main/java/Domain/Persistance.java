package Domain;

import java.util.ArrayList;

public interface Persistance {


    Admin addAdminToSystem(String name, String email, String password);

    void removeAdminFromSystem(String name);

    Producer addProducerToSystem(String name, String email, String password);

    void removeProducerFromSystem(String name);

    Production addProductionToSystem(String title, ArrayList<CrewMember> productionCrewMList, int producerId);

    void removeProductionFromSystem(String title,int producerId);

    void addCrewMember(String name, String email, int castCrewId);

    void removeCrewMember(String name, int castCrewId);
}
