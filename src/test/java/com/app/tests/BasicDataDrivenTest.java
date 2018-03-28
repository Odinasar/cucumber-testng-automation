package com.app.tests;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.app.pages.GasMileageCalculatorPage;
import com.app.utilities.Driver;

public class BasicDataDrivenTest {

	WebDriver driver;
	Workbook workbook;
	Sheet worksheet;
	GasMileageCalculatorPage page;
	@BeforeClass
	public void setUp() {
		driver = Driver.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.calculator.net/gas-mileage-calculator.html");
	}
	
	@Test
	public void dataDrivenMilleageCalculatorTest() {
		double currentOr = 3456;
		double previousOr = 2345;
		double gas = 30;
		
		page = new GasMileageCalculatorPage();
		page.currentOdemeter.sendKeys(String.valueOf(currentOr));
		page.previousOdometer.sendKeys(String.valueOf(previousOr));
		page.gas.sendKeys(String.valueOf(gas));
		page.calculateButton.click();
		String[] result = page.resultText.getText().split(" ");
		System.out.println(result[0]);
		
		double calculationResult = (currentOr - previousOr)/gas;
		DecimalFormat format = new DecimalFormat("#.##");
		
		System.out.println(format.format(calculationResult));
		if(result[0].equals(format.format(calculationResult))) {
			System.out.println("pass");
		}else {
			System.out.println("fail");
		}
	}
	
	
	
	
	

}
