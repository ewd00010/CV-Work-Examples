/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stir.cscu9t4assignment2021;

/**
 *
 * @author User
 */
public class JournalPaperEntry extends Entry 
{
    private int volume;
    private int issue;
    /**
     * Takes the user input and assigns it to different variables under type JournalPaperEntry.
     * Retrieves information for t, pt, au, py, pn, digital, d, m, y from its super class Entry.
     * @param t title from user input (taken from super class Entry)
     * @param pt publication type from user input (taken from super class Entry)
     * @param au author from user input(taken from super class Entry)
     * @param py publication year from user input (taken from super class Entry)
     * @param pn publisher from user input (taken from super class Entry)
     * @param digital DOI from user input (taken from super class Entry)
     * @param d day from user input (taken from super class Entry)
     * @param m month from user input (taken from super class Entry)
     * @param y year from user input (taken from super class Entry)
     * @param vol volume from user input
     * @param iss issue from user input
     */
    public JournalPaperEntry(String t, String pt, String au, String py, String pn, String digital, String d, String m, String y, int vol, int iss) 
    {
        super(t, pt, au, py, pn, digital, d, m, y);
        
         volume = vol;
         issue = iss;
    }
    
    /**
     * Retrieves and returns the Volume from user input.
     * @return string Volume
     */
    public int getVolume()
    {
        return volume;
    }
    
    /**
     * Retrieves and returns the Issue from user input.
     * @return string Issue
     */
    public int getIssue()
    {
        return issue;
    }
    
    /**
     * Takes getters from JournalPaperEntry and its super class Entry and puts them into a user understandable String. 
     * @return string result a user understandable sentence to the output area
     */
    public String getEntryOutputJP()
    {
        String result = "Title: " + getTitle() + " - Type: " + getPublicationType() + " - Authors: " + getAuthors() + " - Year published: " +
                getPublicationYear() + " - Publisher name: " + getPublisherName() + " - Digital object ID: " + getDOI() + " - Issue: " + getIssue()
                + " - Volume: " + getVolume() + " | This entry was added " + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
        return result;
    }
}
