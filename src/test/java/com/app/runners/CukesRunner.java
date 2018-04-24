package com.app.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(   
		plugin = {"pretty",
			  "html:target/cucumber-report",	
			  "json:target/cucumber.json"
		},
		tags = " @tag1",
		//                    UserinterfaceTest.feature  if we will put after / it will run certain feature file
		features= {"src/test/resources/com/app/features/","src/test/resources/com/app/features/hrapp_features"},
		// this line refer to the all the feature files. 
		glue="com/app/step_defenitions/",
		dryRun = false
		)
public class CukesRunner extends AbstractTestNGCucumberTests {
    
}
