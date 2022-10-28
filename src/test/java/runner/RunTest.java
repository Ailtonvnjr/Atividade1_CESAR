package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        plugin = {"pretty", "json:target/cucumber.json", "html:target/cucumber-report"},
        monochrome = true,
        glue = {"steps"},
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class RunTest {
}
