package acme.domain;

import acme.*;
import acme.domain.transforms.BookTransformer;
import acme.domain.transforms.MemberTransformer;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LibraryStepDefinitions {

    private StockManager stockManager;
    private InMemoryLibraryMembership membership;
    private Library library;
    private LoanException caughtException;
    private Loans loans;

    public LibraryStepDefinitions() {
        stockManager = new StockManager();
        membership = new InMemoryLibraryMembership();
        loans = new InMemoryLoans();
        library = new Library(membership, loans);
    }

    @Given("^(.*) is a member of the library$")
    public void jonIsAMemberOfTheLibrary(@Transform(MemberTransformer.class) Member member) throws Throwable {
        membership.add(member);
    }

    @Given("^there is (\\d+) copy of \"([^\"]*)\" in stock$")
    public void thereIsCopyOfInStock(int copies, @Transform(BookTransformer.class) Book book) throws Throwable {
        IntStream.of(copies).forEach(i -> stockManager.add(book));
    }

    @When("^(.*) borrows the book \"([^\"]*)\" from the library$")
    public void jonBorrowsTheBookFromTheLibrary(@Transform(MemberTransformer.class) Member member, @Transform(BookTransformer.class) Book book) throws Throwable {
        library.lend(book, member);
    }

    @Then("^stock count for \"([^\"]*)\" should be (\\d+)$")
    public void stockCountForShouldBe(@Transform(BookTransformer.class) Book book, int expectedStockCount) throws Throwable {
        assertThat(stockManager.getStockLevelFor(book), is(expectedStockCount));
    }

    @And("^the book \"([^\"]*)\" should be loaned to (.*)$")
    public void theBookShouldBeLoanedToJon(@Transform(BookTransformer.class) Book book,
                                           @Transform(MemberTransformer.class) Member member) throws Throwable {
        assertThat(library.whoHas(book), is(member));
    }

    @Given("^Mark is not a member of the library$")
    public void markIsNotAMemberOfTheLibrary() throws Throwable {
    }

    @When("^(.*) tries to borrow the book \"([^\"]*)\"$")
    public void triesToBorrowTheBook(@Transform(MemberTransformer.class) Member member,
                                     @Transform(BookTransformer.class) Book book) throws Throwable {
        try {
            library.lend(book, member);
        } catch (LoanException exception) {
            this.caughtException = exception;
        }
    }

    @Then("^.* should be told \"([^\"]*)\"$")
    public void markShouldBeTold(String exceptionMessage) throws Throwable {
        assertThat(caughtException.getMessage(), is(exceptionMessage));
    }

    @Given("^there are no copies of \"([^\"]*)\" in stock$")
    public void thereAreNoCopiesOfInStock(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^(.*) has the following books out on loan:$")
    public void memberHasTheFollowingBooksOutOnLoan(@Transform(MemberTransformer.class) Member member, DataTable bookList) throws Throwable {

        bookList.raw().forEach(x -> x.forEach(y -> {
            try {
                library.lend(new Book(y), member);
            } catch (LoanException e) {
                e.printStackTrace();
            }
        }));

    }

    @When("^Jon tries to borrow a book \"([^\"]*)\"$")
    public void jonTriesToBorrowABook(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^Jon has a £(\\d+) unpaid fine$")
    public void jonHasA£UnpaidFine(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^Jon borrowed the book \"([^\"]*)\" on (\\d+)th March$")
    public void jonBorrowedTheBookOnThMarch(String arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Jon returns the book \"([^\"]*)\" on the (\\d+)th of March at (\\d+):(\\d+)$")
    public void jonReturnsTheBookOnTheThOfMarchAt(String arg0, int arg1, int arg2, int arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Jon should not be charged a fine$")
    public void jonShouldNotBeChargedAFine() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Jon returns the book \"([^\"]*)\" on the (\\d+)st of March at (\\d+):(\\d+)$")
    public void jonReturnsTheBookOnTheStOfMarchAt(String arg0, int arg1, int arg2, int arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Jon should be charged a £(\\d+) fine$")
    public void jonShouldBeChargedA£Fine(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Jon returns the book \"([^\"]*)\" from the library$")
    public void jonReturnsTheBookFromTheLibrary(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^the book \"([^\"]*)\" should not be marked as loaned to Jon$")
    public void theBookShouldNotBeMarkedAsLoanedToJon(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
