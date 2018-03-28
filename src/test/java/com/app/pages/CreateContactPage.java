package com.app.pages;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class CreateContactPage {
    private WebDriver driver;
    public CreateContactPage() {
    	driver = Driver.getDriver();
    	PageFactory.initElements(driver, this);
    }
    SuitCRMContacts contactPage = new SuitCRMContacts();
    
    @FindBy(id = "first_name")
    public WebElement firstNameBox;
    
    @FindBy(id = "last_name")
    public WebElement lastNameBox;
    
    @FindBy(id = "phone_work")
    public WebElement officePhoneBox;
    
    @FindBy(id = "phone_mobile")
    public WebElement phoneNumberBox;
    
    @FindBy(id = "department")
    public WebElement departmentBox;
    
    @FindBy(css = "#SAVE")
    public WebElement saveButton;
    
//    public String getNotNull(Map<String,String> map,String key) {
//    	if(map.get(key)!=null) {
//    		return map.get(key);
//    	}
//    	return null;
//    }
    public void saveIt() {
    saveButton.click();;
	contactPage.saveContact.click();
    }
}
