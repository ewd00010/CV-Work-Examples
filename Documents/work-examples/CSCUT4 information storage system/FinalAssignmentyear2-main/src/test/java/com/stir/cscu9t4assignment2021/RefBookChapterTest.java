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
public class RefBookChapterTest {
    
    public RefBookChapterTest() {
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
     * Test of getEditor method, of class BookChapterEntry.
     */
    @Test
    public void testGetEditor() {
        System.out.println("getEditor");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String editor = "Scooby Doo";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        BookChapterEntry instance = new BookChapterEntry(title,book,authors,pubyear,
                                                     publisher,doi,
                                                     day,month,year,editor
                                                    );
        String expResult = "Scooby Doo";
        String result = instance.getEditor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEntryOutputBC method, of class BookChapterEntry.
     */
    @Test
    public void testGetEntryOutputBC() {
        System.out.println("getEntryOutputBC");
        String title = "Some Book Chapter";
        String book = "Book Chapter";
        String authors = "Saemi Haraldsson, Ragnheidur Brynjolfsdottir";
        String doi = "10.123456/9876";
        String publisher = "Springer";
        String editor = "Scooby Doo";
        String pubyear = "2002";
        String day = "1";
        String month = "1";
        String year = "2021";
        BookChapterEntry instance = new BookChapterEntry(title,book,authors,pubyear,
                                                     publisher,doi,
                                                     day,month,year,editor
                                                    );
        
        String expResult = "Title: Some Book Chapter - Type: Book Chapter - Authors: Saemi Haraldsson, Ragnheidur Brynjolfsdottir - Year published: 2002 -"
                           + " Publisher name: Springer - Digital object ID: 10.123456/9876 - Editor: Scooby Doo | This entry was added 1/1/2021\n";
        
        String result = instance.getEntryOutputBC();
        assertEquals(expResult, result);
    }
    
}
