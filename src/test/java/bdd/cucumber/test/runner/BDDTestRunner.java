package bdd.cucumber.test.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/inventoryBDDtest.feature"},
        glue = {"bdd.cucumber.test"}
        ,plugin = {"html:build/cucumber/cucumber-html-report", "json:build/cucumber/cucumber.json", "junit:build/cucumber/cucumber.xml", "pretty:build/cucumber/cucumber.txt"}  // Output folder and HTML file.
        ,monochrome = true  // Display the Console output in a proper readable format. Brackets and all will be gone.
)

public class BDDTestRunner  {

}
