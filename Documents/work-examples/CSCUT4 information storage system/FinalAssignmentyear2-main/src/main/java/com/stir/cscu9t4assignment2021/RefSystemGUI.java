/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stir.cscu9t4assignment2021;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author saemundur, 2810164
 */
public class RefSystemGUI extends JFrame implements ActionListener 
{
    protected ButtonGroup publicationType = new ButtonGroup(); // group to choose publication type
    protected JRadioButton journalPaper = new JRadioButton();
    protected JRadioButton conferencePaper = new JRadioButton();
    protected JRadioButton bookChapter = new JRadioButton();
    protected JRadioButton allEntries = new JRadioButton();
    protected JTextField title = new JTextField(40);
    protected JTextField authors = new JTextField(60);
    protected JTextField publicationYear = new JTextField(10);
    protected JTextField publisherName = new JTextField(40);
    protected JTextField digitalObjectIdentifier = new JTextField(40);
    protected JTextField dayAdded = new JTextField(2);
    protected JTextField monthAdded = new JTextField(2);
    protected JTextField yearAdded = new JTextField(4);
    protected JTextField volume = new JTextField(3);
    protected JTextField issue = new JTextField(3);
    protected JTextField conferenceName = new JTextField(60);
    protected JTextField conferenceLocation = new JTextField(60);
    protected JTextField editor = new JTextField(20);
    protected JLabel labTitle = new JLabel(" Title: ");
    protected JLabel labJournalPaper = new JLabel(" Journal Paper ");
    protected JLabel labConferencePaper = new JLabel(" Conference Paper ");
    protected JLabel labBookChapter = new JLabel(" Book Chapter ");
    protected JLabel labAllEntries = new JLabel("All Entries");
    protected JLabel labAuthors = new JLabel(" Authors: ");
    protected JLabel labPubYear = new JLabel(" Publication year: ");
    protected JLabel labPubName = new JLabel(" Publication name: ");
    protected JLabel labDOI = new JLabel(" Digital Object Identifier: ");
    protected JLabel labDateAdded = new JLabel(" Date added: ");
    protected JLabel labVolume = new JLabel(" Volume: ");
    protected JLabel labIssue = new JLabel(" Issue: ");
    protected JLabel labConferenceName = new JLabel(" Conference name: ");
    protected JLabel labConferenceLocation = new JLabel(" Conference location: ");
    protected JLabel labEditor = new JLabel(" Editor: ");
    // GUI version buttons initailizes the Gui elements for the user to work with none GUI buttons
    protected JButton GUIAddEntry = new JButton("Add a new entry");
    protected JButton GUIDeleteEntry = new JButton("Delete a entry");
    protected JButton GUILookUpEntry = new JButton("Look up a entry");
    protected JButton GUIImportEntries = new JButton("Import new entries");
    protected JButton GUIExportEntries = new JButton("Export existing entries");
    protected JButton addEntry = new JButton(" Create entry record ");
    protected JButton lookUpEntry = new JButton(" Look up entry record ");
    protected JButton deleteEntry = new JButton("Delete entry record");
    protected JButton importEntries = new JButton("Import entries records");
    protected JButton exportEntries = new JButton("Export entries records");
    
    private JTextArea outputArea = new JTextArea(40, 170);
    
    private RefCollection bibliography = new RefCollection();
    
    public static void main(String[] args) 
    {
        RefSystemGUI applic = new RefSystemGUI();
    }
    
