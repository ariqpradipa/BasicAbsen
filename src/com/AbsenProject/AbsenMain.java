package com.AbsenProject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AbsenMain {

    //public static Management managing = new Management();

    public static ArrayList<String> nameArrayListString = new ArrayList<String>();

    public static void main(String[] args) {

        new GUI(nameArrayListString);

    }

    public static void addName(String name) {

        nameArrayListString.add(name);
        new GUI(nameArrayListString);

    }

}