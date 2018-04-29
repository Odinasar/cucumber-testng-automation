package com.app.tests;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Demo - how to run Selenium tests in Remote cloud mashine using Selenium Grid
 * 
 * @author Beknazar
 *
 */
public class SeleniumGridDemo {
	WebDriver driver;

	public static final String URL = "http://54.161.217.151:4444/wd/hub";

	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setPlatform(Platform.ANY);
		

		driver = new RemoteWebDriver(new URL(URL), caps);
	}

	@Test
	public void testGoogle() throws InterruptedException {
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("Java programming" + Keys.ENTER);
		Thread.sleep(1000);
		assertTrue(driver.getTitle().startsWith("Java programming"));
		Thread.sleep(2000);
	}

	@AfterTest
	public void cleanUP() {
		driver.close();
	}
}
