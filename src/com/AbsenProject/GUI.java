package com.AbsenProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GUI{

    private JFrame frame;
    private JPanel panel;
    String chooseName;

    Date dateIn, dateOut = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    ArrayList<DataAbsen> dataAbsen = new ArrayList<DataAbsen>();

    JComboBox nameDropDown = new JComboBox();

    public GUI() {

        frame = new JFrame("ABSEN PROGRAM");

        panel = new JPanel();
        SpringLayout sprnglyt = new SpringLayout();

        JLabel nameLabel = new JLabel("Nama: ");

        nameDropDown.setPreferredSize(new Dimension(160, 25));
        chooseName = (String) nameDropDown.getSelectedItem();
        nameDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseName = (String) nameDropDown.getSelectedItem();

            }
        });

        JButton showName = new JButton("Show Name");
        showName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        frame,
                        chooseName
                );
            }
        });

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

                if(name != null && name.length() >= 4) {

                    nameDropDown.addItem(name);

                } else if(name.length() < 1) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Name cannot be empty!",
                            "Blank Name Error",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else if(name.length() < 4) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Name must be 4 letters or more",
                            "Minimum Letters Error",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Something went wrong"
                    );

                }
            }
        });

        JLabel inDateLabel = new JLabel("In Time: ");
        JTextField inDateField = new JTextField();
        inDateField.setEditable(false);
        inDateField.setPreferredSize(new Dimension(160, 25));

        JLabel outDateLabel = new JLabel("Out Time: ");
        JTextField outDateField = new JTextField();
        outDateField.setEditable(false);
        outDateField.setPreferredSize(new Dimension(160, 25));

        JButton inDateSet = new JButton("Set Time");
        inDateSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dateIn = new Date();
                inDateField.setText(dateFormat.format(dateIn));

                if(dateIn.after(dateOut)) {

                    outDateField.setText(" ");

                }
            }
        });

        JButton outDateSet = new JButton("Set Time");
        outDateSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dateOut = new Date();
                outDateField.setText(dateFormat.format(dateOut));

            }
        });

        JButton absenButton = new JButton("Absen");
        absenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(chooseName == null || chooseName.length() < 4) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Name is still empty!",
                            "Name Empty",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else if(inDateField.getText() == null || inDateField.getText().length() < 4) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "In Time is not set yet!",
                            "In Time Not Set",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else if(outDateField.getText() == null || outDateField.getText().length() < 4) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Out Time is not set Yet!",
                            "Out Time Not Set",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {

                    dataAbsen.add(new DataAbsen(chooseName, inDateField.getText(), outDateField.getText()));

                }
            }
        });

        JButton showAbsen = new JButton("Lihat Absen");
        showAbsen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel model = new DefaultTableModel(
                        new Object[][] {

                        },
                        new Object[] {
                                "Nama", "In Time", "Out Time"
                        });

                Object rowData[] = new Object[3];
                for(int i = 0; i < dataAbsen.size(); i++) {

                    rowData[0] = dataAbsen.get(i).name;
                    rowData[1] = dataAbsen.get(i).dateIn;
                    rowData[2] = dataAbsen.get(i).dateOut;
                    model.addRow(rowData);

                }

                JFrame tableFrame = new JFrame();
                JTable table = new JTable(model);

                tableFrame.setResizable(false);
                tableFrame.setLocationRelativeTo(null);
                tableFrame.getContentPane().add(new JScrollPane(table));
                tableFrame.pack();
                tableFrame.setVisible(true);

            }
        });


        panel.setPreferredSize(new Dimension(400, 200));
        panel.setLayout(sprnglyt);

        panel.add(nameLabel);
        panel.add(nameDropDown);
        panel.add(addNewName);
        // panel.add(showName);

        panel.add(inDateLabel);
        panel.add(inDateField);
        panel.add(inDateSet);

        panel.add(outDateLabel);
        panel.add(outDateField);
        panel.add(outDateSet);

        panel.add(absenButton);
        panel.add(showAbsen);

        sprnglyt.putConstraint(SpringLayout.WEST, nameLabel, 6, SpringLayout.WEST, panel);
        sprnglyt.putConstraint(SpringLayout.NORTH, nameLabel, 8, SpringLayout.NORTH, panel);
        sprnglyt.putConstraint(SpringLayout.WEST, nameDropDown, 25, SpringLayout.EAST, nameLabel);
        sprnglyt.putConstraint(SpringLayout.NORTH, nameDropDown, 6, SpringLayout.NORTH, panel);
        sprnglyt.putConstraint(SpringLayout.WEST, addNewName, 6, SpringLayout.EAST, nameDropDown);
        sprnglyt.putConstraint(SpringLayout.NORTH, addNewName, 6, SpringLayout.NORTH, panel);
        // sprnglyt.putConstraint(SpringLayout.WEST, showName, 6, SpringLayout.EAST, addNewName);
        // sprnglyt.putConstraint(SpringLayout.NORTH, showName, 6, SpringLayout.NORTH, panel);

        sprnglyt.putConstraint(SpringLayout.WEST, inDateLabel, 6, SpringLayout.WEST, panel);
        sprnglyt.putConstraint(SpringLayout.NORTH, inDateLabel, 35, SpringLayout.NORTH, nameLabel);
        sprnglyt.putConstraint(SpringLayout.WEST, inDateField, 16, SpringLayout.EAST, inDateLabel);
        sprnglyt.putConstraint(SpringLayout.NORTH, inDateField, 32, SpringLayout.NORTH, nameDropDown);
        sprnglyt.putConstraint(SpringLayout.WEST, inDateSet, 6, SpringLayout.EAST, inDateField);
        sprnglyt.putConstraint(SpringLayout.NORTH, inDateSet, 32, SpringLayout.NORTH, addNewName);

        sprnglyt.putConstraint(SpringLayout.WEST, outDateLabel, 6, SpringLayout.WEST, panel);
        sprnglyt.putConstraint(SpringLayout.NORTH, outDateLabel, 35, SpringLayout.NORTH, inDateLabel);
        sprnglyt.putConstraint(SpringLayout.WEST, outDateField, 6, SpringLayout.EAST, outDateLabel);
        sprnglyt.putConstraint(SpringLayout.NORTH, outDateField, 32, SpringLayout.NORTH, inDateField);
        sprnglyt.putConstraint(SpringLayout.WEST, outDateSet, 6, SpringLayout.EAST, outDateField);
        sprnglyt.putConstraint(SpringLayout.NORTH, outDateSet, 32, SpringLayout.NORTH, inDateSet);

        sprnglyt.putConstraint(SpringLayout.WEST, absenButton, 160, SpringLayout.WEST, panel);
        sprnglyt.putConstraint(SpringLayout.SOUTH, absenButton, -20, SpringLayout.SOUTH, panel);
        sprnglyt.putConstraint(SpringLayout.EAST, showAbsen, -20, SpringLayout.EAST, panel);
        sprnglyt.putConstraint(SpringLayout.SOUTH, showAbsen, -20, SpringLayout.SOUTH, panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
