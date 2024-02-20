package com.expense.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTrackerTest {

    private ExpenseTracker expenseTracker;
    private List<Expense> testExpenses;
    private static final String TEST_FILE_PATH = "tasks_test.obj";

    @BeforeEach
    public void setUp() {
        expenseTracker = new ExpenseTracker();
        testExpenses = new ArrayList<>();
    }

    @Test
    public void testAddExpense() {
        Expense expense = new Expense("2024-02-20", "Test Expense", 100.0,
                "Test Category");
        expenseTracker.addExpense(expense);
        assertTrue(expenseTracker.getExpenses().contains(expense));
    }

    @Test
    public void testSaveToFile_Successful() {
        // Add test expenses
        testExpenses.add(new Expense("2024-02-20", "Test Expense 1", 100.0,"Test Category"));
        testExpenses.add(new Expense("2024-02-21", "Test Expense 2", 200.0,"Test Category" ));
        expenseTracker.getExpenses().addAll(testExpenses);

        // Save to file
        assertTrue(expenseTracker.saveToFile(TEST_FILE_PATH));

        // Clean up - delete the file after the test
        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void testSaveToFile_Failure() {
        // Attempt to save to a non-existent directory
        assertFalse(expenseTracker.saveToFile("non_existent/file_path.obj"));
    }

    @Test
    public void testPrintExpenses() {
        // Since printExpenses() method just prints, we can't assert directly.
        // We can only check if it doesn't throw an exception.
        assertDoesNotThrow(() -> expenseTracker.printExpenses());
    }

    //===============================


    @Test
    public void testReadFromFile_Successful() {
        // Prepare test data
        testExpenses.add(new Expense("2024-02-20", "Test Expense 1", 100.0,"Test Category"));
        testExpenses.add(new Expense("2024-02-21", "Test Expense 2", 200.0,"Test Category"));

        // Save test data to file
        saveTestDataToFile(testExpenses);

        // Read from file
        assertTrue(expenseTracker.readFromFile(TEST_FILE_PATH));

        // Check if the expenses were loaded
        assertEquals(testExpenses, expenseTracker.getExpenses());

        // Clean up - delete the file after the test
        cleanupTestFile();
    }

    @Test
    public void testReadFromFile_FileNotFound() {
        // Attempt to read from a non-existent file
        assertFalse(expenseTracker.readFromFile("nonexistent_file.obj"));
    }

    @Test
    public void testReadFromFile_FileCorrupted() {
        // Create a corrupted test file
        createCorruptedTestFile();

        // Attempt to read from the corrupted file
        assertFalse(expenseTracker.readFromFile(TEST_FILE_PATH));

        // Clean up - delete the corrupted file after the test
        cleanupTestFile();
    }

    private void saveTestDataToFile(List<Expense> expenses) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(TEST_FILE_PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(expenses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCorruptedTestFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(TEST_FILE_PATH)) {
            // Write some data directly to the file without using ObjectOutputStream
            fileOutputStream.write("Corrupted Data".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cleanupTestFile() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
