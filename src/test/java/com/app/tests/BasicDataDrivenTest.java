package com.app.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.app.pages.GasMileageCalculatorPage;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;

public class BasicDataDrivenTest {

	WebDriver driver;
	Workbook workbook;
	Sheet worksheet;
	GasMileageCalculatorPage page;
	FileInputStream inStream;
	FileOutputStream outStream;
	public static final int CURRENTOD_COLUMN = 1;
	public static final int PREVIOUSOD_COLUMN = 2;
	public static final int GAS_COLUMN = 3;
	public static final int ACTUAL_COLUMN = 5;
	public static final int EXPECTED_COLUMN = 4;
	public static final int STATUS_COLUMN = 6;
	public static final int TIME_COLUMN = 7;

	@BeforeClass
	public void setUp() throws Exception {
		inStream = new FileInputStream(ConfigurationReader.getProperty("gasmileage.testdata.path"));
		workbook = WorkbookFactory.create(inStream);
		worksheet = workbook.getSheetAt(0);

		driver = Driver.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.calculator.net/gas-mileage-calculator.html");
	}

	@AfterClass
	public void tearDown() throws IOException {
		outStream = new FileOutputStream(ConfigurationReader.getProperty("gasmileage.testdata.path"));
		workbook.write(outStream);
		outStream.close();
		workbook.close();
		inStream.close();
		driver.close();
	}

	@Test
	public void dataDrivenMilleageCalculatorTest() throws Exception {
		for (int rownum = 1; rownum < worksheet.getPhysicalNumberOfRows(); rownum++) {
			
			Row currentRow = worksheet.getRow(rownum);
			if(!currentRow.getCell(0).toString().equalsIgnoreCase("Y")) {
				if(currentRow.getCell(STATUS_COLUMN) == null) {
					currentRow.createCell(STATUS_COLUMN);
				}
				currentRow.getCell(STATUS_COLUMN).setCellValue("Skip Requested");
				continue;
			}
			double currentOr = currentRow.getCell(CURRENTOD_COLUMN).getNumericCellValue();
			double previousOr = currentRow.getCell(PREVIOUSOD_COLUMN).getNumericCellValue();
			double gas = currentRow.getCell(GAS_COLUMN).getNumericCellValue();

			page = new GasMileageCalculatorPage();
			page.currentOdemeter.clear();
			page.currentOdemeter.sendKeys(String.valueOf(currentOr));
			page.previousOdometer.clear();
			page.previousOdometer.sendKeys(String.valueOf(previousOr));
			page.gas.clear();
			page.gas.sendKeys(String.valueOf(gas));
			page.calculateButton.click();
			// actual result 
			String[] result = page.resultText.getText().split(" ");
			String actualResult = result[0].replace(",", "");
			// write to actual result 
			
			
			if(currentRow.getCell(ACTUAL_COLUMN) == null) {
				currentRow.createCell(ACTUAL_COLUMN);
			}
			currentRow.getCell(ACTUAL_COLUMN).setCellValue(actualResult);
			System.out.println(actualResult);

			double calculationResult = (currentOr - previousOr) / gas;
			DecimalFormat format = new DecimalFormat("##.00");
			System.out.println(format.format(calculationResult));
			if(currentRow.getCell(EXPECTED_COLUMN) == null) {
				currentRow.createCell(EXPECTED_COLUMN);
			}
			currentRow.getCell(EXPECTED_COLUMN).setCellValue(format.format(calculationResult));
			
			
			if(currentRow.getCell(STATUS_COLUMN) == null) {
				currentRow.createCell(STATUS_COLUMN);
			}
	
			if (actualResult.equals(format.format(calculationResult))) {
				System.out.println("pass");
				currentRow.getCell(STATUS_COLUMN).setCellValue("PASS");
				
			} else {
				System.out.println("fail");
				currentRow.getCell(STATUS_COLUMN).setCellValue("FAIL");
			}
			
			// WRITE CURRENT TIME 
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter customUnder = DateTimeFormatter.ofPattern("MMMM/dd/yyyy hh:mm");
			if(currentRow.getCell(TIME_COLUMN ) == null) {
				currentRow.createCell(TIME_COLUMN );
			}
			currentRow.getCell(TIME_COLUMN ).setCellValue( customUnder.format(now).toString());
		}
	}

}
