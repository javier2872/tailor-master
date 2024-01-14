package endtoend.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        // check if all the steps have the step definition
        //dryRun = true,
        // the paths of the features files
        features = {"src/test/resources/features"},
        // the paths of the step definition files
        glue = {"stepsDefinitions"},
        //which tags in feature file should execute
        tags = "@All_Tests",
        // its mean that the console output for cucumber test are much more
        // readable
        monochrome = true,
        // that options used to specify different formatting options for the
        // outputs reports
        plugin = {"html:target/cucumber-reports/cucumber-pretty.html", "json:target/json-cucumber-reports/default/cukejson.json",
                "testng:target/testng-cucumber-reports/cuketestng.xml"}

)
public class TestRunner extends AbstractTestNGCucumberTests{

}
