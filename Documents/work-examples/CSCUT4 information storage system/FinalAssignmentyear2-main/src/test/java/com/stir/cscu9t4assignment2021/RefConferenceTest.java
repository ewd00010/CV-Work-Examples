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
public class RefConferenceTest {
    
    public RefConferenceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getEntryOutputCP method, of class RefConference.
     */
    @Test
    public void testGetEntryOutputCP() {
        System.out.println("getEntryOutputCP");
        String title = "Some Conference Paper";
        String book = "Conference Paper";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String editor = "Scooby Doo";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        String conferenceLoc = "at home";
        ConferencePaperEntry instance = new ConferencePaperEntry(title, book, authors, pubyear, publisher, doi, day, month, year, conferenceLoc);
        String expResult = "Title: Some Conference Paper - Type: Conference Paper - Authors: Saemi Haraldsson, Ragnheidur Brynjolfsdottir - Year published: 2002 -"
                           + " Publisher name: Springer - Digital object ID: 10.123456/9876 - Conference location: at home | This entry was added 1/1/2021\n";
        
        String result = instance.getEntryOutputCP();
        
        assertEquals(expResult, result); 
    }

    /**
     * Test of getConferenceLocation method, of class ConferencePaperEntry.
     */
    @Test
    public void testGetConferenceLocation() {
        System.out.println("getConferenceLocation");
        String title = "Conference Paper";
        String book = "Conference Paper";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String editor = "Scooby Doo";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        String conferenceLoc = "at home";
        ConferencePaperEntry instance = new ConferencePaperEntry(title, book, authors, pubyear, publisher, doi, day, month, year, conferenceLoc);
        String expResult = "at home";
        
        String result = instance.getConferenceLocation();
        
        assertEquals(expResult, result); 
    }
    
}
