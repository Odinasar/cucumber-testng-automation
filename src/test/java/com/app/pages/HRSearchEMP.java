package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class HRSearchEMP {
    private WebDriver driver;
    public HRSearchEMP() { 
    	driver = Driver.getDriver();
    	PageFactory.initElements(driver, this);
    }
    
    @FindBy(id = "qryId1:val00::content")
    public WebElement idBox;
    
    @FindBy(id = "qryId1::search")
    public WebElement searchButton;
    
    @FindBy(id = "it1::content")
    public WebElement empID;
    
    @FindBy(id="it2::content")
    public WebElement empFirstName;
    
    @FindBy(id = "it3::content")
    public WebElement empLastName;
    
    @FindBy(id="it4::content")
    public WebElement empEmail;
    
    @FindBy(id = "sal::content")
    public WebElement empSalary;
    
    @FindBy(id = "id1::content")
    public WebElement empHireDate;
    
    @FindBy(id = "jobIdId::content")
    public WebElement empJobId;
    
    @FindBy(id="it6::content")
    public WebElement empDepId;
    
    @FindBy(id="it7::content")
    public WebElement empDepName;
    
    @FindBy(id="it8::content")
    public WebElement empAnnualSalary;
    
}
