package com.AbsenProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI implements ActionListener{

    private JFrame frame;
    private JPanel panel;

    public GUI(ArrayList<String> nameArrayListString) {

        frame = new JFrame("ABSEN PROGRAM");

        panel = new JPanel();
        SpringLayout sprnglyt = new SpringLayout();

        JLabel nameLabel = new JLabel("Nama: ");

        JComboBox nameDropDown = new JComboBox(nameArrayListString.toArray());
        nameDropDown.setPreferredSize(new Dimension(160, 25));
        JButton addNewName = new JButton("ADD Pegawai");
        addNewName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = (String) JOptionPane.showInputDialog(
                        frame,
                        "Input new Pegawai name",
                        "Add New Pegawai",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );
                if(name != null && name.length() > 4) {

                    frame.dispose();
                    AbsenMain.addName(name);

                } else if(name.length() < 1) {
                    JOptionPane.showMessageDialog(frame,
                            "Name cannot be empty!",
                            "Blank Name Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new GUI(nameArrayListString);
                }

            }
        });

        panel.setPreferredSize(new Dimension(400, 200));
        panel.setLayout(sprnglyt);

        panel.add(nameLabel);
        panel.add(nameDropDown);
        panel.add(addNewName);

        sprnglyt.putConstraint(SpringLayout.WEST, nameLabel, 6, SpringLayout.WEST, panel);
        sprnglyt.putConstraint(SpringLayout.NORTH, nameLabel, 6, SpringLayout.NORTH, panel);
        sprnglyt.putConstraint(SpringLayout.WEST, nameDropDown, 6, SpringLayout.EAST, nameLabel);
        sprnglyt.putConstraint(SpringLayout.NORTH, nameDropDown, 6, SpringLayout.NORTH, panel);
        sprnglyt.putConstraint(SpringLayout.WEST, addNewName, 6, SpringLayout.EAST, nameDropDown);
        sprnglyt.putConstraint(SpringLayout.NORTH, addNewName, 6, SpringLayout.NORTH, panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
