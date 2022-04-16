package stepdefs;

import static org.junit.Assert.assertEquals;
//For 6.9.0
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

//For 4.0.0
/*import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;*/
 
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

//import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factorydrivers.DriverFactory;

public class HomePageStep {

	private LoginPage loginpage = new LoginPage(DriverFactory.getdriver());
	private HomePage homepage =new HomePage(DriverFactory.getdriver());
	
	
	@Given("user logins in")
	public void user_logins_in() throws InterruptedException
	{
		homepage=loginpage.dologin2();
	}
	
	@Then("User clicks on profile button")
	public void user_clicks_on_profile_button() throws IOException{
		homepage.profilecheck();
	}


}
