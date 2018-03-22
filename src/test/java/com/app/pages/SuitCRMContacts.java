package com.app.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class SuitCRMContacts {
	private WebDriver driver;

	public SuitCRMContacts() {
		driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "first_name")
	public WebElement firstName;

	@FindBy(id = "last_name")
	public WebElement lastName;

	@FindBy(css = "a[class= 'email-link']")
	public WebElement email;

	@FindBy(css = "a[data-action-name='Create_Contact_Vcard']")
	public WebElement createContactVCardLink;

	@FindBy(id = "vcard_file")
	public WebElement vCardInput;

	@FindBy(id = "import_vcard_button")
	public WebElement importVcard;

	@FindBy(xpath = "//a[contains(text(),'ACTIONS')]")
	public WebElement actions;

	@FindBy(id = "merge_duplicate_button")
	public WebElement findDuplicatesLink;

	@FindBy(id = "next_step_button")
	public WebElement nextStepButton;

	@FindBy(css = "#massall_top")
	public WebElement selectAll;

	@FindBy(id = "perform_merge_button")
	public WebElement performMerge;

	@FindBy(id = "save_merged_record_button")
	public WebElement saveMerge;
	
	@FindBy(xpath = "//div[.='View Contacts']")
	public WebElement viewContacts;
		
	public int getNumberOfContacts(String contactName) {
		List<WebElement> contacts = driver.findElements(By.xpath("//a[contains(text(),'John Doe')]"));
		return contacts.size();
	}

	public void deleteDuplicates() {
		actions.click();
		findDuplicatesLink.click();
		nextStepButton.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", selectAll);
		performMerge.click();
		saveMerge.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void importVCard(String path) {
		createContactVCardLink.click();
		vCardInput.sendKeys(path);
		importVcard.click();
	}

	public String getFullName() {
		return firstName.getText() + " " + lastName.getText();
	}

}
