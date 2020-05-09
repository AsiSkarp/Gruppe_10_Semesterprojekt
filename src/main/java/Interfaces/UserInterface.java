package Interfaces;

import Domain.User;

import java.util.ArrayList;

public interface UserInterface {

    //Read
    ArrayList<User> getUserList();

    //Delete
    void removeUser(String email);
}
