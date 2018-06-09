package com.app.step_defenitions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.app.utilities.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	@Before
	public void setUp() {
		WebDriver driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@After
	public void teardown(Scenario scenario) {
		
		if(scenario.isFailed()) {
			// taking a screenshot 
			final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			// adding the screenshot to the report 
			scenario.embed(screenshot, "image/png");
		}
		Driver.closeDriver();
	}
}
