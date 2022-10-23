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
public class RefCollectionTest {
    
    public RefCollectionTest() 
    {
        
    }
    
    @BeforeAll
    public static void setUpClass() 
    {
        
    }
    
    @AfterAll
    public static void tearDownClass() 
    {
        System.out.println("finished testing RefCollection");
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
     * Test of addBookChapter method, of class RefCollection.
     */
    @Test
    public void testAddBookChapter() 
    {
        System.out.println("add Book Chapter");
        BookChapterEntry ref = new BookChapterEntry("title", "Book Chapter", "author", "1234", "pub name", "132/678", "29", "9", "2021", "qwerty");
        RefCollection instance = new RefCollection();
        
        instance.addBookChapterEntry(ref);
        
        assertTrue(instance.BCEntryRecord.indexOf(ref) == 0);
    }

    /**
     * Test of loopUpBy method, of class RefCollection.
     */
    @Test
    public void testLookUpByTitle() 
    {
        System.out.println("loopUpByTitle");
        BookChapterEntry ref = new BookChapterEntry("title", "Book Chapter", "author", "1234", "pub name", "132/678", "29", "9", "2021", "qwerty");
        RefCollection instance = new RefCollection();
        instance.addBookChapterEntry(ref);
        String expResult = " \nTitle: title - Type: Book Chapter - Authors: author - Year published: 1234 -"
                           + " Publisher name: pub name - Digital object ID: 132/678 - Editor: qwerty | This entry was added 29/9/2021\n";
        
        String result = instance.lookUpEntry("title", "Book Chapter", "", "", "", "", "", "", "");
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of lookUpBy method, of class RefCollection.
     */
    @Test
    public void testLookUpAuthor() 
    {
        System.out.println("loopUpByAuthor");
        BookChapterEntry ref = new BookChapterEntry("title", "Book Chapter", "author", "1234", "pub name", "132/678", "29", "9", "2021", "qwerty");
        RefCollection instance = new RefCollection();
        instance.addBookChapterEntry(ref);
        String expResult = " \nTitle: title - Type: Book Chapter - Authors: author - Year published: 1234 -"
                           + " Publisher name: pub name - Digital object ID: 132/678 - Editor: qwerty | This entry was added 29/9/2021\n";
        
        String result = instance.lookUpEntry("", "Book Chapter", "author", "", "", "", "", "", "");
        
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of lookUpBy method, of class RefCollection.
     */
    @Test
    public void testLookUpPublicationYear() 
    {
        System.out.println("loopUpByPubYear");
        BookChapterEntry ref = new BookChapterEntry("title", "Book Chapter", "author", "1234", "pub name", "132/678", "29", "9", "2021", "qwerty");
        RefCollection instance = new RefCollection();
        instance.addBookChapterEntry(ref);
        String expResult = " \nTitle: title - Type: Book Chapter - Authors: author - Year published: 1234 -"
                           + " Publisher name: pub name - Digital object ID: 132/678 - Editor: qwerty | This entry was added 29/9/2021\n";
        
        String result = instance.lookUpEntry("", "Book Chapter", "", "1234", "", "", "", "", "");
        
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of lookUpBy method, of class RefCollection.
     */
    @Test
    public void testLookUpPublicationName() 
    {
        System.out.println("loopUpByPubName");
        BookChapterEntry ref = new BookChapterEntry("title", "Book Chapter", "author", "1234", "pub name", "132/678", "29", "9", "2021", "qwerty");
        RefCollection instance = new RefCollection();
        instance.addBookChapterEntry(ref);
        String expResult = " \nTitle: title - Type: Book Chapter - Authors: author - Year published: 1234 -"
                           + " Publisher name: pub name - Digital object ID: 132/678 - Editor: qwerty | This entry was added 29/9/2021\n";
        
        String result = instance.lookUpEntry("", "Book Chapter", "", "", "pub name", "", "", "", "");
        System.out.println(result + "\n" + expResult);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of lookUpBy method, of class RefCollection.
     */
    @Test
    public void testLoopUkDOI() 
    {
        System.out.println("loopUpByDOI");
        BookChapterEntry ref = new BookChapterEntry("title", "Book Chapter", "author", "1234", "pub name", "132/678", "29", "9", "2021", "qwerty");
        RefCollection instance = new RefCollection();
        instance.addBookChapterEntry(ref);
        String expResult = " \nTitle: title - Type: Book Chapter - Authors: author - Year published: 1234 -"
                           + " Publisher name: pub name - Digital object ID: 132/678 - Editor: qwerty | This entry was added 29/9/2021\n";
        
        String result = instance.lookUpEntry("", "Book Chapter", "", "", "", "132/678", "", "", "");
        
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of lookUpBy method, of class RefCollection.
     */
    @Test
    public void testLookUpYear() 
    {
        System.out.println("lookUpByYear");
        BookChapterEntry ref = new BookChapterEntry("title", "Book Chapter", "author", "1234", "pub name", "132/678", "29", "9", "2021", "qwerty");
        RefCollection instance = new RefCollection();
        instance.addBookChapterEntry(ref);
        String expResult = " \nTitle: title - Type: Book Chapter - Authors: author - Year published: 1234 -"
                           + " Publisher name: pub name - Digital object ID: 132/678 - Editor: qwerty | This entry was added 29/9/2021\n";
        
        String result = instance.lookUpEntry("", "Book Chapter", "", "", "", "", "", "", "2021");
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of entryImporter method, of class RefCollection.
     */
    @Test
    public void testEntryImporter() 
    {
        System.out.println("entryImporter");
        BookChapterEntry ref = new BookChapterEntry("title", "Book Chapter", "author", "1234", "pub name", "132/678", "29", "9", "2021", "qwerty");
        RefCollection instance = new RefCollection();
        instance.EntryImporter();
        String expResult = "Total entries imported: 300";
        String result = instance.EntryImporter();
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of entryExporter method, of class RefCollection.
     */
    @Test
    public void testEntryExporter() 
    {
        System.out.println("entryExporter");
        BookChapterEntry ref = new BookChapterEntry("title", "Book Chapter", "author", "1234", "pub name", "132/678", "29", "9", "2021", "qwerty");
        RefCollection instance = new RefCollection();
        instance.addBookChapterEntry(ref);
        String expResult = "Exported 1 entries (All entries)";
        String result = instance.EntryExporter("ALL");
        
        assertEquals(expResult, result);

    }

    /**
     * Test of entryExporter method, of class RefCollection.
     */
    @Test
    public void testExportJournalPaper() 
    {
        System.out.println("exportJournalPaper");
        JournalPaperEntry ref = new JournalPaperEntry("title", "Journal Paper", "author", "1234", "pub name", "132/678", "29", "9", "2021", 12, 23);
        RefCollection instance = new RefCollection();
        instance.addJournalPaperEntry(ref);
        String expResult = "Exported 1 entries (Journal Paper entries)";
        String result = instance.EntryExporter("Journal Paper");
        
        assertEquals(expResult, result);

    }
    
    /**
     * Test of entryExporter method, of class RefCollection.
     */
    @Test
    public void testExportConferencePaper() 
    {
        System.out.println("exportConferencePaper");
        ConferencePaperEntry ref = new ConferencePaperEntry("title", "Conference Paper", "author", "1234", "pub name", "132/678", "29", "9", "2021", "loc");
        RefCollection instance = new RefCollection();
        instance.addConferencePaperEntry(ref);
        String expResult = "Exported 1 entries (Conference Paper entries)";
        String result = instance.EntryExporter("Conference Paper");
        
        assertEquals(expResult, result);

    }
    
    /**
     * Test of entryExporter method, of class RefCollection.
     */
    @Test
    public void testExportBookChapter() 
    {
        System.out.println("exportBookChapter");
        RefCollection instance = new RefCollection();
        BookChapterEntry ref = new BookChapterEntry("title", "Book Chapter", "author", "1234", "pub name", "132/678", "29", "9", "2021", "qwerty");
        instance.addBookChapterEntry(ref);
        String expResult = "Exported 1 entries (Book Chapter entries)";
        String result = instance.EntryExporter("Book Chapter");
        
        assertEquals(expResult, result);

    }
    
}
