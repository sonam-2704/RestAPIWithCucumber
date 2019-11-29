package com.qa.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/featurefile/GetPostWithJson.feature",
glue={"com/qa/stepDefinitions1"},
plugin= {"pretty", "html:target/cucumber-reports/cucumber-pretty","json:target/cucumber-reports/CucumberTestReport.json"
		},
monochrome = true)

public class TestRunnerWithJson {

}
