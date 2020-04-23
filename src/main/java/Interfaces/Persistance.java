package Interfaces;

import Domain.User;

import java.util.ArrayList;

public interface Persistance {


    void addAdminToSystem(String name, String email, String password);

    void removeAdminFromSystem(String name);

    void addProducerToSystem(String name, String email, String password);

    void removeProducerFromSystem(String name);

    void addProductionToSystem(String title, int producerId);

    void removeProductionFromSystem(String title);

    void addCrewMember(String name, String email, int castCrewId);

    void removeCrewMember(String name, int castCrewId);
}
