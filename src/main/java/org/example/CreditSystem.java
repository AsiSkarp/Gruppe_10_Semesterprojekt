package org.example;

import java.io.*;
import java.util.ArrayList;

public class CreditSystem {
    ArrayList userList = new ArrayList<>();
    ArrayList productionList = new ArrayList<>();
    ArrayList personList = new ArrayList();

    //File
    //BufferedReader
    FileWriter fileWriter;

    //Read users from CSV File (UNFINISHED)
    BufferedReader bufferedReader;

    {
        try {
            bufferedReader = new BufferedReader(new FileReader("user_list.csv"));
            String row;
            while((row = bufferedReader.readLine()) != null) {
                String[] userData = row.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}