    /**
     * Handles the implementation of the GUI size and features.
     * Sends "void" to GUIHandler("") which sets the correct elements as visible.
     */
    public RefSystemGUI() 
    {
        super("Bibliography");
        setLayout(new FlowLayout());
        publicationType.add(journalPaper);
        publicationType.add(conferencePaper);
        publicationType.add(bookChapter);
        publicationType.add(allEntries);
        add(journalPaper);
        add(labJournalPaper);
        journalPaper.setSelected(true);
        add(conferencePaper);
        add(labConferencePaper);
        add(bookChapter);
        add(labBookChapter);
        add(allEntries);
        add(labAllEntries);
        add(labTitle);
        add(title);
        title.setEditable(true);
        add(labPubYear);
        add(publicationYear);
        publicationYear.setEditable(true);
        add(labPubName);
        add(publisherName);
        publisherName.setEditable(true);
        add(labDOI);
        add(digitalObjectIdentifier);
        digitalObjectIdentifier.setEditable(true);
        add(labDateAdded);
        add(dayAdded);
        dayAdded.setEditable(true);
        add(monthAdded);
        monthAdded.setEditable(true);
        add(yearAdded);
        yearAdded.setEditable(true);
        add(labAuthors);
        add(authors);
        authors.setEditable(true);
        add(labVolume);
        add(volume);
        volume.setEditable(true);
        add(labIssue);
        add(issue);
        issue.setEditable(true);
        add(labConferenceName);
        add(conferenceName);
        conferenceName.setEditable(true);
        add(labConferenceLocation);
        add(conferenceLocation);
        conferenceLocation.setEditable(true);
        add(labEditor);
        add(editor);
        editor.setEditable(true);
        
        GUIAddEntry.addActionListener(this);
        add(GUIAddEntry);
        GUIDeleteEntry.addActionListener(this);
        add(GUIDeleteEntry);
        GUILookUpEntry.addActionListener(this);
        add(GUILookUpEntry);
        GUIImportEntries.addActionListener(this);
        add(GUIImportEntries);
        GUIExportEntries.addActionListener(this);
        add(GUIExportEntries);
        addEntry.addActionListener(this);
        add(addEntry);
        lookUpEntry.addActionListener(this);
        add(lookUpEntry);
        deleteEntry.addActionListener(this);
        add(deleteEntry);
        importEntries.addActionListener(this);
        add(importEntries);
        exportEntries.addActionListener(this);
        add(exportEntries);
        add(outputArea);
        outputArea.setEditable(false);
        setSize(1500, 1500);
        setVisible(true);
        
        blankDisplay();
        
        String message = "void";
        GUIHandler(message);
        
    }
    
    /**
     * Handles the visibility of the main GUI options which allow for the different features interfaces to be shown.
     * 
     * @param setting a Boolean which sets this group of buttons to be visible if true or not visible if false
     */
    public void optionsGUISwitch(boolean setting)
    {
        GUIAddEntry.setVisible(setting);
        GUIDeleteEntry.setVisible(setting);
        GUILookUpEntry.setVisible(setting);
        GUIImportEntries.setVisible(setting);
        GUIExportEntries.setVisible(setting);
    }
    
    /**
     * Handles the visibility of the GUI options that allow for input from the user.
     * 
     * @param setting specifies if the GUI elements should be visible or not
     */
    public void inputGUISwitch(boolean setting)
    {
        
        title.setVisible(setting);
        labTitle.setVisible(setting);
        authors.setVisible(setting);
        labAuthors.setVisible(setting);
        publicationYear.setVisible(setting);
        labPubYear.setVisible(setting);
        publisherName.setVisible(setting);
        labPubName.setVisible(setting);
        digitalObjectIdentifier.setVisible(setting);
        labDOI.setVisible(setting);
        journalPaper.setVisible(setting);
        labJournalPaper.setVisible(setting);
        conferencePaper.setVisible(setting);
        labConferencePaper.setVisible(setting);
        bookChapter.setVisible(setting);
        labBookChapter.setVisible(setting);
        dayAdded.setVisible(setting);
        monthAdded.setVisible(setting);
        yearAdded.setVisible(setting);
        labDateAdded.setVisible(setting); 
    }
            
