package acme;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "io.cucumber.pro.JsonReporter:all"},
    features = {"classpath:src/test/java"}
)
public class RunCukesTest {
}
