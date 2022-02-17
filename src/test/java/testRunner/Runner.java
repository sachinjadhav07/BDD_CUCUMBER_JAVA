package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
//		features = ".//Features/Customers.feature",
		features = ".//Features",
		glue = "stepdefinitions",
		dryRun = false,
		monochrome = true,
	plugin = { "pretty", "html:target/cucumber-reports/cucumber-pretty","me.jvt.cucumber.report.PrettyReports:target/cucumber" }
)

public class Runner {

}
