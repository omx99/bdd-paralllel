package factorydrivers;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tdldriver = new ThreadLocal<>(); // used for parallel execution

	// Function to Initialise the browser
	public static WebDriver init_driver(String browser) {
		System.out.println("Value of browser is " + browser);
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--start-maximized");
			option.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			option.addArguments("--disable-notifications");
		//	option.setExperimentalOption("prefs", {"profile.block_third_party_cookies": True});

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			//prefs.put("profile.block_third_party_cookies", true);
			option.setExperimentalOption("prefs", prefs);
			
			tdldriver.set(new ChromeDriver(option));
		  } else if (browser.equals("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			tdldriver.set(new FirefoxDriver());
		} else {
			System.out.println("Browser not passed ");
		}
		return getdriver();
	}

	// Return the driver sync because we will have multiple threads running in
	// parallel
	public static synchronized WebDriver getdriver() {
		return tdldriver.get();
	}

}
