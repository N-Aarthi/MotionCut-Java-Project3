package expensetracker;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseManager {
    private List<Expense> expenses;
    private final String fileName = "expenses.dat";

    public ExpenseManager() {
        expenses = new ArrayList<>();
        loadExpenses();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        saveExpenses();
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }

    public double getCategoryWiseSum(String category) {
        double sum = 0;
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                sum += expense.getAmount();
            }
        }
        return sum;
    }

    private void saveExpenses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadExpenses() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            expenses = (List<Expense>) ois.readObject();
        } catch (FileNotFoundException e) {
           
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

