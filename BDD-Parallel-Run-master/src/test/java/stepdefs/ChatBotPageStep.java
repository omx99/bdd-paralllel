package stepdefs;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factorydrivers.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPage;
import pages.ChatBotPage;
import utils.ConfigReader;
import utils.Util;

public class ChatBotPageStep {
	
	Util ul=new Util();
	private WebDriver driver;
	ConfigReader configreader=new ConfigReader();
	private ChatBotPage ChatBot = new ChatBotPage(DriverFactory.getdriver());
	
	
@Given("User Navigates to UAT browser")
public void user_navigates_to_uat_browser() throws InterruptedException, IOException {
DriverFactory.getdriver().get(configreader.initproperties().getProperty("url_uat"));
ChatBot.clickreadmore();
}

@Then("User clicks on Lets Chat")
public void user_clicks_on_lets_chat() throws Exception {
ChatBot.clickletschat();
ChatBot.getchattxt();
ChatBot.MainBubbles();		    
}

@Given("User clicks on Interested in your home bubble")
public void user_clicks_on_interested_in_your_home_bubble() throws IOException {
ChatBot.Interested();
}

@Then("clicks on Yes I would")
public void clicks_on_yes_i_would() {
    
}





}
