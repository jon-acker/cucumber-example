package acme.e2e;

import acme.Basket;

import acme.WebApplication;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebApplication.class)
public class E2EBasketSteps    {

    private MockMvc mvc;

    @Autowired
    private volatile WebApplicationContext webApplicationContext;

    private Basket basket = null;

    private MvcResult result;

    @Given("^my basket is empty$")
    public void my_basket_is_empty() throws Exception {

        this.basket = Basket.empty();
    }

    @When("^I add a bacon sandwich to my basket$")
    public void i_add_a_bacon_sandwich_to_my_basket() throws Exception {
        this.mvc = webAppContextSetup(this.webApplicationContext).build();

        result = this.mvc.perform(get("/basket/add")).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Then("^my basket should contain:$")
    public void my_basket_should_contain(DataTable arg1) throws Exception {
//        throw new PendingException();
    }

    @Given("^my basket contains:$")
    public void my_basket_contains(DataTable arg1) throws Exception {
//        throw new PendingException();
    }

    @When("^I add a cheese sandwich to my basket$")
    public void i_add_a_cheese_sandwich_to_my_basket() throws Exception {
//        throw new PendingException();
    }

}
