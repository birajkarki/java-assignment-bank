import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static int customerIdCounter = 1000;
    private static final Map<Integer, Customer> customers = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Text-Based Banking Application");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getChoice(scanner);

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    modifyCustomerDetails(scanner);
                    break;
                case 3:
                    checkBalance(scanner);
                    break;
                case 4:
                    modifyBalance(scanner);
                    break;
                case 5:
                    retrieveBankSummary();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for choosing our Banking Application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add Customer");
        System.out.println("2. Modify Customer Details");
        System.out.println("3. Check Balance");
        System.out.println("4. Modify Balance");
        System.out.println("5. Retrieve Bank Summary");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice(Scanner scanner) {
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Consume invalid input
            choice = getChoice(scanner);
        }
        return choice;
    }

    private static void addCustomer(Scanner scanner) {
        System.out.println("\nAdding a New Customer:");
        System.out.print("Enter customer name: ");
        String name = scanner.next();
        double balance = getValidBalance(scanner);

        int customerId = customerIdCounter++;
        Customer customer = new Customer(customerId, name, balance);
        customers.put(customerId, customer);

        System.out.println("Customer added successfully. Customer ID: " + customerId);
    }

    private static double getValidBalance(Scanner scanner) {
        double balance;
        while (true) {
            try {
                System.out.print("Enter initial balance: $");
                balance = scanner.nextDouble();
                if (balance < 0) {
                    System.out.println("Invalid balance. Balance cannot be negative.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for balance.");
                scanner.next(); // Consume invalid input
            }
        }
        return balance;
    }

    private static void modifyCustomerDetails(Scanner scanner) {
        System.out.println("\nModifying Customer Details:");
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        if (customers.containsKey(customerId)) {
            System.out.print("Enter new name: ");
            String newName = scanner.next();
            customers.get(customerId).setName(newName);
            System.out.println("Customer details updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.println("\nChecking Customer Balance:");
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        if (customers.containsKey(customerId)) {
            System.out.println("Balance for Customer ID " + customerId + ": $" + customers.get(customerId).getBalance());
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void modifyBalance(Scanner scanner) {
        System.out.println("\nModifying Customer Balance:");
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        if (customers.containsKey(customerId)) {
            double newBalance = getValidBalance(scanner);
            customers.get(customerId).setBalance(newBalance);
            System.out.println("Balance updated successfully for Customer ID " + customerId + ".");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void retrieveBankSummary() {
        System.out.println("\nBank Summary:");
        System.out.println("Total Customers: " + customers.size());
        for (Customer customer : customers.values()) {
            System.out.println("Customer ID: " + customer.getId() + ", Name: " + customer.getName() + ", Balance: $" + customer.getBalance());
        }
    }
}

class Customer {
    private final int id;
    private String name;
    private double balance;

    public Customer(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
