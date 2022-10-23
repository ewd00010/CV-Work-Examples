/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stir.cscu9t4assignment2021;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 2810164
 */
public class RefCollection {
    
    // at a later point maybe add a check to see if the entry has already been entered
    int count;
    String outputStore;
    
    List<JournalPaperEntry> JPEntryRecord;
    List<ConferencePaperEntry> CPEntryRecord;
    List<BookChapterEntry> BCEntryRecord;
    /**
     * Initializes JPEntryRecord, CPEntryRecord and BCEntryRecord as ArrayLists to store each types entries.
     */
    public RefCollection() 
    {
        JPEntryRecord = new ArrayList<>();
        CPEntryRecord = new ArrayList<>();
        BCEntryRecord = new ArrayList<>();
    } // constructor
    
    /**
     * Takes information gained in RefSystemGUI from addEntry() and puts it into the correct record
     * @param info journalPaperEntry info takes the parameters (title, publication type, authors, publication year, publisher, DOI, day, month, year, volume, issue)
     */
    public void addJournalPaperEntry(JournalPaperEntry info)
    {    
        System.out.println("adding to jp");
        JPEntryRecord.add(info);
    }
    
    /**
     * Takes information gained in RefSystemGUI from addEntry() and puts it into the correct record
     * @param info conferencePaperEntry info takes the parameters (title, publication type, authors, publication year, publisher, DOI, day, month, year, conference location)
     */
    public void addConferencePaperEntry(ConferencePaperEntry info)
    {
        System.out.println("adding to cp");
        CPEntryRecord.add(info);
    }
    
    /**
     * Takes information gained in RefSystemGUI from addEntry() and puts it into the correct record
     * @param info BookChapterEntry info takes the parameters (title, publication type, authors, publication year, publisher, DOI, day, month, year, editor)
     */
    public void addBookChapterEntry(BookChapterEntry info)
    {
        System.out.println("adding to bc");
        BCEntryRecord.add(info);
    }
    
