package com.orcunguducu.dynamic;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/com/orcunguducu/dynamic/features"},
		monochrome = true,
		plugin = {"html:target/cucumber-html-report", 
				  "json:target/cucumber-json-report.json" })
@Category(IntegrationTest.class)
public class ApplicationBDDTests {

}
