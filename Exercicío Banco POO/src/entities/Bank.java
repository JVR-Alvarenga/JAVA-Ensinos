package entities;

public class Bank {
    private int account;
    private String name;
    private double balance;

    public Bank(int account, String name, double initialDeposit) {
        this.account = account;
        this.name = name;
        deposit(initialDeposit);
    }
    public Bank(int account, String name) {
        this.account = account;
        this.name = name;
    }
    
    public void withdrawal(double amount) {
        balance -= amount + 5.0;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public String toString() {
        return "Account "
            + account
            + ", Holder: "
            + name
            + ", Balance: $ "
            + String.format("%.2f", balance);
    }
}