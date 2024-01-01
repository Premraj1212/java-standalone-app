package com.expense.tracker;

import com.github.tomaslanger.chalk.Chalk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExpenseTrackerClient {

    // A string to hold the data file name which contains all tasks and their details
    public static String filename = "expense.obj";
    static List<String> expenseCategories = new ArrayList<>(List.of(
            "FAMILY",
            "FRIENDS",
            "OTHERS"));

    /**
     * main method to run the command line based "To Do List" application
     * @param args array of String holding command line parameters
     */
    public static void main(String[] args) {
        // An object of ExpenseTracker to hold all expenses and their data
        ExpenseTracker expenses = new ExpenseTracker();


        //A string to hold the choice that will be entered by the user
        String menuChoice = "-17";

        try{
            Scanner input = new Scanner(System.in);

            // reading the date from task data file
            // if this is the first time, a message will be shown that no data file is found
            // reading the date from task data file
            // if this is the first time, a message will be shown that no data file is found
            expenses.readFromFile(filename);

            Utility.showMessage("Welcome to Expense Tracker App", false);


            while (!menuChoice.equals("3")) {
               Utility.mainMenu();
               menuChoice = input.nextLine();


                switch (menuChoice) {
                    case "1":
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String date = input.nextLine();

                        System.out.print("Enter description: ");
                        String desc = input.nextLine();

                        System.out.print("Enter Expense Category such as " + expenseCategories.toString() + ": ");
                        String category = input.nextLine();

                        System.out.print("Enter amount: ");
                        double amount = input.nextDouble();
                        input.nextLine();

                        expenses.addExpense(new Expense(date, desc, amount, category));
                        Utility.showMessage("Expense is added successfully", false);

                        break;
                    case "2":
                        expenses.printExpenses();
                        break;
                    case "3":
                        break;
                    default:
                        System.out.println("Invalid choice. Please select from the menu.");
                }
            }
            // saving the expense details in data file
            // if this is the first time, a new expense file will be created
            expenses.saveToFile(filename);
            Utility.byeMessage();

        }catch (Exception e) {
            Utility.showMessage("UNCAUGHT EXCEPTION THROWN", true);
            System.out.println("Trying to write the unsaved data of all expenses in data file");
            expenses.saveToFile(filename);
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
}
