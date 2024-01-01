package com.lm;

public class Messages {
    //A public constant field to hold code to RESET the text font color
    public static final String RESET_TEXT = "\u001B[0m";
    //A public constant field to hold code to change the text font color to RED
    public static final String RED_TEXT = "\u001B[31m";
    //A public constant field to hold code to change the text font color to GREEN
    public static final String GREEN_TEXT = "\u001B[32m";

    /**
     * This method will display the main menu (top level menu) on standard output (terminal)
     * to display all options for user selection.
//     * @param availableBooksCount takes the number of books (int) not available and to show in main menu
//     * @param unavailableBookCount takes the number of books (int) available and  to show in main menu
     */

    public static void mainMenu(int availableBooksCount, int unavailableBookCount) {
        System.out.println("\nMAIN MENU");
        System.out.println("===========\n");
        System.out.println("You have " + Messages.RED_TEXT
                + unavailableBookCount + " book(s) unavailable "
                + Messages.RESET_TEXT + " and " + Messages.GREEN_TEXT
                + availableBooksCount + " book(s) available\n" + Messages.RESET_TEXT);
        System.out.println("Pick an option:");
        System.out.println("(1) Show List of Books");
        System.out.println("(2) Add New Book");
        System.out.println("(3) Borrow Book (update books availability)");
        System.out.println("(4) Return Book (update books availability)");
        System.out.println("(5) Save and Quit\n");
        System.out.print("Please enter your choice [1-4]: ");
    }



    /**
     * This method will display a prompt to user for typing the book id to BORROW
     */
    public static void enterBookIdToBorrow_Return() {
        System.out.println(GREEN_TEXT);
        System.out.print(">>> Type a book number to BORROW and press ENTER key: ");
        System.out.print(RESET_TEXT);
    }

    /**
     * This method will display the Edit menu options on standard output (terminal)
     * for user selection
     */
    public static void editTaskMenu() {
        System.out.println("\nTask Edit Options");
        System.out.println("======================\n");
        System.out.println("Pick an option:");
        System.out.println("(1) Modify selected task");
        System.out.println("(2) Mark selected task as COMPLETED");
        System.out.println("(3) Delete selected task");
        System.out.println("(4) Return to main menu "
                + Messages.RED_TEXT + " [default choice, just press ENTER]"+Messages.RESET_TEXT);
        System.out.print("\nPlease enter your choice [1-4]: ");
    }

    /**
     * This method will display the bye message while ending the program
     */
    public static void  byeMessage() {
        System.out.println(GREEN_TEXT);
        System.out.println(">>> All tasks are saved to data file");
        System.out.println(">>> Good Bye");
        System.out.println(RESET_TEXT);
    }

    /**
     * This method will display the error message if a user input an option which is not
     * from the choices given in main menu
     */
    public static void unknownMessage() {
        System.out.println(RED_TEXT);
        System.out.println(">>> Incorrect choice: Please type a number from given choices ");
        System.out.print(RESET_TEXT);
    }

    /**
     * This message will display any given message in RED or GREEN text on standard output (terminal)
     * @param message a text message as String
     * @param warning a boolean value, true for printing warning with RED text, and false
     *                for printing message in GREEN text on standard output (terminal)
     */
    public static void showMessage(String message, boolean warning) {
        System.out.println(warning?RED_TEXT:GREEN_TEXT);
        System.out.println(">>> " + message);
        System.out.println(RESET_TEXT);
    }

    /**
     * This message will print the given character on standard output (terminal) to given number of times
     * @param charToPrint a character given in single quote to print, i.e., '='
     * @param times an integer to repeat printing the given character
     */
    public static void separator (char charToPrint, int times) {
        for (int index=0; index<times; index++) System.out.print(charToPrint);
        System.out.println("");
    }
}
