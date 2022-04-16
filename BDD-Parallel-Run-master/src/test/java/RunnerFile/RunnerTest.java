package RunnerFile;

import org.junit.runner.RunWith;

//below for 6.9.0
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

//below for 4.2.0
//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature",
		// tags = "@Login and @Home",
		//tags = "@Smoke1 and @Home",
		// tags = "@Home",
		dryRun = false, glue = { "stepdefs", "AppHooks" }, monochrome = true, plugin = { "pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
// "timeline:test-output-thread/,
// "json:target/cucumber-reports/CucumberTestReport.json"})

public class RunnerTest {

}
