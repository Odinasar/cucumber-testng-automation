package com.app.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.BrowserUtils;
import com.app.utilities.Driver;

public class SuitCRMDashboard {
    private WebDriver driver;
    public SuitCRMDashboard() {
    	this.driver = Driver.getDriver();
    	PageFactory.initElements(driver, this);
    }
    
    @FindBy(css= ".topnav>span>a")
    public List<WebElement> moduls;
    
    @FindBy (id = "text")
    public WebElement textBox;
    
    @FindBy(xpath = "(//input[@value='Post'])[1]")
    public WebElement postButton;
    
    @FindBy(id ="with-label")
    public WebElement profileMenu;
    
    @FindBy(xpath = "(//a[@id='logout_link'])[3]")
    public WebElement logoutlink;
    
    @FindBy(xpath = "(//button[@id ='searchbutton'])[3]")
    public WebElement searchButton;
    
    @FindBy(xpath = "(//input[@id='query_string'])[5]")
    public WebElement searchBox;
    
    @FindBy(id = "grouptab_1")
    public WebElement marketingMenu;
    
    @FindBy(css = "span[class='notCurrentTab open']>ul>li>a[id='moduleTab_9_Contacts']")
    public WebElement contactsLink;
    
    @FindBy(css = "div[class='desktop-bar']>ul>li>a")
    public WebElement createMenu;
    
    @FindBy(xpath = "//div[@class='desktop-bar']//a[.='Create Task']")
    public WebElement createTaskLink;
    
    
    public void clickCreateTask() {
    	createMenu.click();
        createTaskLink.click();
    }
    

    
    public void openContact(String contactName) {
      driver.findElement(By.xpath("(//a[contains(text(),'"+contactName+"')])[1]")).click();   	
    }
    
    public void openContacts() {
    	Actions action = new Actions(driver);
    	action.moveToElement(marketingMenu).click().perform();
    	BrowserUtils.waitForVisibility(contactsLink, 3);
    	contactsLink.click();
    	
    }

    
    public boolean verifyPostDisplayed(String value) {
    	return driver.findElement(By.xpath("//div[contains(text(),'"+value+"')]")).isDisplayed();
    }
    
    public void post(String value) {
    	textBox.sendKeys(value);
    	postButton.click();
    }
    
    
    
    public boolean verifyElementsDispayed(List<WebElement> listOfElements) {
    	for(WebElement e : listOfElements) {
    		if(!e.isDisplayed()) {
    			System.out.println(e.getText()+" does dispayed");
    			return false;
    		}
    	}
    	return true;
    }

	public void logout() {
		Actions action = new Actions(driver);
		action.moveToElement(profileMenu).perform();
		BrowserUtils.waitForVisibility(logoutlink, 5);
		logoutlink.click();
		
	}
	

}
