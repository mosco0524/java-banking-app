import java.util.InputMismatchException;
import java.util.Scanner;

class Account {
    private String ownerName;
    private double balance;

    private static final double MAX_TRANSACTION_AMOUNT = 10000.0;

    public Account(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0.0;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        if (amount > MAX_TRANSACTION_AMOUNT) {
            System.out.println("Transaction limit exceeded. Max amount: €" + MAX_TRANSACTION_AMOUNT);
            return;
        }

        balance += amount;
        System.out.println("Deposit successful. New balance: €" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        if (amount > MAX_TRANSACTION_AMOUNT) {
            System.out.println("Transaction limit exceeded. Max amount: €" + MAX_TRANSACTION_AMOUNT);
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= amount;
        System.out.println("Withdrawal successful. New balance: €" + balance);
    }
}

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Java Banking App ===");
        System.out.print("Enter account owner name: ");
        String name = scanner.nextLine();

        Account account = new Account(name);

        boolean running = true;

        while (running) {
            System.out.println("\nWelcome, " + account.getOwnerName());
            System.out.println("1. Check balance");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Exit");

            int choice = readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    System.out.println("Current balance: €" + account.getBalance());
                    break;

                case 2:
                    double depositAmount = readDouble(scanner, "Enter amount to deposit: €");
                    account.deposit(depositAmount);
                    break;

                case 3:
                    double withdrawAmount = readDouble(scanner, "Enter amount to withdraw: €");
                    account.withdraw(withdrawAmount);
                    break;

                case 4:
                    running = false;
                    System.out.println("Thank you for using Java Banking App.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                int number = scanner.nextInt();
                scanner.nextLine();
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static double readDouble(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                double number = scanner.nextDouble();
                scanner.nextLine();
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.nextLine();
            }
        }
    }
}