package StepDefinitions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
 
@CucumberOptions(
	    tags = "",
	    features = {"src/test/resources/Features/Login.feature"},
	    glue = {"StepDefinitions"},
	    plugin = {"pretty", "html:target/cucumber-reports/report"}
	)
	public class TestRunner extends AbstractTestNGCucumberTests {
	    // Your configurations here
	}