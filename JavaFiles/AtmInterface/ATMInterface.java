import java.util.Scanner;
import java.io.IOException;


public class ATMInterface
{
    private BankAccount account;

    public ATMInterface(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("\nWelcome to the XXX Bank ATM!");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void start() 
    {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();
            cls();
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("\nEnter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("\nEnter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("\nThank you for using the XXX Bank ATM. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 4);
        scanner.close();
    }

    public void checkBalance() {
        System.out.println("\nCurrent balance: " + account.getBalance());
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void withdraw(double amount) {
        account.withDraw(amount);
    }

    public static void cls()
    {
        try
        {	
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }


    public static void main(String[] args) {
        cls();
        BankAccount userAccount = new BankAccount(1000);
        ATMInterface atm = new ATMInterface(userAccount);
        atm.start();
    }
    
}

class BankAccount {
    private double amtBalance;

    public BankAccount(double initialBalance) {
        this.amtBalance = initialBalance;
    }

    public double getBalance() {
        return amtBalance;
    }

    public void deposit(double amount) {
        amtBalance += amount;
        System.out.println("\nDeposit successfully Completed. \n Current balance: " + amtBalance);
    }

    public boolean withDraw(double amount) {
        if (amount <= amtBalance) {
            amtBalance -= amount;
            System.out.println("\nWithdrawal successfully Completed. \n Current balance: " + amtBalance);
            return true;
        } else {
            System.out.println("\nOOPS !! Insufficient funds. Withdrawal failed.");
            return false;
        }
    }

}