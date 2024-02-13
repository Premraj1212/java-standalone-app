package com.lm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * This class is created for unit testing for model class, i.e., Book (Book.java)
 *
 * @author  Premraj
 * @version 1.0
 * @since   2024-01-14
 **/
public class BookTest {
    static final Logger log = getLogger(lookup().lookupClass());
    Book book;
    String bookAuthor="Thiruvalluvar";
    String bookTitle="Thirukural";

    /**
     * This method will execute before executing any Test.
     * This method will initialize the task object with void test parameters.
     */
    @BeforeEach
    void setup() {
        try {
            book = new Book(bookAuthor, bookTitle);
        } catch (Exception e) {
            log.debug("There are few errors in the testing of Book class");
            log.debug("Following message could be helpful to identify the cause:");
            log.debug(e.getMessage());
        }
    }

    /**
     * This method will validate the bok.getTitle() method for valid parameter
     */
    @Test
    void testValidTitle() {
        log.debug("Testing getTitle() method in {}", book.getBookTitle());

        // verify
        assertEquals(bookTitle,book.getBookTitle());
    }

    /**
     * This method will validate the task.setBookTitle() method for an empty string "" as invalid parameter
     * which should result as NullPointerException
     */
    @Test
    void testEmptyTitle() {
        boolean success=false;

        try {
            book.setBookTitle("");
            fail("should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            success = true;
        }

        Assertions.assertTrue(success);
    }
    /**
     * This method will validate the task.setBookTitle() method for trimming an empty string "     " as invalid parameter
     * which should result as NullPointerException
     */
    @Test
    void testTrimEmptyTitle() {
        boolean success=false;

        try {
            book.setBookTitle("           ");
            fail("should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            success = true;
        }

        assertEquals(true,success);
    }

    /**
     * This method will validate the task.setBookAuthor() method for an empty string "" as invalid parameter
     * which should result as NullPointerException
     */
    @Test
    void testEmptyAuthor() {
        boolean success=false;

        try {
            book.setBookAuthor("");
            fail("should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            success = true;
        }

        Assertions.assertTrue(success);
    }
    /**
     * This method will validate the task.setBookAuthor() method for trimming an empty string "     " as invalid parameter
     * which should result as NullPointerException
     */
    @Test
    void testTrimEmptyAuthor() {
        boolean success=false;

        try {
            book.setBookAuthor("           ");
            fail("should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            success = true;
        }

        assertEquals(true,success);
    }

    /**
     * This method will print with all details of the object
     * which should print Book Object
     */
    @Test
    void testFormattedBook(){
        String expectedBook = "\nBook Title     : Thirukural" +
                "\nBook Author   : Thiruvalluvar" +
                "\nBook Availability    :  YES" +
                "\n";
        String actualBook = book.formattedStringOfBook();
        assertEquals(actualBook,expectedBook);
    }

}
