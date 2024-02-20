package com.todo.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {

    private TodoList todoList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        todoList = new TodoList();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddTask() {
        todoList.addTask("Shop","Grocery", LocalDate.now());
        assertEquals(1, todoList.notCompletedCount());
    }
    @Test
    public void testAddTaskWithNullParameters() {
        assertThrows(NullPointerException.class, () -> todoList.addTask(null, null, null));
    }

    @Test
    public void testReadTaskFromUser() {
        String input = "Test Task\nTest Project\n2024-02-19\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertTrue(todoList.readTaskFromUser());
        assertEquals(1, todoList.notCompletedCount());
        assertTrue(outContent.toString().contains("Task is added successfully"));
    }



    @Test
    public void testListAllTasksWithIndex() {
        todoList.addTask("Test Task", "Test Project", LocalDate.now());
        todoList.listAllTasksWithIndex();
        assertTrue(outContent.toString().contains("Test Task"));
    }

    @Test
    public void testListAllTasks() {
        todoList.addTask("Test Task", "Test Project", LocalDate.now());
        todoList.listAllTasks("1");
        assertTrue(outContent.toString().contains("Test Task"));
    }



    @Test
    public void testNotCompletedCount() {
        todoList.addTask("Test Task", "Test Project", LocalDate.now());
        assertEquals(1, todoList.notCompletedCount());
    }

    @Test
    public void testReadTaskFromUserToUpdate() {
        Task task = new Task("Initial Task", "Initial Project", LocalDate.now());
        ByteArrayInputStream in = new ByteArrayInputStream("Updated Task\nUpdated Project\n2024-02-19\n".getBytes());
        System.setIn(in);

        assertTrue(todoList.readTaskFromUserToUpdate(task));
        assertEquals("Updated Task", task.getTitle());
        assertEquals("Updated Project", task.getProject());
        assertEquals(LocalDate.of(2024, 2, 19), task.getDueDate());
    }

    @Test
    public void testReadTaskFromUserToUpdateWithEmptyInput() {
        Task task = new Task("Initial Task", "Initial Project", LocalDate.now());
        ByteArrayInputStream in = new ByteArrayInputStream("\n\n\n".getBytes());
        System.setIn(in);

        assertTrue(todoList.readTaskFromUserToUpdate(task));
        assertEquals("Initial Task", task.getTitle());
        assertEquals("Initial Project", task.getProject());
        assertEquals(LocalDate.now(), task.getDueDate());
        assertTrue(outContent.toString().contains("Task is NOT modified"));
    }

    //==========================================

    @Test
    public void testEditTaskWithEmptyInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("\n".getBytes());
        System.setIn(in);

        todoList.editTask("");
        assertTrue(outContent.toString().contains("EMPTY/NULL TASK NUM"));
    }

    @Test
    public void testEditTaskWithInvalidTaskNumber() {
        todoList.addTask("Test Task", "Test Project", LocalDate.now());

        ByteArrayInputStream in = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(in);

        todoList.editTask("0");
        assertTrue(outContent.toString().contains("TASK NUM NOT GIVEN FROM TASK LIST"));
    }

    @Test
    public void testEditTaskWithInvalidInput() {
        todoList.addTask("Test Task", "Test Project", LocalDate.now());

        ByteArrayInputStream in = new ByteArrayInputStream("1\ninvalid choice\n".getBytes());
        System.setIn(in);

        todoList.editTask("1");
        assertFalse(outContent.toString().contains("Returning to Main Menu"));
    }

    @Test
    public void testListAllTasksWithIndexEmpty() {
        todoList.listAllTasksWithIndex();
        assertTrue(outContent.toString().contains("No tasks to show"));
    }

    @Test
    public void testListAllTasksEmpty() {
        todoList.listAllTasks("1");
        assertTrue(outContent.toString().contains("No tasks to show"));
    }

    @Test
    public void testReadFromFileNotReadable() {
        assertFalse(todoList.readFromFile("non_existent_file.obj"));
        assertTrue(outContent.toString().contains("does not exists"));
    }

    @Test
    public void testSaveToFileReadOnly() {
        todoList.addTask("Test Task", "Test Project", LocalDate.now());

        // Set the file read-only
        assertTrue(new File("test_tasks.obj").setReadOnly());

        assertFalse(todoList.saveToFile("test_tasks.obj"));
        assertFalse(outContent.toString().contains("Error"));
    }

}
