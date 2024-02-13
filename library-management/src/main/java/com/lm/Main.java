package com.lm;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // A string to hold the data file name which contains all tasks and their details
    public static String filename = "booksStore.obj";

    /**
     * main method to run the command line based "To Do List" application
     * @param args array of String holding command line parameters
     */

    public static void main(String[] args) throws IOException {

        // An object of Library Management to hold all transactions of books and their data
        LibraryManagement libraryManagement = new LibraryManagement();


        //A string to hold the choice that will be entered by the user
        String menuChoice = "-17";

        try {
            Scanner input = new Scanner(System.in);

            // reading the books from book data file
            // if this is the first time, a message will be shown that no data file is found
            libraryManagement.readFromFile(filename);

            Messages.showMessage("Welcome to Library Management System", false);

            while (!menuChoice.equals("5")){

                Messages.mainMenu(libraryManagement.availableCount(),libraryManagement.notAvailableCount());
                menuChoice = input.nextLine();

                switch (menuChoice) {
                    case "1":
                        libraryManagement.listOfBooks();
                        break;
                    case "2":
                        libraryManagement.addBookFromUser();
                        break;
                    case "3":
                        libraryManagement.listAllBooksWithIndex();
                        Messages.enterBookIdToBorrow_Return();
                        libraryManagement.borrowBook(input.nextLine());
                        break;
                    case "4":
                        libraryManagement.listAllBooksWithIndex();
                        Messages.enterBookIdToBorrow_Return();
                        libraryManagement.returnBook(input.nextLine());
                        break;
                    case "5":
                        break;

                    default:
                        Messages.unknownMessage();
                }
            }
            // saving the task details in data file
            // if this is the first time, a new task file will be created
            libraryManagement.saveToFile(filename);
            Messages.byeMessage();

        } catch (Exception e) {
            Messages.showMessage("UNCAUGHT EXCEPTION THROWN", true);
            System.out.println("Trying to write the unsaved data of all tasks in data file");
            libraryManagement.saveToFile(filename);
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}