package com.expense.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * This class is created for unit testing for model class, i.e., Expense (Expense.java)
 *
 * @author  Premraj
 * @version 1.0
 * @since   2024-01-14
 **/
public class ExpenseTest {

    static final Logger log = getLogger(lookup().lookupClass());
    Expense expense;
    String date = "14-01-2024";
    String description = "Birthday celebration";
    double amount = 25.99;
    String category = "others";

    /**
     * This method will execute before executing any Test.
     * This method will initialize the task object with void test parameters.
     */
    @BeforeEach
    void setup() {
        try {
            expense = new Expense(date, description, amount,category);
        } catch (Exception e) {
            log.debug("There are few errors in the testing of Expense class");
            log.debug("Following message could be helpful to identify the cause:");
            log.debug(e.getMessage());
        }
    }

    /**
     * This method will validate the task.description() method for valid parameter
     */
    @Test
    void testExpenseCreate() {
        Expense nextExpense = new Expense(date,description,amount,category);
        log.debug("Testing expense creation method in {} {}", expense, nextExpense);

        // verify
        assertEquals(expense,nextExpense);
        assertEquals(expense.hashCode(),nextExpense.hashCode());
    }

    /**
     * This method will validate the expense.description() method for valid parameter
     */
    @Test
    void testExpenseDescription() {
        log.debug("Testing description() method in {}", expense.description());

        // verify
        assertEquals(description,expense.description());
    }
    /**
     * This method will validate the expense constructor method for invalid parameter
     */
    @Test
    void testInvalidConstructorArguments() {
        assertThrows(IllegalArgumentException.class, () -> new Expense(null, "Movie",23.21,"Others"));
        assertThrows(IllegalArgumentException.class, () -> new Expense("John", "Computer",-5.0,"Others"));
    }
    /**
     * This will test invalid comparison
     */
    @Test
    void testImmutableRecord() {
        String notAPerson = "I'm not a Person object";
        assertNotEquals(expense.hashCode(), notAPerson.hashCode());
    }


}
