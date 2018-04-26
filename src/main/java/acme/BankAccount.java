package acme;

import acme.bank.Charge;
import java.util.*;


public class BankAccount {
    private double limit;

    private double balance;

    private String accountNumber;

    private String accountHolder;

    private List<Charge> charges;

    BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        
        this.charges = new ArrayList<Charge>();
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void charge(double amount) {
        this.balance -= amount;
        this.charges.add(new Charge(amount));
    }

    public void withdraw(double amount) {
        this.balance -= amount;

        if (this.balance < 0) {
            throw new RuntimeException("You have exceeded your overdraft limit");
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public BankAccount setLimit(double limit) {
        this.limit = limit;

        return this;
    }

    public List<Charge> getCharges() {
        return this.charges;
    }
}
