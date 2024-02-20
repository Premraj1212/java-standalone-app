package com.todo.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * This class is created for unit testing for model class, i.e., Task (Task.java)
 *
 * @author  Premraj
 * @version 1.0
 * @since   2024-01-24
 **/
public class TaskTest {

    static final Logger log = getLogger(lookup().lookupClass());
    Task task;

    String validTitle="Test Title";
    String validProject="Test Project";
    LocalDate validDueDate=LocalDate.now();

    /**
     * This method will execute before executing any Test.
     * This method will initialize the task object with void test parameters.
     */
    @BeforeEach
    void setup() {
        try {
            task = new Task(validTitle, validProject, validDueDate);
        } catch (Exception e) {
            log.debug("There are few errors in the testing of Task class");
            log.debug("Following message could be helpful to identify the cause:");
            log.debug(e.getMessage());
        }
    }

    /**
     * This method will validate the task.getTitle() method for valid parameter
     */
    @Test
    void testValidTitle() {
        log.debug("Testing getTitle() method in {}", task.getTitle());

        // verify
        assertEquals(validTitle,task.getTitle());
    }

    /**
     * This method will validate the task.setTitle() method for an empty string "" as invalid parameter
     * which should result as NullPointerException
     */
    @Test
    void testEmptyTitle() {
        boolean success=false;

        try {
            task.setTitle("");
            fail("should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            success = true;
        }

        Assertions.assertTrue(success);
    }
    /**
     * This method will validate the task.setTitle() method for trimming an empty string "     " as invalid parameter
     * which should result as NullPointerException
     */
    @Test
    void testTrimEmptyTitle() {
        boolean success=false;

        try {
            task.setTitle("           ");
            fail("should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            success = true;
        }

        assertEquals(true,success);
    }
    /**
     * This method will validate the task.setTitle() method for null as invalid parameter
     * which should result as NullPointerException
     */
    @Test
    void testNullTitle() {
        boolean success=false;

        try {
            task.setTitle(null);
            fail("should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            success = true;
        }

        assertEquals(true,success);
    }

    /**
     * This method will validate the task.getProject() method for valid parameter
     * Project title is allowed be empty
     */
    @Test
    void testValidProject() {
        assertEquals(validProject,task.getProject());
    }

    /**
     * This method will validate the task.setProject() method for empty string "" as parameter
     * And it will also test the task.getProject
     * Project title is allowed be empty
     */
    @Test
    void testEmptyProject() {
        task.setProject("");
        assertEquals("",task.getProject());
    }

    /**
     * This method will validate the task.getDueDate() method
     */
    @Test
    void testValidDueDate() {
        assertEquals(validDueDate,task.getDueDate());
    }

    /**
     * This method will validate the task.setDueDate() method with a wrong format date as parameter
     */
    @Test
     void testIncorrectFormatDueDate() {
        boolean success=false;

        try {
            task.setDueDate(LocalDate.parse("2024-31-12"));
            fail("should have thrown a DateTimeParseException");
        } catch (DateTimeParseException e) {
            success = true;
        }

        assertEquals(true,success);
    }

    /**
     * This method will validate the task.setDueDate() method with a past due date
     * If a past due date is tried to set, it should throw DateTimeException
     */
    @Test
     void testPastDueDate() {
        boolean success=false;

        try {
            task.setDueDate(LocalDate.parse("2024-01-13"));
            fail("should have thrown a DateTimeParseException");
        } catch (DateTimeException e) {
            success = true;
        }

        assertEquals(true,success);
    }
}
