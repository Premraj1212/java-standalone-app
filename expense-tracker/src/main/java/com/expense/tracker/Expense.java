package com.expense.tracker;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class Expense implements Serializable {
    private String date;
    private String description;
    private double amount;
    private String category;

    // Add validation logic in constructors
    public Expense(String date, String description, double amount, String category) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("date cannot be null or empty");
        }
        this.date = date;

        this.description = description;
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.amount = amount;
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        this.category = category;
    }
}
