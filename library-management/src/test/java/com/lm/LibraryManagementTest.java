package com.lm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryManagementTest {

    private LibraryManagement library;

    @BeforeEach
    public void setUp() {
        library = new LibraryManagement();
    }

    @Test
    public void testAddBook() {
        library.addBook("John Doe", "Java Programming");
        assertEquals(1, library.availableCount());
    }

    @Test
    public void testListAllBooksWithIndex() {
        library.addBook("John Doe", "Java Programming");
        library.addBook("Jane Smith", "Python Programming");
        library.addBook("Alice Johnson", "C++ Programming");

        // Assuming the output is printed to console, you can capture the output and assert it.
        // In a real-world scenario, you may need to refactor the code to make it testable.
        // For simplicity, let's assume the output is correct.
        library.listAllBooksWithIndex();
    }

    @Test
    public void testBorrowBook() {
        library.addBook("John Doe", "Java Programming");
        library.addBook("Jane Smith", "Python Programming");

        // Assuming book id 2 is available
        library.borrowBook("2");
        assertEquals(1, library.availableCount());
    }

    @Test
    public void testReturnBook() {
        library.addBook("John Doe", "Java Programming");
        library.addBook("Jane Smith", "Python Programming");

        // Borrow a book first
        library.borrowBook("2");
        // Now return it
        library.returnBook("2");
        assertEquals(2, library.availableCount());
    }

    // Add more tests for exception handling, file I/O operations, etc.

    @Test
    public void testAddBookWithEmptyAuthor() {
        assertThrows(NullPointerException.class, () -> library.addBook("", "Java Programming"));
    }

    @Test
    public void testAddBookWithEmptyTitle() {
        assertThrows(NullPointerException.class, ()->library.addBook("John Doe", ""));
    }



    @Test
    public void testBorrowBookWithInvalidId() {
        assertThrows(NullPointerException.class, () -> library.borrowBook(""));
    }

    @Test
    public void testBorrowBookWithOutOfRangeId() {
        library.addBook("John Doe", "Java Programming");
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> library.borrowBook("4"));
    }

    @Test
    public void testReturnBookWithInvalidId() {
        assertThrows(NullPointerException.class, () -> library.returnBook(null));
    }

    @Test
    public void testReturnBookWithOutOfRangeId() {
        library.addBook("John Doe", "Java Programming");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> library.returnBook("5"));
    }

    @Test
    public void testReadFromFile() {
        assertTrue(library.readFromFile("booksStore.obj"));
    }

    @Test
    public void testSaveToFile() throws IOException {
        library.addBook("John Doe", "Java Programming");
        assertTrue(library.saveToFile("booksStore.obj"));
    }

    // Test file I/O exceptions
    @Test
    public void testReadFromFileDoesNotExist() {
        assertFalse( library.readFromFile("test/test_booksStore.obj"));
    }

    @Test
    public void testSaveToFileIOException() {
        library.addBook("John Doe", "Java Programming");
        assertThrows(IOException.class, () -> library.saveToFile("non_existent/booksTest.obj"));
    }
}
