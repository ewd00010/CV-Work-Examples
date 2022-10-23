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
public class ConferencePaperEntry extends Entry 
{ 
    private String conferenceLocation;
    /**
     * Takes the user input and assigns it to different variables under type ConferencePaperEntry.
     * Retrieves information for t, pt, au, py, pn, digital, d, m, y from its super class Entry.
     * @param t title from user input (taken from super class Entry)
     * @param pt publication type from user input (taken from super class Entry)
     * @param au author from user input (taken from super class Entry)
     * @param py publication year from user input (taken from super class Entry)
     * @param pn publisher from user input (taken from super class Entry)
     * @param digital DOI from user input (taken from super class Entry)
     * @param d day from user input (taken from super class Entry)
     * @param m month from user input (taken from super class Entry)
     * @param y year from user input (taken from super class Entry)
     * @param cl conference location from user input
     */
    public ConferencePaperEntry(String t, String pt, String au, String py, String pn, String digital, String d, String m, String y, String cl) 
    {
        super(t, pt, au, py, pn, digital, d, m, y);
        
        conferenceLocation = cl;
    }
    
    /**
     * Retrieves and returns the ConferenceLocation from user input.
     * @return string ConferenceLocation
     */    
    public String getConferenceLocation()
    {
        return conferenceLocation;
    }
    
    /**
     * Takes getters from conferencePaperEntry and its super class Entry and puts them into a user understandable String. 
     * @return string result a user understandable sentence to the output area
     */
    public String getEntryOutputCP()
    {
        String result = "Title: " + getTitle() + " - Type: " + getPublicationType() + " - Authors: " + getAuthors() + " - Year published: " +
                getPublicationYear() + " - Publisher name: " + getPublisherName() + " - Digital object ID: " + getDOI() + " - Conference location: " 
                + getConferenceLocation() + " | This entry was added " + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
        return result;
    }
    
}
