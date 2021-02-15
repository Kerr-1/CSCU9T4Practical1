// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField tempo = new JTextField(15); 
    private JTextField surface = new JTextField(15);
    private JTextField reps = new JTextField(2);
    private JTextField recovery = new JTextField(2);
    private JTextField where = new JTextField(10);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labTemp = new JLabel(" Tempo:");
    private JLabel labSurf = new JLabel(" Surface:");
    private JLabel labRep = new JLabel(" Reps:");
    private JLabel labRec = new JLabel(" Recovery:");
    private JLabel labWhere = new JLabel(" Where:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    String type[] = {"SELECT", "Sprint", "Swim", "Cycle"};
    private JComboBox selector = new JComboBox(type);
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton WeeklyDist = new JButton("Weekly Distance");
    private JButton FindAllByDate = new JButton("Find All By Date");
    private JButton FindAllByName = new JButton("Find All By Name");
    private JButton RemoveEntry = new JButton("Remove Entry");
    private TrainingRecord myAthletes = new TrainingRecord();
    private JTextArea outputArea = new JTextArea(5, 50);
    

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
      
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(selector);
        selector.addActionListener(this);
        add(labn);
        add(name);
        name.setEditable(true);
     
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
          
        add(labTemp); //add tempo box for Cycle class
        add(tempo);
        tempo.setEditable(false);
        add(labSurf); //add Surface for cycle class
        add(surface);
        surface.setEditable(false);
        add(labRep);  //add reps for for sprint class
        add(reps);
        reps.setEditable(false);
        add(labRec);  //add recovery for sprint class
        add(recovery);
        recovery.setEditable(false);
        add(labWhere);  //add where for swim class
        add(where);
        where.setEditable(false);
        
        add(addR);
        addR.addActionListener(this);
        addR.setEnabled(true);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        lookUpByDate.setEnabled(false);
        add(FindAllByDate);
        FindAllByDate.addActionListener(this);
        FindAllByDate.setEnabled(false);
        add(FindAllByName);
        FindAllByName.addActionListener(this);
        FindAllByName.setEnabled(false);
        add(RemoveEntry);
        RemoveEntry.addActionListener(this);
        RemoveEntry.setEnabled(false);
        add(WeeklyDist);
        WeeklyDist.addActionListener(this);
        WeeklyDist.setEnabled(true);
        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 500);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
          
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if(event.getSource() == WeeklyDist) {
          message = WeeklyDist();
        }
        if(event.getSource() == selector) {
          setEditable((String)selector.getSelectedItem());
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
         if(event.getSource() == FindAllByDate) {
          message = FindAllByDate(); 
        }
        if(event.getSource() == FindAllByName) {
          message = FindAllByName();
        }
        if(event.getSource() == RemoveEntry) {
          message = RemoveEntry();
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
        String message = "Record added\n";
        System.out.println("Adding "+what+" entry to the records");
        boolean bool = true;
        String n = name.getText();
        String selectValue = (String)selector.getSelectedItem(); //Gets the value of the combo box
        int m; int d; int y; float km; int h; int mm; int s; int r; int rec;
        String t = tempo.getText();
        String sur = surface.getText(); 
        String w = where.getText();
       
  try {
            m = Integer.parseInt(month.getText());
            d = Integer.parseInt(day.getText());
            y = Integer.parseInt(year.getText());
            h = Integer.parseInt(hours.getText());
            mm = Integer.parseInt(mins.getText());
            s = Integer.parseInt(secs.getText());
            km = java.lang.Float.parseFloat(dist.getText());
            r = Integer.parseInt(reps.getText());
            rec = Integer.parseInt(recovery.getText());
            
        } catch (NumberFormatException e) {
          return message = "Invalid Input, check inputs";
        }


        if(n.isEmpty()) {
            message = "Error input for name was empty.";
            bool = false;
            return message;
            //checks whether the name field is empty upon add
        }
        setEnable(n, bool); //Checks whether the conditions suffice to enable buttons
        if(selectValue.equals("Sprint"))
          {
          SprintEntry e = new SprintEntry(n, d, m, y, h, mm, s, km,  r,  rec); 
          ifDupe(n,d,m,y);
          myAthletes.addEntry(e);
          }
        else if(selectValue.equals("Cycle"))
      {
          CycleEntry e = new CycleEntry(n, d, m, y, h, mm, s, km, sur, t); 
          ifDupe(n,d,m,y);
        myAthletes.addEntry(e);
      }
        else if(selectValue.equals("Swim"))
      {
        SwimEntry e = new SwimEntry(n, d, m, y, h, mm, s, km, w); 
        ifDupe(n,d,m,y);
        myAthletes.addEntry(e);
      }
        else {
          Entry e = new Entry(n, d, m, y, h, mm, s, km);
          myAthletes.addEntry(e);
          
        }
        //Add setEditEnable
          
      
        return message;
    }
    
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);

        return message;
    }
    
    public String FindAllByDate() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
      String str = myAthletes.FindAllByDate(d, m, y);
      
      return str; 
    }
    
    public String FindAllByName() {
      String n = name.getText();
      outputArea.setText("Searching...");
      String str = myAthletes.FindAllByName(n);
      
      return str;
    }
    
    public String RemoveEntry() {
      String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("removing record ...");
        myAthletes.RemoveEntry(n, d, m, y);
        
        return "Removed.";
    }
    
    public String WeeklyDist() {
      String n = name.getText(); 
      String type =(String)selector.getSelectedItem(); 
      outputArea.setText("Getting Weekly distance...");
      String str = myAthletes.weeklyDistance(n, type);
      

      return str;
    }
    
    public void ifDupe(String n, int d, int m, int y) {
      String str = "No duplicates present.";
      if(myAthletes.ifDupe(n, d, m, y)) {
        str = "There is a duplicate removing last entry";
        myAthletes.RemoveEntry(n, d, m, y);
      }
      
      System.out.println(str);
      
      
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        tempo.setText("");
        reps.setText("");
        recovery.setText("");
        where.setText("");
        surface.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }
    
  
    public void setEnable(String n, boolean bool) {
        
      if(!n.isEmpty()) {
            FindAllByName.setEnabled(true);
        }
        if(bool) {
          FindAllByDate.setEnabled(true);
            lookUpByDate.setEnabled(true);
            RemoveEntry.setEnabled(true);
        } //Checks if the conditions are correct to enable buttons.
    }
   
    public void setEditable(String input) {
      if(input.equals("Sprint"))
        {
          reps.setEditable(true);
          recovery.setEditable(true);
          tempo.setEditable(false);
            surface.setEditable(false);
            where.setEditable(false);
        }
      if(input.equals("Cycle"))
      {
          tempo.setEditable(true);
          surface.setEditable(true);
          reps.setEditable(false);
        recovery.setEditable(false);
        where.setEditable(false);
      }
      if(input.equals("Swim"))
        {
        where.setEditable(true);
        tempo.setEditable(false);
          surface.setEditable(false);
          reps.setEditable(false);
        recovery.setEditable(false);
        }
      
    
    }

} // TrainingRecordGUI

