package acme.domain;

import acme.Sandwich;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import acme.Basket;


public class BaksetStepDefinitions {

    private Basket basket = null;

    public BaksetStepDefinitions() {
    }

    @Given("^my basket is empty$")
    public void my_basket_is_empty() throws Exception {

        this.basket = Basket.empty();
    }

    @When("^I add a (\\w+) sandwich to my basket$")
    public void i_add_a_bacon_sandwich_to_my_basket(String sandwichType) throws Exception {

        Sandwich sandwich = new Sandwich(sandwichType);

        this.basket.add(sandwich);
    }

    @Then("^my basket should contain:$")
    public void my_basket_should_contain(DataTable list) throws Exception {

        assertThat(this.basket.getContents().size(), is(list.raw().size()));
        int i = 0;
        for (List<String> row:list.raw()) {
            String element = row.get(0);
            assertThat(new Sandwich(element),is(basket.getContents().get(i)));
            i++;
        }
    }

    @Given("^my basket contains:$")
    public void my_basket_contains(DataTable list) throws Exception {
        list.raw().stream().forEach(row -> {
            this.basket.add(new Sandwich(row.get(0)));
        });
    }
}
