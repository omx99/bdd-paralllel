package stepdefs;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factorydrivers.DriverFactory;

//For 4.2.0
//import cucumber.api.Scenario;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.Given;

//Below for 6.9.0
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utils.ConfigReader;

/**
 * This class acts as a precondition class we are calling the driver class where
 * we are creating instance of driver
 *
 */
public class Hooks {

	//private DriverFactory drivers;
	private WebDriver driver;
	private ConfigReader configreader;
	Properties prop;
	Scenario scenario;
	DriverFactory driverfactory;

	@Before(order = 0) // this will get first
	public void getproperty() {
		configreader = new ConfigReader();
		prop = configreader.initproperties(); // we are calling the config properties class
	}

	@Before(order = 1) // this will get second
	public void getbrowser() {
		//String browsername = System.getProperty("BrowserName");
		String browsername = prop.getProperty("browser"); // key is passed from prop file 
		System.out.println("Browser is :" + browsername);
		//drivers = new DriverFactory();
		driver = DriverFactory.init_driver(browsername); // we stored driver in the driver declared in this class
	}

	@After(order = 0) // this will get executed at last
	public void quitdriver() {
		//driver.quit();
		DriverFactory.getdriver().close();
		
	}

	@After(order = 1) // this will take screen shot in case of failing
	public void TakeScreenShot(Scenario scenario) // Creating Scenario Object
	{
		if (scenario.isFailed()) {
			String screenshotname = scenario.getName().replaceAll("", "_");// with scenario object take screenshot and
																			// put in string
			byte[] sourcepath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES); // take screenshot and
																								// save
			
			scenario.attach(sourcepath, "image/png", screenshotname); // attaching the screen shot 6.9.0 
		    //scenario.embed(sourcepath, "image/png"); // attaching the screen shot 4.2.0
			
		}
	}

}