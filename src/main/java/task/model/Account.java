package task.model;

public class Account {

    private long id;
    private double balance;

    public Account(long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }
}
