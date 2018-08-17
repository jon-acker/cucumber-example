package acme.e2e;

import acme.WebApplication;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebApplication.class)
public class E2ELibrarySteps {

    private MockMvc mvc;

    @Autowired
    private volatile WebApplicationContext webApplicationContext;

    private MvcResult result;

    @When("^I register using email \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void iRegisterUsingEmailWithPassword(String arg0, String arg1) throws Throwable {
        this.mvc = webAppContextSetup(this.webApplicationContext).build();

        result = this.mvc.perform(post("/register")).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
