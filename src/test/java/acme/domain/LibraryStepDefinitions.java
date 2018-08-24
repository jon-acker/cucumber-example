package acme.domain;

import acme.*;
import acme.domain.transforms.BookTransformer;
import acme.domain.transforms.MemberTransformer;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LibraryStepDefinitions {

    private StockManager stockManager;

    private InMemoryLibraryMembership membership;

    private Library library;
    private LoanException caughtException;
    private InMemoryLoans loans;
    private FineManager fineManager;
    private SystemClock systemClock;

    private DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("MMMM dd, yyyy H:m:s").toFormatter();

    @Before
    public void libraryStepDefinitions() {
        stockManager = new StockManager();
        membership = new InMemoryLibraryMembership();
        loans = new InMemoryLoans();
        fineManager = new InMemoryFineManager();
        systemClock = new SystemClock(LocalDateTime.now());
        library = new Library(membership, loans, fineManager, systemClock);
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
    public void memberBorrowsTheBookFromTheLibrary(@Transform(MemberTransformer.class) Member member, @Transform(BookTransformer.class) Book book) throws Throwable {
        library.lend(book, member);
    }

    @Then("^stock count for \"([^\"]*)\" should be (\\d+)$")
    public void stockCountForShouldBe(@Transform(BookTransformer.class) Book book, int expectedStockCount) throws Throwable {
        assertThat(stockManager.getStockLevelFor(book), is(expectedStockCount));
    }

    @And("^the book \"([^\"]*)\" should be loaned to (.*)$")
    public void theBookShouldBeLoanedToJon(@Transform(BookTransformer.class) Book book,
                                           @Transform(MemberTransformer.class) Member member) throws Throwable {
        assertTrue(loans.bookIsLoanedToMember(book, member));
    }

    @Given("^Mark is not a member of the library$")
    public void markIsNotAMemberOfTheLibrary() throws Throwable {
    }

    @When("^(.*) tries to borrow the book \"([^\"]*)\"$")
    public void triesToBorrowTheBook(
            @Transform(MemberTransformer.class) Member member,
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

        bookList.raw().forEach(x -> x.forEach(bookName -> loans.add(new Book(bookName), member, LocalDateTime.now())));

    }

    @When("^Jon tries to borrow a book \"([^\"]*)\"$")
    public void jonTriesToBorrowABook(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^(.+) has a £(\\d+) unpaid fine$")
    public void jonHasAGBPUnpaidFine(@Transform(MemberTransformer.class) Member member, int fineAmount) throws Throwable {
        fineManager.add(member, fineAmount);
    }

    @Given("^(.+) borrowed the book \"([^\"]*)\" on (\\d+)th of (\\w+)$")
    public void jonBorrowedTheBookOnThMarch(
            @Transform(MemberTransformer.class) Member member,
            @Transform(BookTransformer.class) Book book,
            int day, String month) throws Throwable {

        loans.add(book, member, LocalDateTime.parse(month + " " + day + ", 2018 00:00:00", formatter));
    }

    @When("^(.+) returns the book \"([^\"]*)\" on the (\\d+)(th|st) of (\\w+) at (\\d+):(\\d+)$")
    public void memberReturnsTheBookOnDateAtTime(
            @Transform(MemberTransformer.class) Member member,
            @Transform(BookTransformer.class) Book book,
            int day, String ignore, String month, int hour, int minute
    ) throws Throwable {
        systemClock.setDateTime(LocalDateTime.parse(month + " " + day + ", 2018 " + hour + ":" + minute + ":00", formatter));

        library.returnMy(book);
    }

    @Then("^Jon should not be charged a fine$")
    public void jonShouldNotBeChargedAFine() throws Throwable {
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
