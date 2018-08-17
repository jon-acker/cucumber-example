package acme.domain;

import acme.Book;
import acme.domain.transforms.BookTransformer;
import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class LibraryStepDefinitions {

    public LibraryStepDefinitions() {
    }

    @When("^Jim tries to borrow \"([^\"]*)\"$")
    public void jim_tries_to_borrow(@Transform(BookTransformer.class) Book book) throws Exception {
        // Write code here that turns the phrase above into concrete actions
    }
}
