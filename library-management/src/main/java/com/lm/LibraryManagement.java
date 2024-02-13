package com.lm;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagement {

    // An array list of task objects
    private ArrayList<Book> books;

    /**
     * creating an TodoList object
     */
    public LibraryManagement(){
        books = new ArrayList<>();
    }

    /**
     * Adding a Book object in ArrayList
     * @param bookAuthor A String that holds the author of a book and it cannot be empty or null.
     * @param bookTitle A String that holds the title of book and it cannot be empty or null.

     */
    public void addBook(String bookAuthor, String bookTitle) {
        this.books.add(new Book(bookAuthor,bookTitle));
    }


    /**
     *  A method to display list of book and its availability status
     */
    public void listOfBooks() {
        Messages.separator('=',75);
        System.out.println(
                "Total Books = " + books.size() +
                        "\t\t (Available Books = " + availableCount() + "\t\t" +
                        Messages.RED_TEXT + " unavailable books = " + notAvailableCount() + Messages.RESET_TEXT +
                        " )");
        Messages.separator('=',75);


        String displayFormat = "%-30s %-35s %-20s";

        if (books.size() > 0) {
                System.out.println(String.format(displayFormat,"BOOK_AUTHOR ","BOOK_TITLE","AVAILABILITY" ));
                System.out.println(String.format(displayFormat,"============","==========","============" ));
        } else {
                System.out.println(Messages.RED_TEXT + "No Books available to borrow" + Messages.RESET_TEXT);
        }

        books.stream()
                .forEach(book -> System.out.println(
                        String.format(displayFormat,
                                book.getBookAuthor(),
                                book.getBookTitle(),
                                (book.bookIsAvailable() ? "YES" : "NO")
                        )));

    }

    /**
     * A method to read the value from user (standard input, i.e., terminal)
     * to create a Book object and to add in the ArrayList of Books
     * @return true, if the Book object is created and added to ArrayList, otherwise false
     */
    public boolean addBookFromUser() {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println(Messages.GREEN_TEXT + "Please enter the following details to add a book:" + Messages.RESET_TEXT);
            System.out.print(">>> Book Author  : ");
            String id = scan.nextLine();
            System.out.print(">>> Book Title: ");
            String bookTitle = scan.nextLine();

            this.books.add(new Book(id,bookTitle));
            Messages.showMessage("Book is added successfully", false);

            return true;
        } catch (Exception e) {
            Messages.showMessage(e.getMessage(),true);
            return false;
        }

    }

    /**
     * A method to display the contents of ArrayList with first column as Book ID
     */
    public void listAllBooksWithIndex() {
        String displayFormat = "%-4s%-35s %-20s";

        if (books.size()>0) {
            System.out.println(String.format(displayFormat,"BOOK ID","BOOK TITLE","AVAILABILITY"));
            System.out.println(String.format(displayFormat,"=======","==========","============="));
        } else {
            System.out.println(Messages.RED_TEXT + "No books to show" + Messages.RESET_TEXT);
        }

        books.stream()
                .forEach(book -> System.out.println(String.format(displayFormat,
                        books.indexOf(book)+1,
                        book.getBookAuthor(),
                        book.getBookTitle(),
                        (book.bookIsAvailable()?"YES":"NO")
                )));
    }

    /**
     * A method to borrow a particular book object from ArrayList and update its availability
     * @param selectedBook Book id that is selected by user from given list to borrow operations
     * @throws NullPointerException if book id is empty string or null
     * @throws ArrayIndexOutOfBoundsException if book id does not fall in index range of ArrayList
     */
    public void borrowBook(String selectedBook) throws NullPointerException {
        // checking if the book id is empty string or null
        if (selectedBook.trim().equals("") || selectedBook == null) {
            throw new NullPointerException("EMPTY/NULL Book Id: Returning to Main Menu");
        }
        int bookId = Integer.parseInt(selectedBook) - 1;
        if (bookId < 0 || bookId > books.size()) {
            throw new ArrayIndexOutOfBoundsException("BOOK ID NOT IN THE LIST: Returning to Main Menu");
        }
        try {

            Book book = books.get(bookId);
            book.bookIsNotAvailable();
            Messages.showMessage("Book Id " + selectedBook + "  is selected:" + book.formattedStringOfBook(), false);


        } catch (Exception e) {
            Messages.showMessage(e.getMessage(),true);
        }
    }

    /**
     * A method to return a particular book object from ArrayList and update its availability
     * @param selectedBook Book id that is selected by user from given list to return operations
     * @throws NullPointerException if book id is empty string or null
     * @throws ArrayIndexOutOfBoundsException if book id does not fall in index range of ArrayList
     */
    public void returnBook(String selectedBook) throws NullPointerException {
        // checking if the book id is empty string or null
        if (selectedBook == null || selectedBook.trim().isEmpty()) {
            throw new NullPointerException("EMPTY/NULL Book Id: Returning to Main Menu");
        }
        int bookId = Integer.parseInt(selectedBook) - 1;

        // Check if books list is null or empty
        if (books == null || books.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("BOOK LIST IS EMPTY: Returning to Main Menu");
        }
        if (bookId < 0 || bookId > books.size()) {
            throw new ArrayIndexOutOfBoundsException("BOOK ID NOT IN THE LIST: Returning to Main Menu");
        }
        try {

            Book book = books.get(bookId);
            book.bookIsAvailable();


            Messages.showMessage("Book Id " + selectedBook + "  is selected:" + book.formattedStringOfBook(), false);


        } catch (Exception e) {
            Messages.showMessage(e.getMessage(),true);
        }
    }



    /**
     * This method will read the data file from disk which will contain the data of previously saved books
     * @param filename a string specifying the full path and extension of data file, for example,  "resources/books.obj"
     * @return true if the reading operation was successful, otherwise false
     */
    public boolean readFromFile(String filename) {
        boolean status = false;

        try {
            if (!Files.isReadable(Paths.get(filename))) {
                Messages.showMessage("The data file, i.e., " + filename + " does not exists", true);
                return false;
            }

            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            books = (ArrayList<Book>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
            return true;

        } catch (Exception e) {
            Messages.showMessage(e.getMessage(),true);
            return false;
        }
    }

    /**
     * This method will write the data of Tasks from ArrayList to data file on disk, i.e., tasks.obj
     * @param filename a string specifying the full path and extension of data file, for example,  "resources/tasks.obj"
     * @return true if the reading operation was successful, otherwise false
     */
    public boolean saveToFile(String filename) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(books);

            objectOutputStream.close();
            fileOutputStream.close();
            return true;

        } catch (IOException e) {
            Messages.showMessage(e.getMessage(),true);
            throw e;
        }
    }

    /**
     * A method to count the number of Books available
     * @return number of books available to borrow
     */
    public int availableCount() {
        return (int) books.stream()
                .filter(Book::isAvailable)
                .count();
    }
    /**
     * A method to count the number of books not available to borrow
     * @return number of books not available to borrow
     */
    public int notAvailableCount() {
        return (int) books.stream()
                .filter(book -> !book.isAvailable())
                .count();
    }

}