    /**
     * The main GUI handler method which controls the GUI at different points of time during the applications lifespan.
     * Elements of the GUI are visible after a press of one of the main GUI buttons like "Add a new entry" 
     * 
     * @param message specifies the button pushed with its phrase
     */
    public void GUIHandler(String message)
    {
        inputGUISwitch(false);
        
        volume.setVisible(false);
        labVolume.setVisible(false);
        issue.setVisible(false);
        labIssue.setVisible(false);
        conferenceName.setVisible(false);
        labConferenceName.setVisible(false);
        conferenceLocation.setVisible(false);
        labConferenceLocation.setVisible(false);
        editor.setVisible(false);
        labEditor.setVisible(false);
        allEntries.setVisible(false);
        labAllEntries.setVisible(false);
        addEntry.setVisible(false);
        deleteEntry.setVisible(false);
        lookUpEntry.setVisible(false);
        importEntries.setVisible(false);
        exportEntries.setVisible(false);
        
        
        if(message.equals("Please enter the information you want to add"))
        {
            inputGUISwitch(true);
            optionsGUISwitch(false);
            
            volume.setVisible(true);
            labVolume.setVisible(true);
            issue.setVisible(true);
            labIssue.setVisible(true);
            
            conferenceName.setVisible(true);
            labConferenceName.setVisible(true);
            conferenceLocation.setVisible(true);
            labConferenceLocation.setVisible(true);
            
            editor.setVisible(true);
            labEditor.setVisible(true);
        
            addEntry.setVisible(true);
        }
        else if(message.equals("Please enter the information you want to find"))
        {
            inputGUISwitch(true);
            optionsGUISwitch(false);
            
            lookUpEntry.setVisible(true);
        }
        else if(message.equals("Please enter the information you want to delete"))
        {
            inputGUISwitch(false);
            optionsGUISwitch(false);
            
            title.setVisible(true);
            labTitle.setVisible(true);
            
            journalPaper.setVisible(true);
            labJournalPaper.setVisible(true);
            conferencePaper.setVisible(true);
            labConferencePaper.setVisible(true);
            bookChapter.setVisible(true);
            labBookChapter.setVisible(true);
            
            deleteEntry.setVisible(true);
        }
        else if(message.equals("Please enter the import file name"))
        {
            inputGUISwitch(false);
            optionsGUISwitch(false);
            
            importEntries.setVisible(true);
        }
        else if(message.equals("Please enter the export file name and type"))
        {
            inputGUISwitch(false);
            optionsGUISwitch(false);
            
            journalPaper.setVisible(true);
            labJournalPaper.setVisible(true);
            conferencePaper.setVisible(true);
            labConferencePaper.setVisible(true);
            bookChapter.setVisible(true);
            labBookChapter.setVisible(true);
            allEntries.setVisible(true);
            labAllEntries.setVisible(true);
            
            exportEntries.setVisible(true);
        }
    }
    
    /**
     * Upon pressing a button element in the GUI this class takes the event and tells the application what process it should follow.
     * 
     * @param event specifies which button has been pushed
     */
    public void actionPerformed(ActionEvent event) 
    {
        String message = "";
        if(event.getSource() == GUIAddEntry)
        {
            System.out.println("adding started");
            message = "Please enter the information you want to add";
            GUIHandler(message);
        }
        
        if(event.getSource() == GUILookUpEntry)
        {
            System.out.println("Look Up started");
            message = "Please enter the information you want to find";
            GUIHandler(message);
        }
        
        if(event.getSource() == GUIDeleteEntry)
        {
            System.out.println("Deleteion started");
            message = "Please enter the information you want to delete";
            GUIHandler(message);
        }
        
        if(event.getSource() == GUIImportEntries)
        {
            System.out.println("Import started");
            message = "Please enter the import file name";
            GUIHandler(message);
        }
        
        if(event.getSource() == GUIExportEntries)
        {
            System.out.println("Export started");
            message = "Please enter the export file name and type";
            GUIHandler(message);
        }
        
        if(event.getSource() == addEntry)
        {
            System.out.println("adding to records");
            message = addEntry();
        }
        
        if(event.getSource() == lookUpEntry)
        {
            System.out.println("looking up entry");
            message = lookUpEntry();
        }
        
        if(event.getSource() == deleteEntry)
        {
            System.out.println("deleteing entry");
            message = deleteEntry(); 
        }
        
        if(event.getSource() == importEntries)
        {
            System.out.println("importing entries");
            message = importEntries();
        }
        if(event.getSource() == exportEntries)
        {
            System.out.println("exporting entries");
            message = exportEntries();
        }
        
        outputArea.setText(message);
        
        blankDisplay();
        // this if statement checks if the user has completed the task they wanted and resets the GUI to its original state
        if(event.getSource() == addEntry || event.getSource() == lookUpEntry ||  event.getSource() == deleteEntry ||  event.getSource() == importEntries ||  event.getSource() == exportEntries)
        {
            message = "void";
            GUIHandler(message);
            optionsGUISwitch(true);
        }
    }
    
    /**
     * Checks which type of publication is currently selected by the user.
     * Value of pt will be based on which radio button is currently selected.
     * 
     * @return string pt as either "Journal Paper", "Conference Paper", "Book Chapter" or "All"
     */
    public String typeChecker()
    {
        String pt = "";
        if(journalPaper.isSelected())
        {
            pt = "Journal Paper";
        }
        else if(conferencePaper.isSelected())
        {
            pt = "Conference Paper";
        }
        else if(bookChapter.isSelected())
        {
            pt = "Book Chapter";
        }
        else
        {
            pt = "ALL";
        }
        return pt;
    }
    
