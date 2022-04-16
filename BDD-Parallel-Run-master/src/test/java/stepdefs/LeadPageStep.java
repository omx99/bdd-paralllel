package stepdefs;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import factorydrivers.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;
import pages.LeadPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.Util;

import static org.junit.Assert.assertEquals;

public class LeadPageStep {

	private HomePage homepage = new HomePage(DriverFactory.getdriver());
	private LeadPage leadpage=new LeadPage(DriverFactory.getdriver());
	ConfigReader config = new ConfigReader();

	@Given("user clicks on AppFinder searches for Leads Option and clicks same")
	public void user_clicks_on_app_finder_searches_for_leads_option_and_clicks_same() throws Exception {
	    homepage.clickapplauncher();
		homepage.sendobject("Leads");
		leadpage=homepage.clickObject("Leads");
		//if(leadpage==null)
		//{return;}
	}

	@Then("user clicks on new button to create New Lead")
	public void user_clicks_on_new_button_to_create_new_lead() throws InterruptedException {
		leadpage.newlead();
		String newleadwin_txt = config.initproperties().getProperty("newlead_window");
		assertEquals(newleadwin_txt, Util.childwindow_header);
		leadpage.name_contacttype_validation();
		leadpage.closerror();
	}

	@And("user enters Last Name and selects {string} from Contact type")
	public void user_enters_Last_Name_and_selects_phone_from_Contact_type(String str) throws InterruptedException {
		leadpage.lastname(); 
		leadpage.contacttype_select(str); // user selects phone contact type from dropdown
	}

	@Then("user clicks Save btn")
	public void user_clicks_save_btn() {
		leadpage.savebtn();
	}

	@And("user gets validation error for emailid or phoneno and marketing source when not entered and selects {string} marketingsource")
	public void user_gets_validation_error_for_emailid_or_phoneno_and_marketing_source_when_not_entered(String str)
			throws Exception {
		leadpage.Email_Phone_MarketingSource_Validation();
		leadpage.closerror();
		leadpage.MarketingSource_dropdown(str);
	}

	@Then("user enters invalid {string} and {string} and clicks Save btn")
	public void user_enters_invalid_and_and_clicks_save_btn(String emailid, String phoneno) {
		leadpage.Emailid_Phoneno(emailid, phoneno);
	}

	@Then("enters valid LastName PhoneNo {string} {string} {string} and clicks Save btn")
	public void enters_valid_last_name_phone_no(String email, String contacttype, String marketingSource) throws Exception {
		leadpage.lastname();
		leadpage.PhoneNo();
		leadpage.Emailid_Contacttype_MarketSource_Validdata(email, contacttype, marketingSource);
		leadpage.savebtn();
	}

//	@Then("user enters the LeadName to search the Lead if created and clicks when displayed")
//	public void user_enters_the_lead_name_to_search_the_lead_if_created_and_clicks_when_displayed() throws Exception {
//		Leadpg.searchlead();
//	}

}
