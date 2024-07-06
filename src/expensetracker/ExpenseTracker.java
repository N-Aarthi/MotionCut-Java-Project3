package expensetracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ExpenseTracker {
    private static UserManager userManager = new UserManager();
    private static ExpenseManager expenseManager = new ExpenseManager();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (choice == 1) {
                userManager.registerUser(username, password);
            } else if (choice == 2) {
                loggedInUser = userManager.loginUser(username, password);
            }
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Category-wise Sum");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    String dateInput = scanner.nextLine();
                    Date date = null;
                    try {
                        date = dateFormat.parse(dateInput);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format! Please enter the date in yyyy-MM-dd format.");
                        continue;
                    }
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); 

                    Expense expense = new Expense(date, category, amount);
                    expenseManager.addExpense(expense);
                    System.out.println("Expense added!");
                    break;

                case 2:
                    for (Expense exp : expenseManager.getAllExpenses()) {
                        System.out.println(exp);
                    }
                    break;

                case 3:
                    System.out.print("Enter category: ");
                    category = scanner.nextLine();
                    double sum = expenseManager.getCategoryWiseSum(category);
                    System.out.println("Total amount spent in " + category + ": " + sum);
                    break;

                case 4:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
