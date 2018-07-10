package acme.e2e;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = { "pretty", "io.cucumber.pro.JsonReporter:all"},
    glue = {"acme"}
)
@SpringBootTest
public class WebTestRunner {

}