    /**
     * Initializes the RefCollection method addJournalPaperEntry(), addConferencePaperEntry(), addBookChapterEntry() after
     * creating a new Entry based on publication type.
     * Takes Strings and ints from the user, adds them to JPEntryRecord in RefCollection and returns the information as
     * getEntryOutput for the user to read and check to make sure the details entered are correct.
     * 
     * @return string result as one of the 3 subclass versions of getEntryOutput()
     */
    public String addEntry()
    {
        String result = "Entry failed";
        System.out.println("adding to records");
        String t = title.getText();
        String au = authors.getText();
        String pt;
        String py = publicationYear.getText();
        String pn = publisherName.getText();
        String digital = digitalObjectIdentifier.getText();
        String day = dayAdded.getText();
        String month = monthAdded.getText();
        String year = yearAdded.getText();
        
        pt = typeChecker();
        switch(pt) // decides which record the entry should be added to, taking more information from user dependant on case
        {
            case "Journal Paper":
            {
                int vol = Integer.parseInt(volume.getText());
                int iss = Integer.parseInt(issue.getText());
                
                JournalPaperEntry info = new JournalPaperEntry(t, pt, au, py, pn, digital, day, month, year, vol, iss);
                bibliography.addJournalPaperEntry(info);
                result = info.getEntryOutputJP(); // returning User readable String
                break;
            }
            case "Conference Paper":
            {
                String cl = conferenceLocation.getText();
                
                ConferencePaperEntry info = new ConferencePaperEntry(t, pt, au, py, pn, digital, day, month, year, cl);
                bibliography.addConferencePaperEntry(info);
                result = info.getEntryOutputCP();
                break;
            }
            case "Book Chapter":
            {
                String ed = editor.getText();
                
                BookChapterEntry info = new BookChapterEntry(t, pt, au, py, pn, digital, day, month, year, ed);
                bibliography.addBookChapterEntry(info);
                result = info.getEntryOutputBC();
                break;
            }
        } // end of switch
        
        return result;
    }
    
    /**
     * Initializes the RefCollection method lookUpEntry().
     * Takes strings from the user and searches for the information in the records to then return the information to the user.
     * @return string message returns one or more entry/ies in there publication type getEntryOutput form
     */
    public String lookUpEntry()
    {
        String pt;
        String t = title.getText();
        String au = authors.getText();
        String py = publicationYear.getText();
        String pn = publisherName.getText();
        String digital = digitalObjectIdentifier.getText();
        String d = dayAdded.getText();
        String m = monthAdded.getText();
        String y = yearAdded.getText();
        
        outputArea.setText("looking up record...");
        
        pt = typeChecker();
        
        String message = bibliography.lookUpEntry(t, pt, au, py, pn, digital, d, m, y); 
        return message;
    }
    
    /**
     * Initializes the RefCollection method deleteEntry().
     * Takes the publication type and title from the user and deletes the entry it finds.
     * @return string message returns "Delete 'x'" with x being the title, if unsuccessful it returns "No entry exists"
     */
    public String deleteEntry()
    {
        String pt;
        String t = title.getText();
        
        outputArea.setText("looking for info to ommit...");
        
        pt = typeChecker();
        
        String message = bibliography.deleteEntry(t, pt);
        return message;
    }
    
    /**
     * Initializes the RefCollection method entryImporter().
     * Imports entries from file selected in code.
     * @return string message returns "Total Entries imported: 'x'" with x being the amount imported
     */
    public String importEntries()
    {
        outputArea.setText("importing entries...");
        
        String message = bibliography.EntryImporter();
        return message; 
    }
    
    /**
     * Initializes the RefCollection method entryExporter().
     * Exports all entries of the selected type or all types at once.
     * @return string message returns "Exported 'x' entries ( 'y' entries)" with x being the amount exported and y being the publication type or all
     */
    public String exportEntries()
    {
        String pt = typeChecker();
        
        outputArea.setText("exporting entries...");
        
        String message = bibliography.EntryExporter(pt);
        return message;
    }
    
    /**
     * Clears the display after each feature use to save the user from deleted old info to add new info.
     */
    public void blankDisplay() 
    {
        title.setText("");
        journalPaper.setSelected(true);
        authors.setText("");
        publicationYear.setText("");
        publisherName.setText("");
        digitalObjectIdentifier.setText("");
        dayAdded.setText("");
        monthAdded.setText("");
        yearAdded.setText("");
        volume.setText("");
        issue.setText("");
        conferenceName.setText("");
        conferenceLocation.setText("");
        editor.setText("");
    }
}