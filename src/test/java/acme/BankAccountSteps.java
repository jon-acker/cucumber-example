package acme;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.hamcrest.core.IsSame;

import acme.bank.Charge;
import acme.BankAccount;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import cucumber.api.PendingException;
import java.util.*;

import org.apache.commons.lang.builder.ToStringBuilder;


public class BankAccountSteps {
    private World world;

    private float currentBalance;

    public BankAccountSteps(World world) {
        this.world = world;
        this.world.accounts = new Hashtable<String, String>();
    }

    @Then("^the balance on (\\w+)\'s account should be (-?[\\d\\.]+) GBP$")
    public void myBalanceShoulwBe(String accountHolder, double balance) throws Exception {
        assertThat(this.world.bank.getAccount(this.world.accounts.get(accountHolder)).getBalance(), is(balance));
    }

    @Given("^the balance on (\\w+)\'s account is (-?\\d+) GBP$")
    public void the_balance_on_my_account_is_GBP(String accountHolder, float balance) throws Exception {
        BankAccount account = this.world.bank.getAccount(this.world.accounts.get(accountHolder));
        System.out.println(ToStringBuilder.reflectionToString(this.world.accounts.get(accountHolder)));
        account.setBalance(balance);
        
        this.currentBalance = balance;
    }

    @When("^the bank settles its end of day accounts$")
    public void the_bank_settles_its_end_of_day_accounts() throws Exception {
        this.world.bank.settleAccounts();
    }

    @Then("^(\\w+)\'s account should have been charged (\\d+)p$")
    public void my_account_should_have_been_charged_p(String accountHolder, float amountInPence) throws Exception {
        double amount = amountInPence / 100;
        BankAccount account = this.world.bank.getAccount(this.world.accounts.get(accountHolder));
        List<Charge> charges = account.getCharges();

        assertEquals(charges.get(0), new Charge(amount));
    }

    @Given("^(\\w+)\'s account (\\d+) is held at (\\w+) bank$")
    public void my_account_is_held_at_bank(String accountHolder, String accountNumber, String bankName) throws Exception {
        if (this.world.bank == null) {
            this.world.bank = new Bank(bankName);
        }

        this.world.bank.hold(new BankAccount(accountNumber, accountHolder));
        this.world.accounts.put(accountHolder, accountNumber);
    }

}