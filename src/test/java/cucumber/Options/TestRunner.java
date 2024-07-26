package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


// tags = "@DeletePlace")

// maven command to run based on parameters
// mvn test -D cucumber.filter.tags="@DeletePlace"
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", plugin="json:target/jsonReports/cucumber-report.json", glue = { "stepDefinitions" }, tags = "" )
public class TestRunner {

}
