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
public class RefSystemGUITest 
{
    
    public RefSystemGUITest() 
    {
    }
    
    @BeforeAll
    public static void setUpClass() 
    {
        RefSystemGUI applicTest = new RefSystemGUI();
    }
    
    @AfterAll
    public static void tearDownClass() 
    {
        System.out.println("test complete");
    }
    
    @BeforeEach
    public void setUp() 
    {
        
    }
    
    @AfterEach
    public void tearDown() 
    {
        System.out.println("test complete");
    }

    /**
     * Test of main method, of class RefSystemGUI.
     * Just tests if the GUI initiates without explicit fail
     */
    @Test
    public void testMain() 
    {
        System.out.println("main");
        String[] args = null;
        RefSystemGUI.main(args);
    }
    
    /**
     * Test of TtypeChecker method of class RefSystemGUI.
     */
    @Test
    public void testTypeChecker()
    {
        System.out.println("typeChecker");
        
        RefSystemGUI instance = new RefSystemGUI();
        instance.bookChapter.setSelected(true);
        String result = instance.typeChecker();
        String expResult = "Book Chapter";
        
        assertEquals(result, expResult);
    }
    
    /**
     * Test of optionsGUISwitch method of class RefSystemGUI.
     */
    @Test
    public void testOptionsGUISwitch()
    {
        System.out.println("optionsGUISwitch");
        
        RefSystemGUI instance = new RefSystemGUI();
        instance.optionsGUISwitch(false);
        assertTrue(instance.GUIAddEntry.isVisible() == false);
    }
    
    /**
     * Test of inputGUISwitch method of class RefSystemGUI.
     */
    @Test
    public void testinputGUISwitch()
    {
        System.out.println("inputGUISwitch");
        
        RefSystemGUI instance = new RefSystemGUI();
        instance.inputGUISwitch(true);
        assertTrue(instance.title.isVisible() && instance.digitalObjectIdentifier.isVisible());
    }
    
    /**
     * Test of GUIHandler method of class RefSystemGUI.
     */
    @Test
    public void testGUIHandler()
    {
        System.out.println("GUIHandler");
        
        RefSystemGUI instance = new RefSystemGUI();
        instance.GUIHandler("Please enter the export file name and type");
        assertTrue(instance.allEntries.isVisible() && instance.labAllEntries.isVisible());
    }
    
    /**
     * Test of addEntry method of class RefSystemGUI.
     */
    @Test
    public void testAddEntry()
    {
        System.out.println("addEntry");
        
        RefSystemGUI instance = new RefSystemGUI();
        instance.title.setText("title");
        instance.authors.setText("authors");
        instance.publicationYear.setText("1234");
        instance.publisherName.setText("publisher name");
        instance.digitalObjectIdentifier.setText("DOI");
        instance.dayAdded.setText("3");
        instance.monthAdded.setText("2");
        instance.yearAdded.setText("1234");
        instance.volume.setText("13");
        instance.issue.setText("14");
        String result = instance.addEntry();
        String expResult = "Title: title - Type: Journal Paper - Authors: authors - Year published: 1234 -"
                           + " Publisher name: publisher name - Digital object ID: DOI - Issue: 14 - Volume: 13 | This entry was added 3/2/1234\n";
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deleteEntry method of class RefSystemGUI.
     */
    @Test
    public void testDeleteEntry()
    {
        System.out.println("deleteEntry");
        
        RefSystemGUI instance = new RefSystemGUI();
        instance.title.setText("title");
        instance.authors.setText("authors");
        instance.publicationYear.setText("1234");
        instance.publisherName.setText("publisher name");
        instance.digitalObjectIdentifier.setText("DOI");
        instance.dayAdded.setText("3");
        instance.monthAdded.setText("2");
        instance.yearAdded.setText("1234");
        instance.volume.setText("13");
        instance.issue.setText("14");
        instance.addEntry();
        String result = instance.deleteEntry();
        String expResult = "Deleted title";
        assertEquals(expResult, result);
    }
    
    /**
     * Test of importEntries method of class RefSystemGUI.
     */
    @Test
    public void testImportEntries()
    {
        System.out.println("importEntries");
        
        RefSystemGUI instance = new RefSystemGUI();
        String result = instance.importEntries();
        String expResult = "Total entries imported: 300";
        assertEquals(expResult, result);
    }
    
    /**
     * Test of exportEntries method of class RefSystemGUI.
     */
    @Test
    public void testExportEntries()
    {
        System.out.println("exportEntries");
        
        RefSystemGUI instance = new RefSystemGUI();
        instance.importEntries();
        instance.allEntries.setSelected(true);
        String result = instance.exportEntries();
        String expResult = "Exported 300 entries (All entries)";
        assertEquals(expResult, result);
    }
    
    /**
     * Test of blankDisplay method of class RefSystemGUI.
     */
    @Test
    public void testBlankDisplay()
    {
        System.out.println("blankDisplay");
        
        RefSystemGUI instance = new RefSystemGUI();
        instance.title.setText("to be cleared");
        instance.blankDisplay();
        assertTrue(instance.title.getText().equals(""));
    }
}
