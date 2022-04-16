package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factorydrivers.DriverFactory;
import org.junit.Assert;

import utils.ConfigReader;
import utils.Util;
import org.apache.log4j.Logger;

public class LoginPage {

	private WebDriver driver;
	private HomePage hp;
	ConfigReader configreader=new ConfigReader();
	Logger log= Logger.getLogger(LoginPage.class); 
	Util Ut=new Util();;
	
	// Define Locators or Object Repository
	private By username = By.cssSelector("#username");
	private By password = By.cssSelector("#password");
	private By lognbtn = By.cssSelector("#Login");
	// private By frgtpassworddlink = By.linkText("Text of the link");
	
	// Create constructor for the class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Page actions
	public String gettile() {
		return driver.getTitle();
	}

	// public boolean passwordlink() {
	// return driver.findElement(frgtpassworddlink).isDisplayed(); // this will
	// return a boolean
	// }

	/*public void enterusername(String User)// UserName is same as the feature file
	{
		boolean flag_username = false;
		flag_username = Ut.WaitForElePresent(driver, username, 3);
			if (flag_username) {
				driver.findElement(username).click();
				driver.findElement(username).sendKeys(User);
				flag_username = true;
				log.info("Username entered");
			}
		Assert.assertTrue(flag_username);
	}*/

	/*public void enterpassword(String Password) {
			boolean flag_username = false;
		//try {
			flag_username = Ut.WaitForElePresent(driver, password, 3);
			if (flag_username) 
			{
				driver.findElement(password).click();
				driver.findElement(password).sendKeys(Password);
				flag_username = true;
				log.info("Password entered");
			}
			else
			{
				flag_username = false;
				
			}
		Assert.assertTrue(flag_username);
	}*/
	
	
	
	
	public void enterusername(String User)// UserName is same as the feature file
	{
		boolean flag_username;
		//flag_username = Ut.checkElementpresent(driver, username);
		flag_username=Ut.WaitForElePresent(driver, username, 3);
		if (flag_username) {
			driver.findElement(username).click();
			driver.findElement(username).sendKeys(User);
		} else {
			Assert.assertTrue(false);
		}
	}

	public void enterpassword(String Password) {
		boolean flag_password;
	//	flag_password = Ut.checkElementpresent(driver, password);
		flag_password=Ut.WaitForElePresent(driver, password, 3);
		if (flag_password) {
			driver.findElement(password).click();
			driver.findElement(password).sendKeys(Password);
		} else {
			Assert.assertTrue(false);
		}
	}

/*	public HomePage clicklngnbtn() {
		boolean flag_lgnbtn;
		//flag_lgnbtn = Ut.checkElementpresent(driver, lognbtn, 3);
		try
		{
		flag_lgnbtn=Ut.WaitForElePresent(driver, lognbtn, 3);
		if (flag_lgnbtn) {
			driver.findElement(lognbtn).click();
			hp= new HomePage(driver);
		} else {
			Assert.assertTrue(false);
		}
		}catch(Exception e)
		{e.printStackTrace();}
	}*/
		
	public HomePage clicklngnbtn() {
		boolean flag_lgnbtn;
		try {
			flag_lgnbtn = Ut.WaitForElePresent(driver, lognbtn, 3);
			// flag_lgnbtn=Ut.fluientWaitforElementTobePresent(driver, lognbtn);
			if (flag_lgnbtn) {
				driver.findElement(lognbtn).click();
				return new HomePage(driver);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("to print null");
		return null;
	}
	
	/*public HomePage dologin(String un, String pswrd) throws InterruptedException {
		driver.findElement(username).sendKeys(un);
		driver.findElement(password).sendKeys(pswrd);
		driver.findElement(lognbtn).click();
		return new HomePage(driver); // return object of homepage
	}*/
	
	public HomePage dologin2() throws InterruptedException {
		DriverFactory.getdriver().get(configreader.initproperties().getProperty("url"));
		driver.findElement(username).sendKeys(configreader.initproperties().getProperty("admin_username"));
		driver.findElement(password).sendKeys(configreader.initproperties().getProperty("admin_password"));
		driver.findElement(lognbtn).click();
		Thread.sleep(3000);
		return new HomePage(driver);
	}
	
	
}
