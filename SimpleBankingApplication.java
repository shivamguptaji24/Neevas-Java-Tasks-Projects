import java.util.ArrayList;
import java.util.Scanner;

// Main class to manage the banking application
public class SimpleBankingApplication {
    
    private static ArrayList<Account> accounts = new ArrayList<>();     // List to store accounts
    private static int nextAccountNumber = 1000;        // Initialize account number

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            showMenu();
            int choice = getValidatedChoice(scan);

            switch (choice) {
                case 1:
                    createNewAccount(scan);
                    break;
                case 2:
                    depositToAccount(scan);
                    break;
                case 3:
                    withdrawFromAccount(scan);
                    break;
                case 4:
                    checkAccountBalance(scan);
                    break;
                case 5:
                    System.out.println("Thank you for using the banking application. Goodbye!!!!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please Try again.");
                    break;
            }
        }

        scan.close();

    }

    // For displaying the main menu
    private static void showMenu() {

        System.out.println("\n ----- Banking Application Menu -----");
        System.out.println("1. Create a New Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Account Balance");
        System.out.println("5. Exit");
        System.out.print("Please select an option : ");

    }


    // For validate user's menu choice
    private static int getValidatedChoice(Scanner scan) {
        
        while (true) {
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("!!!!! Invalid input. !!!!!");
                System.out.println("Please enter a number between 1 and 5 : ");
            }
        }

    }

    // For create a new bank account
    private static void createNewAccount(Scanner scan) {

        System.out.print("Enter Account holder's name : ");
        String accountHolderName = scan.nextLine();
        int accountNumber = nextAccountNumber++;
        Account newAccount = new Account(accountHolderName, accountNumber);
        accounts.add(newAccount);
        System.out.printf("Account created successfully! Account Number : %d%n", accountNumber);

    }

    // For deposit money to a specific account
    private static void depositToAccount(Scanner scan) {

        Account account = findAccount(scan);
        if (account != null) {
            double amount  = getValidatedAmount(scan, "Enter the amount to deposit : ");
            account.deposit(amount);
        }

    }

    // For withdraw money from a specific account
    private static void withdrawFromAccount(Scanner scan) {

        Account account = findAccount(scan);
        if (account != null) {
            double amount = getValidatedAmount(scan, "Enter the amount to withdraw : ");
            account.withdraw(amount);
        }
    }

    // For checking the balance of a specific account
    private static void checkAccountBalance(Scanner scan) {

        Account account = findAccount(scan);
        if (account != null) {
            account.checkBalance();
        }

    }

    // For finding an account by account number
    private static Account findAccount(Scanner scan) {

        System.out.print("Enter account number : ");
        int accountNumber = getValidatedChoice(scan);
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        System.out.println("!!!!! Error : Account not found. !!!!!");
        return null;

    }

    // For validate the amount enetered for deposit/withdrawal
    private static double getValidatedAmount(Scanner scan, String prompt) {

        while (true) {
            System.out.print(prompt);
            try {
                double amount = Double.parseDouble(scan.nextLine());
                if (amount > 0) {
                    return amount;
                } else {
                    System.out.println("!!!!! Error : Amount must be greater than zero. !!!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("!!!!! Invalid input. !!!!!");
                System.out.println("Please enter a valid amount.");
            }
        }

    }


}

// Class to represent a Bank Account
class Account {

    private String accountHolderName;
    private int accountNumber;
    private double balance;

    public Account(String accountHolderName, int accountNumber) {

        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = 0.0;     // Initialize with zero balance

    }

    public String getAccountHolderName() {

        return accountHolderName;

    }

    public int getAccountNumber() {

        return accountNumber;

    }

    public double getBalance() {

        return balance;

    }

    // For deposit money into account
    public void deposit(double amount) {

        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited %.2f into your account.", amount);
            System.out.printf("\n New Balance : %.2f%n", balance);
        } else {
            System.out.println("!!!!! Error : Deposit amount must be positive. !!!!!");
        }

    }

    // For withdraw money from account
    public void withdraw(double amount) {

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("Successfully withdrew %.2f into your account.", amount);
            System.out.printf("\n Remaining Balance : %.2f%n", balance);
        } else if (amount > balance) {
            System.out.println("!!!!! Error : Insufficient balance. !!!!!");
        } else {
            System.out.println("!!!!! Error : Deposit amount must be positive. !!!!!");
        }

    }

    // For display account balance
    public void checkBalance() {

        System.out.printf("Account Balance : %.2f%n", balance);

    }

}