package com.app.step_defenitions;
import static org.testng.Assert.assertEquals;
import java.util.List;
import com.app.pages.SuitCRMDashboard;
import com.app.utilities.BrowserUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MenuOptionsStepDef {
	SuitCRMDashboard dashPage = new SuitCRMDashboard();
	@When("^I hover over the (Collaboration|Sales|Marketing|Support|All|Activities) menu$")
	public void i_hover_over_the_Collaboration_menu(String menu) {
	    switch(menu) {
	    case "Sales":
	    	BrowserUtils.hover(dashPage.salesMenu);
	    	break;
	    case "Marketing":
	    	BrowserUtils.hover(dashPage.marketingMenu);
	    	break;
	    case "Collaboration":
	    	BrowserUtils.hover(dashPage.collobarationMenu);
	    	break;
	    case "Support":
	    	BrowserUtils.hover(dashPage.supportMenu);
	    	break;
	    case "All":
	    	BrowserUtils.hover(dashPage.allMenu);
	    	break;
	    case "Activities":
	        BrowserUtils.hover(dashPage.activitiesMenu);
	        break;
	    	
	    }
	}

	@Then("^following menu options should be visible for (Collaboration|Sales|Marketing|Support|All|Activities):$")
	public void following_menu_options_should_be_visible_for_Collaboration(String menu,List<String> options) {
		// convert to List<String> 
		List<String> topMenuOptionsString = BrowserUtils.convertToString(dashPage.topMenuOptions(menu));
		// compare the size of lists 
		assertEquals(options.size(),topMenuOptionsString.size(),
				"Number of expected menu options did not match");
		// verify options match
		for(int i = 0; i < options.size(); i++) {
			assertEquals(options.get(i),topMenuOptionsString.get(i));
		}
	}
}
