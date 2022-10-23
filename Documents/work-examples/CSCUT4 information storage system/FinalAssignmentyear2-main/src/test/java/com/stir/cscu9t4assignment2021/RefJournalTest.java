/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stir.cscu9t4assignment2021;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author saemundur
 */
public class RefJournalTest 
{
    
    public RefJournalTest() 
    {
    }
    
    @BeforeAll
    public static void setUpClass() 
    {
    }
    
    @AfterAll
    public static void tearDownClass() 
    {
    }
    
    @BeforeEach
    public void setUp() 
    {
    }
    
    @AfterEach
    public void tearDown() 
    {
    }

    /**
     * Test of getEntryOutputJP method, of class JournalPaperEntry.
     */
    @Test
    public void testGetEntryOutputJP() 
    {
        System.out.println("getEntryOutputJP");
        String title = "Some Journal Paper";
        String book = "Journal Paper";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        int issue = 12;
        int volume = 23;
        JournalPaperEntry instance = new JournalPaperEntry(title, book, authors, pubyear, publisher, doi, day, month, year, volume, issue);
        String expResult = "Title: Some Journal Paper - Type: Journal Paper - Authors: Saemi Haraldsson, Ragnheidur Brynjolfsdottir - Year published: 2002 -"
                           + " Publisher name: Springer - Digital object ID: 10.123456/9876 - Issue: 12 - Volume: 23 | This entry was added 1/1/2021\n";
        
        String result = instance.getEntryOutputJP();
        
        assertEquals(expResult, result); 
    }

    /**
     * Test of getIssue method, of class JournalPaperEntry.
     */
    @Test
    public void testGetIssue() 
    {
        System.out.println("getIssue");
        String title = "Some Journal Paper";
        String book = "Conference Paper";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        int issue = 12;
        int volume = 23;
        JournalPaperEntry instance = new JournalPaperEntry(title, book, authors, pubyear, publisher, doi, day, month, year, volume, issue);
        int expResult = 12;
        
        int result = instance.getIssue();
        
        assertEquals(expResult, result); 
    }
    
    /**
     * Test of getVolume method, of class JournalPaperEntry.
     */
    @Test
    public void testGetVolume() 
    {
        System.out.println("getVolume");
        String title = "Some Journal Paper";
        String book = "Conference Paper";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        int issue = 12;
        int volume = 23;
        JournalPaperEntry instance = new JournalPaperEntry(title, book, authors, pubyear, publisher, doi, day, month, year, volume, issue);
        int expResult = 23;
        
        int result = instance.getVolume();
        
        assertEquals(expResult, result); 
    }
    
}
