package com.lm;

import java.io.Serializable;

public class Book implements Serializable {

    // A String that holds the title of a task and it cannot be empty or null.
    private String bookAuthor;
    // A String that holds the title of the book, and it cannot be empty or null.
    private String bookTitle;
    // A boolean value, if true: the task is completed, otherwise false.
    private boolean isAvailable;

    /**
     * Creating an object of Task class
     * @param bookAuthor A String that holds the author of a book, and it cannot be empty or null.
     * @param bookTitle A String that holds the title of book, and it cannot be empty or null.
     *
     */

    public Book(String bookAuthor, String bookTitle) {
        this.setBookAuthor(bookAuthor);
        this.setBookTitle(bookTitle);
        this.isAvailable = true;
    }

    /**
     * A method to get the Id of the book
     * @return a String containing the id of a book
     */
    public String getBookAuthor() {
        return this.bookAuthor;
    }

    /**
     * A method to set the id of a book object
     * @param bookAuthor A String that holds the author of a book and it cannot be empty or null.
     * @throws NullPointerException if book is null or empty string
     */
    public void setBookAuthor(String bookAuthor) {
        if (bookAuthor.trim().equals("") || bookAuthor == null) {
            throw new NullPointerException("REQUIRED: Book Author can not be empty.");
        }
        this.bookAuthor = bookAuthor.trim();
    }
    /**
     * A method to get the book title
     * @return a String containing the title of a book
     */
    public String getBookTitle() {
        return bookTitle;
    }
    /**
     * A method to set the title of a Book object
     * @param bookTitle A String that holds the title of a book and it cannot be empty or null.
     * @throws NullPointerException if title is null or empty string
     */
    public void setBookTitle(String bookTitle) {
        if (bookTitle.trim().equals("") || bookTitle == null) {
            throw new NullPointerException("REQUIRED: Book Title can not be empty.");
        }
        this.bookTitle = bookTitle.trim();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * A method to mark a book as not available
     * @return the book status as not available
     */
    public boolean bookIsNotAvailable() {
        this.isAvailable = false;
        return false;
    }
    /**
     * A method to mark a book as available
     * @return the book status as available
     */
    public boolean bookIsAvailable() {
        this.isAvailable = true;
        return true;
    }

    /**
     * A method to get the book data as formatted string to display in multiple lines
     * @return formatted string of all fields of a book
     */
    public String formattedStringOfBook(){
        return (
                "\nBook Title     : " +  bookTitle +
                        "\nBook Author   : " + bookAuthor +
                        "\nBook Availability    : " + (isAvailable() ?" YES":" NO") +
                        "\n");
    }
}
