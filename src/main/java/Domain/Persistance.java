package Domain;

import java.util.ArrayList;

public interface Persistance {


    void addAdminToSystem(String name, String email, String password, User user);

    void removeAdminFromSystem(String name, User user);

    void addProducerToSystem(String name, String email, String password, User user);

    void removeProducerFromSystem(String name, User user);

    void addProductionToSystem(String title, int producerId, User user);

    void removeProductionFromSystem(String title, User user);

    void addCrewMember(String name, String email, int castCrewId, User user);

    void removeCrewMember(String name, int castCrewId, User user);
}
