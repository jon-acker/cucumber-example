package acme;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List; 
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Bank {

    private String name;

    private Hashtable<String, BankAccount> accounts;

    public Bank(String bankName) {
        this.name = bankName;
        this.accounts = new Hashtable<String, BankAccount> ();
    }

    public void hold(BankAccount account) {
        this.accounts.put(account.getAccountNumber(), account);
    }

    public BankAccount getAccount(String accountNumber) {
        BankAccount account =this.accounts.get(accountNumber);
        if (account == null) {
            throw new InvalidParameterException("Cannot find account: " + accountNumber);
        }

        return account;
    }

    public void settleAccounts() {
        this.accounts.values().stream()
            .filter(account -> account.getBalance() < 0)
            .forEach(account -> account.charge(0.5)) ;
    }
}