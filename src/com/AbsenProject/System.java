package com.AbsenProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class System {

    private JFrame frame;   // main frame variable
    private JPanel panel;
    String chooseName;

    Date dateIn, dateOut = new Date(); // set date
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMM yyyy HH:mm:ss"); // date format

    ArrayList<DataAbsen> dataAbsen = new ArrayList<DataAbsen>();    // create object arraylist

    JComboBox<String> nameDropDown = new JComboBox<String>();   // dropdown name selector

    public System() {

        frame = new JFrame("ABSEN PROGRAM"); // create frame and set title

        panel = new JPanel();
        SpringLayout sprnglyt = new SpringLayout(); // set layot type

        JLabel nameLabel = new JLabel("Nama: ");

        nameDropDown.setPreferredSize(new Dimension(170, 25));  // dropdown size
        chooseName = (String) nameDropDown.getSelectedItem();
        nameDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseName = (String) nameDropDown.getSelectedItem();

            }
        });

        // for testing purpose only, currently not being used
        // show what namedropdown selector is choosing
        /*
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
        */

        // adding new pegawai to the system
        JButton addNewName = new JButton("ADD Pegawai");
        addNewName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // showing the input name dialog
                String name = (String) JOptionPane.showInputDialog(
                        frame,
                        "Input new Pegawai name",
                        "Add New Pegawai",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );

                try {

                    nameCheck(name);
                    nameDropDown.addItem(name);

                } catch(NameAppropriateException ex) {

                    JOptionPane.showMessageDialog(
                            frame,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                }
            }
        });

        // for in date time
        JLabel inDateLabel = new JLabel("In Time: ");
        JTextField inDateField = new JTextField();
        inDateField.setEditable(false);
        inDateField.setPreferredSize(new Dimension(170, 25));

        //for out date time
        JLabel outDateLabel = new JLabel("Out Time: ");
        JTextField outDateField = new JTextField();
        outDateField.setEditable(false);
        outDateField.setPreferredSize(new Dimension(170, 25));

        // set time button for in and out
        // out time is 8 hours after in time
        // because the work time is 8 hours
        JButton inDateSet = new JButton("Set Time");
        inDateSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dateIn = new Date();
                inDateField.setText(dateFormat.format(dateIn));
                dateOut = new Date(dateIn.getTime() + 8 * (3600*1000));
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

                    inDateField.setText(null);
                    outDateField.setText(null);

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

                JFrame tableFrame = new JFrame("Absen List");
                JTable table = new JTable(model);

                tableFrame.setPreferredSize(new Dimension(520, 300));
                tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        panel.add(absenButton);
        panel.add(showAbsen);

        sprnglyt.putConstraint(SpringLayout.WEST, nameLabel, 6, SpringLayout.WEST, panel);
        sprnglyt.putConstraint(SpringLayout.NORTH, nameLabel, 8, SpringLayout.NORTH, panel);
        sprnglyt.putConstraint(SpringLayout.WEST, nameDropDown, 25, SpringLayout.EAST, nameLabel);
        sprnglyt.putConstraint(SpringLayout.NORTH, nameDropDown, 6, SpringLayout.NORTH, panel);
        sprnglyt.putConstraint(SpringLayout.WEST, addNewName, 6, SpringLayout.EAST, nameDropDown);
        sprnglyt.putConstraint(SpringLayout.NORTH, addNewName, 6, SpringLayout.NORTH, panel);

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
        //sprnglyt.putConstraint(SpringLayout.WEST, outDateSet, 6, SpringLayout.EAST, outDateField);
        //sprnglyt.putConstraint(SpringLayout.NORTH, outDateSet, 32, SpringLayout.NORTH, inDateSet);

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

    public static String nameCheck(String name) throws NameAppropriateException {

        if(name != null && name.length() >= 4) {

            return name; // adding name to the dropdown selector

        } else if(name.length() < 1) {

            throw new NameAppropriateException("Name cannot be empty!");

        } else if(name.length() < 4) {

            throw new NameAppropriateException("Name must be 4 letters or more!");

        } else {

            throw new NameAppropriateException("Something went wrong");

        }
    }
}
