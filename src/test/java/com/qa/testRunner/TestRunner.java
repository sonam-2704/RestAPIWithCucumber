package com.qa.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/featurefile/GetPostFeature",
glue={"com/qa/stepDefinitions/GetPostSteps"},
plugin= {"pretty", "html:target/cucumber-reports/cucumber-pretty","json:target/cucumber-reports/CucumberTestReport.json"
		},
monochrome = true)

public class TestRunner {

}
