package com.AbsenProject;

import java.util.ArrayList;

public class Management {

    public ArrayList<Pegawai> namaPegawai = new ArrayList<Pegawai>();

    public void addPegawai(String name) {

        namaPegawai.add(new Pegawai(name));

    }
}
