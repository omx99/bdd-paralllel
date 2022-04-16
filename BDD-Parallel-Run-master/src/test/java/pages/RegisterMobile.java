//package pages;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//
//import factorydrivers.DriverFactory;
//
//import static org.testng.Assert.assertEquals;
//
//import java.io.IOException;
//
//import org.junit.Assert;
//
//import utils.Util;
//
//public class RegisterMobile {
//
//	private WebDriver driver;
//	Util ul = new Util();
//	public static String remindermelater_label;
//	
//	public RegisterMobile(WebDriver driver) {
//		this.driver = driver;
//	}
//
//	@FindBy(css = "#header")
//	private WebElement registerheader;
//
//	@FindBy(css = "div:nth-child(1) div:nth-child(3) form:nth-child(1) p.small:nth-child(12) > a:nth-child(1)")
//	private WebElement remindermelater;
//
//	public String getregistertile() {
//		return driver.getTitle();
//	}
//
//	public boolean remindmelaterlabel_isdisplayed() {
//		return remindermelater.isDisplayed();
//	}
//
////	public void headerisdisplayed() {
////		boolean header;
////		header = ul.WaitForElePresent(registerheader, 10);
////		if (header) {
////			String expected = "Register Your Mobile Phone | Salesforce";
////			String actual = registerheader.getText();
////			assertEquals(expected, actual);
////		
////		} else {
////			Assert.assertTrue(false);
////		}
//
//	}
//
//	public String remindmelater() throws IOException {
//		try {
//			boolean remindermelater_flag;
//			remindermelater_flag = ul.WaitForElePresent(remindermelater, 50);
//			if (remindermelater_flag) {
//				remindermelater_label=remindermelater.getText();
//				} else {
//				Assert.assertTrue(false);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return remindermelater_label;
//	}
//	
//	public void remindmelater_click()
//	{
//		remindermelater.click();
//	}	
//	
//}
