package com.expense.tracker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {

    private List<Expense> expenses;

    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * A method to display the contents of ArrayList
     */
    public void printExpenses() {
        String displayFormat = "%-10s %-35s %-20s %-10s";

        if (expenses.size() > 0) {
            System.out.println(String.format(displayFormat,"DATE    ","DESCRIPTION","CATEGORY" , "AMOUNT   "));
            System.out.println(String.format(displayFormat,"========","===========","========" , "========="));
        } else {
            System.out.println(Utility.RED_TEXT + "No tasks to show" + Utility.RESET_TEXT);
        }

        expenses.stream()
                .forEach(
                            expense -> System.out.println(String.format(displayFormat,
                                    expense.getDate(),
                                    expense.getDescription(),
                                    expense.getAmount(),
                                    expense.getCategory())
                            )
                );
    }

    /**
     * This method will read the data file from disk which will contain the data of previously saved tasks
     * @param filename a string specifying the full path and extension of data file, for example,  "resources/tasks.obj"
     * @return true if the reading operation was successful, otherwise false
     */
    public boolean readFromFile(String filename) {
        boolean status = false;

        try {
            if (!Files.isReadable(Paths.get(filename))) {
                Utility.showMessage("The data file, i.e., " + filename + " does not exists", true);
                return false;
            }

            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            expenses = (ArrayList<Expense>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
            return true;

        } catch (Exception e) {
            Utility.showMessage(e.getMessage(),true);
            return false;
        }
    }

    /**
     * This method will write the data of Tasks from ArrayList to data file on disk, i.e., tasks.obj
     * @param filename a string specifying the full path and extension of data file, for example,  "resources/tasks.obj"
     * @return true if the reading operation was successful, otherwise false
     */
    public boolean saveToFile(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(expenses);

            objectOutputStream.close();
            fileOutputStream.close();
            return true;

        } catch (Exception e) {
            Utility.showMessage(e.getMessage(),true);
            return false;
        }
    }
}
