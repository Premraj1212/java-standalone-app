package com.expense.tracker;

public class Utility {

    //A public constant field to hold code to RESET the text font color
    public static final String RESET_TEXT = "\u001B[0m";
    //A public constant field to hold code to change the text font color to RED
    public static final String RED_TEXT = "\u001B[31m";
    //A public constant field to hold code to change the text font color to GREEN
    public static final String GREEN_TEXT = "\u001B[32m";



    /**
     * This method will display the main menu (top level menu) on standard output (terminal)
     * to display all options for user selection.
     */
    public static void mainMenu() {

        System.out.println("\nExpense Tracker Menu:");
        System.out.println("1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Save and Quit\n");
        System.out.println("Enter your choice [1-3]: ");
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
     * This method will display the bye message while ending the program
     */
    public static void  byeMessage() {
        System.out.println(GREEN_TEXT);
        System.out.println(">>> All tasks are saved to data file");
        System.out.println(">>> Good Bye");
        System.out.println(RESET_TEXT);
    }
}