    /**
     * Takes the user input to find the entries for which the user search matches.
     * Acts depending on which of the 3 publication types its told.
     * Joins these in form "'x1' \n 'x2'" with x1 and x2 being entries converted into there respected getEntryOutput type form.
     * @param entryIter iterator of one of the 3 types, version depends on the type selected
     * @param t title from user input
     * @param pt publication type from user input
     * @param au author from user input
     * @param py publication year from user input
     * @param pn publisher from user input
     * @param digital DOI from user input
     * @param d day from user input
     * @param m month from user input
     * @param y year from user input
     * @return string result returns the entries in form "'x1' \n 'x2'" with x1 and x2 being entries converted into there respected getEntryOutput type form.
     */
    public String EntryChecker(ListIterator entryIter, String t, String pt, String au, String py, String pn, String digital, String d, String m, String y )
    {
        String resultWorking = "";
        String output = "";
        switch(pt) // controls current variables type
        {
            case "Journal Paper":
            {
                while(entryIter.hasNext())
                {
                    JournalPaperEntry current = (JournalPaperEntry) entryIter.next();
                    System.out.println("checker1");
                    if(!t.equals("") && current.getTitle().equals(t))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputJP();
                        entryIter.remove();
                    }
                    else if(!au.equals("") && current.getAuthors().equals(au))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputJP();
                        entryIter.remove();
                    }
                    else if(!py.equals("") && current.getPublicationYear().equals(py))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputJP();
                        entryIter.remove();
                    }
                    else if(!pn.equals("") && current.getPublisherName().equals(pn))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputJP();
                        entryIter.remove();
                    }
                    else if(!digital.equals("")  && current.getDOI().equals(digital))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputJP();
                        entryIter.remove();
                    }
                    else if(!y.equals("")  && current.getYear().equals(y))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputJP();
                        entryIter.remove();
                    }

                    if(!entryIter.hasNext())
                    {
                        break;
                    }
                }
                break;
            }
            case "Conference Paper":
            {
                while(entryIter.hasNext())
                {
                    ConferencePaperEntry current = (ConferencePaperEntry) entryIter.next();
                    System.out.println("checker1");
                    if(!t.equals("") && current.getTitle().equals(t))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputCP();
                        entryIter.remove();
                    }
                    else if(!au.equals("") && current.getAuthors().equals(au))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputCP();
                        entryIter.remove();
                    }
                    else if(!py.equals("") && current.getPublicationYear().equals(py))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputCP();
                        entryIter.remove();
                    }
                    else if(!pn.equals("") && current.getPublisherName().equals(pn))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputCP();
                        entryIter.remove();
                    }
                    else if(!digital.equals("")  && current.getDOI().equals(digital))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputCP();
                        entryIter.remove();
                    }
                    else if(!y.equals("")  && current.getYear().equals(y))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputCP();
                        entryIter.remove();
                    }

                    if(!entryIter.hasNext())
                    {
                        break;
                    }
                }
                break;
            }
            case "Book Chapter":
            {
                while(entryIter.hasNext())
                {
                    BookChapterEntry current = (BookChapterEntry) entryIter.next();
                    System.out.println("checker1");
                    if(!t.equals("") && current.getTitle().equals(t))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputBC();
                        entryIter.remove();
                    }
                    else if(!au.equals("") && current.getAuthors().equals(au))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputBC();
                        entryIter.remove();
                    }
                    else if(!py.equals("") && current.getPublicationYear().equals(py))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputBC();
                        entryIter.remove();
                    }
                    else if(!pn.equals("") && current.getPublisherName().equals(pn))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputBC();
                        entryIter.remove();
                    }
                    else if(!digital.equals("")  && current.getDOI().equals(digital))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputBC();
                        entryIter.remove();
                    }
                    else if(!y.equals("")  && current.getYear().equals(y))
                    {
                        resultWorking = resultWorking + " \n" + current.getEntryOutputBC();
                        entryIter.remove();
                    }

                    if(!entryIter.hasNext())
                    {
                        break;
                    }
                }
                break;
            }    
        } // end of switch
        return resultWorking;
    }
        
    /**
     * Works with EntryChecker to find and return Entries related to the user search.
     * @param t title from user input
     * @param pt publication type from user input
     * @param au author from user input
     * @param py publication year from user input
     * @param pn publisher from user input
     * @param digital DOI from user input
     * @param d day from user input
     * @param m month from user input
     * @param y year from user input
     * @return string result returns the results from EntryChecker, otherwise it returns "no entry found"
     */
    public String lookUpEntry(String t, String pt, String au, String py, String pn, String digital, String d, String m, String y )
    {
        String result = "no entry found";
        String resultworking = "";
        switch(pt) // decides what type entryIter should be
        {    
            case "Journal Paper":
            {
                ListIterator<JournalPaperEntry> entryIter = JPEntryRecord.listIterator();

                resultworking = EntryChecker(entryIter, t, pt, au, py, pn, digital, d, m, y);
                break;
            }
        
            case "Conference Paper":
            {
                ListIterator<ConferencePaperEntry> entryIter = CPEntryRecord.listIterator();

                resultworking = EntryChecker(entryIter, t, pt, au, py, pn, digital, d, m, y);
                break;
            }
            case "Book Chapter":
            {
                ListIterator<BookChapterEntry> entryIter = BCEntryRecord.listIterator();

                resultworking = EntryChecker(entryIter, t, pt, au, py, pn, digital, d, m, y);
                break;
            }
        } // end of switch
        if(!resultworking.equals(""))
        {
            result = resultworking;
            System.out.println(result);
        }
        return result;
    }
    
    /**
     * Takes the user input of title and publication type to find and remove the said entry.
     * @param t title from user input
     * @param pt publication type from user input
     * @return string result returns "deleted 'x'" with x being the title of the entry, otherwise returns "no entry exists"
     */
    public String deleteEntry(String t, String pt) 
    {
        int index = 0;
        String result = "no entry exists";

        switch(pt) // decides which record to delete from
        {
            case "Journal Paper":
            {
                ListIterator<JournalPaperEntry> entryIter = JPEntryRecord.listIterator();
                while(entryIter.hasNext())
                {

                    Entry current = entryIter.next();
                    System.out.println("checkerDelete1");

                    if(current.getTitle().equals(t))
                    {
                        JPEntryRecord.remove(index);
                        result = "Deleted " + current.getTitle();
                        break;
                    }
                    index++;
                }
                break;
            }

            case "Conference Paper":
            {
                ListIterator<ConferencePaperEntry> entryIter = CPEntryRecord.listIterator();
                while(entryIter.hasNext())
                {

                    Entry current = entryIter.next();
                    System.out.println("checkerDelete2");

                    if(current.getTitle().equals(t))
                    {
                        CPEntryRecord.remove(index);
                        result = "Deleted " + current.getTitle();
                        break;
                    }
                    index++;
                }
                break;
            }

            case "Book Chapter":
            {
                ListIterator<BookChapterEntry> entryIter = BCEntryRecord.listIterator();
                while(entryIter.hasNext())
                {

                    Entry current = entryIter.next();
                    System.out.println("checkerDelete3");

                    if(current.getTitle().equals(t))
                    {
                        BCEntryRecord.remove(index);
                        result = "Deleted " + current.getTitle();
                        break;
                    }
                    index++;
                }
                break;
            }
        } // end of switch

        return result;
    }
    
    /**
     * Checks each line of the designated file and returns the input in a as one of the 3 publication types dependant on if the unique info for any of the 3 is shown.
     * It achieves this by splitting each line into readable Strings and changes Strings to int where necessary, it then assigns each String/int to a value.
     * 
     * @return string message returns "Total entries imported: 'x'" where x is amount imported
     */
    public String EntryImporter()
    {
        String t;
        String au;
        String pt;
        String py;
        String pn;
        String digital;
        String day;
        String month;
        String year;
        int vol;
        int iss;
        String cl;
        String ed;

        String message;

        count = 0;
        int entryAmount = 0;

        try
        {
            File inputFile = new File("C:\\Users\\User\\Documents\\GitHub\\FinalAssignmentyear2\\files\\all_data.csv");
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine())
            {
                if(count == 0)
                {
                    input.nextLine();
                }
                count++;

                String details = input.nextLine();
                var detailsDivider = details.split(","); // splitting the line to individual strings
                    
                    var DateSplit = detailsDivider[5].split("/"); // splitting the date to 3 different numbers
                    // assigning strings to variables
                    t = detailsDivider[0];
                    au = detailsDivider[1];
                    py = detailsDivider[2];
                    pn = detailsDivider[3];
                    digital = detailsDivider[4];
                    day = DateSplit[0];
                    month = DateSplit[1];
                    year = DateSplit[2];

                    if(!detailsDivider[6].isEmpty()) // index 6 must have details to be a JournalPaperEntry
                    {
                        pt = "Journal Paper";
                        vol = Integer.parseInt(detailsDivider[7]);
                        iss = Integer.parseInt(detailsDivider[8]);

                        JournalPaperEntry info = new JournalPaperEntry(t, pt, au, py, pn, digital, day, month, year, vol, iss);
                        JPEntryRecord.add(info);
                        entryAmount++;
                    }
                    else if(!detailsDivider[9].isEmpty()) // index 9 must have details to be a ConferencePaperEntry
                    {
                        pt = "Conference Paper";
                        cl = detailsDivider[10];

                        ConferencePaperEntry info = new ConferencePaperEntry(t, pt, au, py, pn, digital, day, month, year, cl);
                        CPEntryRecord.add(info);
                        entryAmount++;
                    }
                    else if(!detailsDivider[11].isEmpty()) // index 11 must have details to be a BookChapterEntry
                    {
                        pt = "Book Chapter";
                        ed = detailsDivider[12];

                        BookChapterEntry info = new BookChapterEntry(t, pt, au, py, pn, digital, day, month, year, ed);
                        BCEntryRecord.add(info);
                        entryAmount++;
                    }

                } 

            input.close();
        }
        catch (FileNotFoundException ex) // throws exception if the file allocated does not exist
            {
                Logger.getLogger(RefCollection.class.getName()).log(Level.SEVERE, null, ex);
                System.out.printf("Error: %s \n", ex);
            }
        message = "Total entries imported: " + entryAmount;
        return message;
    }       

    /**
     * Takes the entries in the selected record/s and exports them to a file.
     * Creates iterator versions of records to achieve this.
     * @param exportType one of the 3 publication types or all types
     * @return string message returns "Exported 'x' entries ( 'y' entries)" with x being the amount exported and y being the publication type or all
     */
    public String EntryExporter(String exportType)
    {
        int JPCount = JPEntryRecord.size();
        int CPCount = CPEntryRecord.size();
        int BCCount = BCEntryRecord.size();
        int ALLCount = JPCount + CPCount + BCCount;
        
        ListIterator<JournalPaperEntry> JPentryIter = JPEntryRecord.listIterator();
        ListIterator<ConferencePaperEntry> CPEntryIter = CPEntryRecord.listIterator();
        ListIterator<BookChapterEntry> BCEntryIter = BCEntryRecord.listIterator();
        
        String message = "no entries exported";
        
        try
        {
            FileWriter outputFileWriter = new FileWriter("C:\\Users\\User\\Documents\\GitHub\\FinalAssignmentyear2\\files\\OutputFile.txt");
            PrintWriter outputFile = new PrintWriter(outputFileWriter);
            
            outputStore = "";
            
            if(exportType.equals("ALL"))
            {
                message = "Exported " + ALLCount + " entries (All entries)";
                
                while(ALLCount != 0)
                {
                    while(JPCount != 0 && JPentryIter.hasNext())
                    {
                        JournalPaperEntry current = JPentryIter.next();
                        outputStore = current.getEntryOutput();
                        outputFile.write(outputStore + "\n");
                        JPCount--;
                        ALLCount--;
                    }
                    while(CPCount != 0 && CPEntryIter.hasNext())
                    {
                        ConferencePaperEntry current = CPEntryIter.next();
                        outputStore = current.getEntryOutput();
                        outputFile.write(outputStore + "\n");
                        CPCount--;
                        ALLCount--;
                    }
                    while(BCCount != 0 && BCEntryIter.hasNext())
                    {
                        BookChapterEntry current = BCEntryIter.next();
                        outputStore = current.getEntryOutput();
                        outputFile.write(outputStore + "\n");
                        BCCount--;
                        ALLCount--;
                    }
                }
            }
            else if(exportType.equals("Journal Paper"))
            {
                message = "Exported " + JPCount + " entries (Journal Paper entries)";
                
                while(JPCount != 0 && JPentryIter.hasNext())
                {
                    JournalPaperEntry current = JPentryIter.next();
                    outputStore = current.getEntryOutput();
                    outputFile.write(outputStore + "\n");
                    JPCount--;
                }
            }
            else if(exportType.equals("Conference Paper"))
            {
                message = "Exported " + CPCount + " entries (Conference Paper entries)";
                
                while(CPCount != 0 && CPEntryIter.hasNext())
                {
                    ConferencePaperEntry current = CPEntryIter.next();
                    outputStore = current.getEntryOutput();
                    outputFile.write(outputStore + "\n");
                    CPCount--;
                }
            }
            else if(exportType.equals("Book Chapter"))
            {
                message = "Exported " + BCCount + " entries (Book Chapter entries)";
                
                 while(BCCount != 0 && BCEntryIter.hasNext())
                {
                    BookChapterEntry current = BCEntryIter.next();
                    outputStore = current.getEntryOutput();
                    outputFile.write(outputStore + "\n");
                    BCCount--;
                }
            }
            outputFile.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(RefCollection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Error %s \n");   
        }
        
    return message;
    }
}
