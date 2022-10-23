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
public class RefTest {
    
    public RefTest() {
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
     * Test of getTitle method, of class Entry.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        Entry instance = new Entry(title, book, authors, pubyear, publisher, doi, day, month, year);
        String expResult = "Some Book Chapter";
        
        String result = instance.getTitle();
        
        assertEquals(expResult, result); 
    }

    /**
     * Test of getAuthors method, of class EntryEntry.
     */
    @Test
    public void testGetAuthors() {
        System.out.println("getAuthors");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        Entry instance = new Entry(title, book, authors, pubyear, publisher, doi, day, month, year);
        String expResult = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        
        String result = instance.getAuthors();
        
        assertEquals(expResult, result); 
    }

    /**
     * Test of getPubYear method, of class Entry.
     */
    @Test
    public void testGetPubYear() {
        System.out.println("getPublicationYear");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        Entry instance = new Entry(title, book, authors, pubyear, publisher, doi, day, month, year);
        String expResult = "2002";
        
        String result = instance.getPublicationYear();
        
        assertEquals(expResult, result); 
    }

    /**
     * Test of getPubname method, of class Entry.
     */
    @Test
    public void testGetPubName() {
        System.out.println("getPublisherName");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        Entry instance = new Entry(title, book, authors, pubyear, publisher, doi, day, month, year);
        String expResult = "Springer";
        
        String result = instance.getPublisherName();
        
        assertEquals(expResult, result); 
    }

    /**
     * Test of getDOI method, of class Entry.
     */
    @Test
    public void testGetDOI() {
        System.out.println("getDOI");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        Entry instance = new Entry(title, book, authors, pubyear, publisher, doi, day, month, year);
        String expResult = "10.123456/9876";
        
        String result = instance.getDOI();
        
        assertEquals(expResult, result); 
    }

    /**
     * Test of getDay method, of class Entry.
     */
    @Test
    public void testGetDay() {
        System.out.println("getDay");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        Entry instance = new Entry(title, book, authors, pubyear, publisher, doi, day, month, year);
        String expResult = "1";
        
        String result = instance.getDay();
        
        assertEquals(expResult, result); 
    }
    
    /**
     * Test of getMonth method, of class Entry.
     */
    @Test
    public void testGetMonth() {
        System.out.println("getDay");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        Entry instance = new Entry(title, book, authors, pubyear, publisher, doi, day, month, year);
        String expResult = "1";
        
        String result = instance.getMonth();
        
        assertEquals(expResult, result); 
    }
    
    /**
     * Test of getYear method, of class Entry.
     */
    @Test
    public void testGetYear() {
        System.out.println("getDay");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        Entry instance = new Entry(title, book, authors, pubyear, publisher, doi, day, month, year);
        String expResult = "2021";
        
        String result = instance.getYear();
        
        assertEquals(expResult, result); 
    }
    
    /**
     * Test of getEntryOutput method, of class Entry.
     */
    @Test
    public void testGetEntryOutput() {
        System.out.println("getEntryOutput");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        Entry instance = new Entry(title, book, authors, pubyear, publisher, doi, day, month, year);
        String expResult = "Title: Some Book Chapter - Type: Book Chapter - Authors: Saemi Haraldsson, Ragnheidur Brynjolfsdottir - Year published: 2002 -"
                           + " Publisher name: Springer - Digital object ID: 10.123456/9876 | This entry was added 1/1/2021\n";;
        
        String result = instance.getEntryOutput();
        
        assertEquals(expResult, result); 
    }
    
}
