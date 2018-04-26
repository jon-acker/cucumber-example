package acme;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import cucumber.api.PendingException;

import acme.BankAccount;

public class WithdrawingMoneySteps {
    private World world;

    private Exception exception;

    public WithdrawingMoneySteps(World world) {
        this.world = world;
    }

    @Given("^(\\w+)\'s overdraft limit is (\\d+) GBP$")
    public void my_overdraft_limit_is_GBP(String accountHolder, float limit) throws Exception {
        BankAccount account = this.world.bank.getAccount(this.world.accounts.get(accountHolder));
        account.setLimit(limit);
    }

    @When("^(\\w+) withdraws (\\d+) GBP from his account$")
    public void i_withdraw_GBP_from_my_account(String accountHolder, float amount) throws Exception {
        BankAccount account = this.world.bank.getAccount(this.world.accounts.get(accountHolder));
        try {
            account.withdraw(amount);
        } catch (Exception e) {
            this.exception = e;
        }
    }

    @Then("^(\\w+) should receive a warning that his OD limit is £(\\d+)$")
    public void i_should_receive_a_warning_that_my_OD_limit_is_£(String accountHolder, int limit) throws Exception {
        assertNotNull(this.exception);
        assertThat(exception.getMessage(), is("You have exceeded your overdraft limit"));
    }

    @When("^(\\w+) deposits (\\d+) GBP in his account$")
    public void iDepositMoney(String accountHolder, float amount) throws Exception {
        BankAccount account = this.world.bank.getAccount(this.world.accounts.get(accountHolder));
        
        account.deposit(amount);
    }
}