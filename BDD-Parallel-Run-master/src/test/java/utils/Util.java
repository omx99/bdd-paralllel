package utils;

import java.time.Duration;
import static org.junit.Assert.assertEquals;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

//import pages.HomePage;

public class Util {

	private WebDriver driver;

	public static String childwindow_header;

	public boolean WaitForElePresent(WebDriver driver, By by, int timeOut) {
		boolean checkElementpresent = false;
			for (int timer = 0; timer <= timeOut; timer++) {
				waitFor(1);
				try {
					checkElementpresent = driver.findElement(by).isDisplayed();
					if (checkElementpresent)
						break;
				} catch (Exception e) {
					System.out.println("Element is not present");
				}  
			}
			return checkElementpresent;
		}

	public void waitFor(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean webDriverWaitTextPresent(WebDriver driver, WebElement ele) {
		try {
			(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return ele.getText().length() != 0;
				}
			});
		} catch (Exception e) {
			{
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean fluientWaitforElementTobePresent(WebDriver driver, By element) {
		boolean status = false;
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofMinutes(2))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(Exception.class);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			status = true;
		} catch (Exception e) {
			status = false;
			System.out.println("Element not Present");
			e.printStackTrace();
		}
		return status;
	}

	public boolean isTextpresent(WebElement ele, String expectedstring) {
		boolean value = false;
		String actualstring = "";
		try {
			actualstring = ele.getText();
			if (actualstring.equals(expectedstring))
				value = true;
			else
				value = false;
			return value;
		} catch (Exception e) {
			System.out.println("isTextpresent exception catch block");
		}
		value = false;
		return value;
	}

	// Page load DOM status check
	public void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(pageLoadCondition);

	}

	public boolean elementVisible(WebDriver driver, By by, int time) {
		boolean found_flag = true;
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			WebElement temp = wait.until(ExpectedConditions.visibilityOf((WebElement) by));
			if (temp != null)
				found_flag = true;
		} catch (Exception e) {
			found_flag = false;
		}
		return found_flag;
	}
	
	public boolean elementclickable(WebDriver driver, By by, int time) {
		boolean profile_flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			WebElement temp = wait.until(ExpectedConditions.elementToBeClickable(by));
			if (temp != null)
				profile_flag = true;
				return profile_flag;
		} catch (Exception e) {
			profile_flag = false;
		}
		return profile_flag;
		
	}
	
	
	

	// public boolean checkElementpresent(WebDriver driver, By by) {
	// boolean checkelementpresent = false;
	// try {
	// checkelementpresent = driver.findElement(by).isDisplayed();
	// //System.out.println(checkelementpresent);
	// } catch (Exception e) {
	// e.printStackTrace();
	// return checkelementpresent;
	// }
	// return checkelementpresent;
	// }

	public boolean switchchildwindow(WebDriver driver, By element) {
		boolean element_flag;
		String mainWindowHandle = driver.getWindowHandle();
		// System.out.println(mainWindowHandle);
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();
		// System.out.println(iterator);
		// fetch the heading of the child window
		while (iterator.hasNext()) {
			String ChildWindow = iterator.next();
			// System.out.println(ChildWindow);
			if (mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				element_flag = fluientWaitforElementTobePresent(driver, element);
				if (element_flag) {
					WebElement text = driver.findElement(element);
					childwindow_header = text.getText();
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
					// System.out.println(childwindow_header);
					return true;
				} else {
					Assert.assertTrue(false);
				}
			}
		}
		return false;
	}

	// Random name generator
	public String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	// Random mobileNo generator starting with 99945
	public long generateNumber() {
		return (long) (Math.random() * 100000 + 9994500000L);
	}

	// Scroll element
	public void scroll(WebElement element) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView();", element);

		} catch (Exception e) {

			// System.out.println(e.getMessage());
		}
	}

	// Scroll element
	public void jsclick(WebElement ele) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	
	
}
