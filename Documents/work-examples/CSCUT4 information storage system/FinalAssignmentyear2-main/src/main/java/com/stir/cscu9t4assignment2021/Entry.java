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
public class Entry 
{
    private String title;
    private String publicationType;
    private String authors;
    private String publicationYear;
    private String publisherName;
    private String DOI;
    private String day;
    private String month;
    private String year;
    /**
     * Takes the user input and assigns it to different variables under type Entry
     * @param t title from user input
     * @param pt publication type from user input
     * @param au author from user input
     * @param py publication year from user input
     * @param pn publisher from user input
     * @param digital DOI from user input
     * @param d day from user input
     * @param m month from user input
     * @param y year from user input
     */
    public Entry (String t, String pt, String au, String py, String pn, String digital, String d, String m, String y )
    {
        title = t;
        publicationType = pt;
        authors = au;
        publicationYear = py;
        publisherName = pn;
        DOI = digital;
        day = d;
        month = m;
        year = y;
    } //Entry Constructor
    
    /**
     * Retrieves and returns the title from user input.
     * @return string title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Retrieves and returns the PublicationType from user input.
     * @return string PublicationType
     */
    public String getPublicationType()
    {
        return publicationType;
    }
    
    /**
     * Retrieves and returns the Authors from user input.
     * @return string Authors
     */
    public String getAuthors()
    {
        return authors;
    }
    
    /**
     * Retrieves and returns the PublicationYear from user input.
     * @return string PublicationYear
     */
    public String getPublicationYear()
    {
        return publicationYear;
    }
    
    /**
     * Retrieves and returns the PublisherName from user input.
     * @return string PublisherName
     */ 
    public String getPublisherName()
    {
        return publisherName;
    }
    
    /**
     * Retrieves and returns the DOI from user input.
     * @return string DOI
     */
    public String getDOI()
    {
        return DOI;
    }
    
    /**
     * Retrieves and returns the Day from user input.
     * @return string Day
     */
    public String getDay()
    {
        return day;
    }
    
    /**
     * Retrieves and returns the Month from user input.
     * @return string Month
     */
    public String getMonth()
    {
        return month;
    }
    
    /**
     * Retrieves and returns the Year from user input.
     * @return string Year
     */
    public String getYear()
    {
        return year;
    }
    
    /**
     * Takes getters from the same class and puts them into a user understandable String. 
     * @return string result a user understandable sentence to the output area
     */
    public String getEntryOutput()
    {
        String result = "Title: " + getTitle() + " - Type: " + getPublicationType() + " - Authors: " + getAuthors() + " - Year published: " +
                getPublicationYear() + " - Publisher name: " + getPublisherName() + " - Digital object ID: " + getDOI() + " | This entry was added " +
                getDay() + "/" + getMonth() + "/" + getYear() + "\n";
        return result;
    }
    
}
