package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Util;

public class ChatBotPage {

	private WebDriver driver;
	Util ut = new Util();

	private By ClickReadMore = By.xpath("//div[@id='onetrust-banner-sdk']/div/div/div[3]/a");
	//private By Letschat = By.xpath("//div[@class='embeddedServiceHelpButton']/div/button/span[2]/span[2]");
	
	private By Letschat_btn = By.xpath("//div[@class='embeddedServiceHelpButton']/div/button");
	private By WelCome_txt= By.xpath("//div[@class='dockableContainer showDockableContainer']//ul[@class='messageWrapper']/li[1]/div//lightning-formatted-rich-text/span");
	private By InterestedClick = By.xpath("//div[@class='dockableContainer showDockableContainer']//ul[@class='messageWrapper']/li[3]/div/button[1]");
	private By InterestedClick_txt= By.xpath("//div[@class='dockableContainer showDockableContainer']//ul[@class='messageWrapper']/li[4]/div[2]//lightning-formatted-rich-text/span");
	
	List<String> BotChat_txt_expected = Arrays.asList("Hi! Welcome to Meritage Homes! My name is Meritage Bot.","I'd love to help you find your next home but before that, what brings you to our site today?");
	List<String> BotChat_txt_actual = new ArrayList<>();
	
	List<String> MainBubble_expected = Arrays.asList("Interested in your homes","Currently own a Meritage Home","I am a Real Estate Professional");
	List<String> MainBubble_actual = new ArrayList<>();
	
	public String Interested_expected_txt="I need to get some information from you so we can get you to the correct person.(First Name, Last Name, Email & Phone)";
	
	////div[@class='dockableContainer showDockableContainer']//ul[@class='messageWrapper']/li[5]//button
	
	// Create constructor for the class
	public ChatBotPage(WebDriver driver) {
		this.driver = driver;
	}

	// Click ReadMore
	public void clickreadmore() throws IOException {
		try {
			
			boolean flag_clickreadmore;
			flag_clickreadmore = ut.fluientWaitforElementTobePresent(driver, ClickReadMore);
			if (flag_clickreadmore) {
				driver.findElement(ClickReadMore).click();
				//Thread.sleep(1000);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// get title of page
	public String gettitle() {
		return driver.getTitle();
	}

	public void clickletschat() throws IOException {
		try {
			boolean flag_clickletschat;
			//Thread.sleep(1000);
			flag_clickletschat = ut.fluientWaitforElementTobePresent(driver, Letschat_btn);
			if (flag_clickletschat) {
			//driver.findElement(Letschat).click();
			WebElement ele=driver.findElement(By.xpath("//div[@class='embeddedServiceHelpButton']/div/button"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Main Menu text
	public void getchattxt() throws Exception {
		try {
			Thread.sleep(1000);
			boolean flag_chatbottext;
			flag_chatbottext = ut.fluientWaitforElementTobePresent(driver,WelCome_txt);
			if (flag_chatbottext) {
				List<WebElement> ele = driver.findElements(By.xpath(
						"//div[@class='dockableContainer showDockableContainer']//ul[@class='messageWrapper']/li/div//div/lightning-formatted-rich-text/span"));
				//System.out.println(ele.size());
				for(int i=0;i<ele.size();i++)
				{
					String actual=ele.get(i).getText();
					//System.out.println(actual);
					String noSpaceStr = actual.replaceAll("\\r|\\n", "");
					BotChat_txt_actual.add(noSpaceStr);
				}
				Thread.sleep(500);
				Assert.assertEquals(BotChat_txt_expected, BotChat_txt_actual);
				//System.out.println("text matched");
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	//Main Menu Bubbles 
	public void MainBubbles() throws Exception {
		try {
				List<WebElement> ele = driver.findElements(By.xpath("//div[@class='dockableContainer showDockableContainer']//ul[@class='messageWrapper']/li[3]/div/button"));
				System.out.println(ele.size());
				for(int i=0;i<ele.size();i++)
				{
					String actual=ele.get(i).getText();
					//System.out.println(actual);
					MainBubble_actual.add(actual);
				}
				Thread.sleep(500);
				Assert.assertEquals(MainBubble_expected, MainBubble_actual);
			} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	//Click Interested in your homes text
		public void Interested() throws IOException {
			try {
			    boolean flag_Interested_btn,flag_Interested_txt;
			    flag_Interested_btn = ut.fluientWaitforElementTobePresent(driver, InterestedClick);
				if(flag_Interested_btn) {
					driver.findElement(InterestedClick).click();
					flag_Interested_txt = ut.fluientWaitforElementTobePresent(driver, InterestedClick_txt);
					if(flag_Interested_txt)
					{
					String Interested_actual_txt=driver.findElement(InterestedClick_txt).getText();
					String text=Interested_actual_txt.replaceAll("\\r|\\n", "");
					Assert.assertEquals(text,Interested_expected_txt);
					//Thread.sleep(1000);
					}
				} else {
					Assert.assertTrue(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		public void clickYesIwould()
		{
			try
			{
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}
		
}
