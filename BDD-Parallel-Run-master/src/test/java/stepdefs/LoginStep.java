package stepdefs;

import factorydrivers.DriverFactory;
//for 6.9.0
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//4.0.0
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import pages.HomePage;
import pages.LeadPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.Util;


public class LoginStep {

	// first hook will get executed and get driver will get the driver instance
	private LoginPage loginpage = new LoginPage(DriverFactory.getdriver());
	private HomePage homepage = new HomePage(DriverFactory.getdriver());
	
	private String title;
	Util ul=new Util();
	ConfigReader configreader=new ConfigReader();
	
	@Given("User is on Login Page")
	public void user_is_on_login_page() throws InterruptedException {
			DriverFactory.getdriver().get(configreader.initproperties().getProperty("url"));
		}

	@When("User gets the Title of the Page")
	public void user_gets_the_title_of_the_page() {
		title = loginpage.gettile();
		System.out.println("title is :" +  title);
	}

	@Then("page Title should be {string}")
	public void page_title_should_be(String expectedtitle) {
		assertEquals(title,expectedtitle);
	}

	@When("User enters valid UserName {string}")
	public void user_enters_valid_user_name(String User) {
		loginpage.enterusername(User);
	}

	@When("User enters valid Password {string}")
	public void user_enters_valid_password(String pswrd) {
		loginpage.enterpassword(pswrd);
	}

	@And("User clicks on Login btn")
	public void user_clicks_on_login_btn() throws InterruptedException {
		homepage=loginpage.clicklngnbtn();
	}

	@Then("user navigates to home page and title is {string}")
	public void user_navigates_to_home_page_and_title_is(String homepagetitle) throws IOException, InterruptedException {
		/*if(homepage==null) 
		{	return;}*/
		homepage.labelSandBox();
	}

	@Then("User clicks on profile")
	public void user_clicks_on_profile() throws IOException {
		homepage.profilecheck();
	}
	
	@Then("User clicks to logout")
	public void user_clicks_to_logout() throws InterruptedException, IOException {
		//HomePage hp =new HomePage(driver);
		homepage.logout();
		}
		
	

}
