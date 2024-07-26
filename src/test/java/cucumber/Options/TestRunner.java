package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


// tags = "@DeletePlace")
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinitions" }, tags = "")
public class TestRunner {

}
