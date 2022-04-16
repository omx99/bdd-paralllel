package pages;

import static org.junit.Assert.assertEquals;

//import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;
import utils.Util;

public class HomePage {

	private WebDriver driver;
	Util ut = new Util();
	Properties prop;
	/*private String labelSandbox;
	private String Profilecheck;
	private String homepage_title_txt;
	private String headeractual;
	private String lead_headerexpected = "Leads";*/
	private String lead_headeractual;
	ConfigReader configreader = new ConfigReader();

	// private By SandBox= By.cssSelector("div[class='slds-notify_alert
	// system-message level-info slds-theme_info'] span");
	private By SandBox = By.xpath("//*[@class='slds-notify_alert system-message level-info slds-theme_info']/span");
	// private By applauncher= By.cssSelector("div[class='slds-icon-waffle']");
	private By profileclick = By.xpath(
			"//div[@class='profileTrigger branding-user-profile bgimg slds-avatar slds-avatar_profile-image-small circular forceEntityIcon']//span[@class='uiImage']");
	//private By logout = By.xpath("//div[@class=\"oneUserProfileCard\"]/div[2]/div/a[2]");
	//private By profiletxt = By.xpath("//div[@class=\"oneUserProfileCard\"]/div[2]/h1/a");
	public static By GloablSearch = By.xpath("//input[@placeholder='Search...']");
	public static By LeadSearch = By.xpath("//input[@placeholder='Search Leads and more...']");
	public static By Table = By
			.xpath("//div[@class='uiScroller scroller-wrapper scroll-bidirectional native']/div/div/table");
	public static By Tab = By
			.xpath("//nav[@class='slds-context-bar__secondary navCenter']/div/one-app-nav-bar-item-root");
	public static By LeadviewTable = By
			.xpath("//div[@class='listViewContent slds-table--header-fixed_container']//table");
	private By profilelogout = By.xpath(
			"//div[@class='userProfilePanel uiPanel--default uiPanel positioned south open active']//div[@class='profile-card-toplinks']/a[2]");
	private By Applauncher = By.xpath("//div[@class='slds-icon-waffle']");
	private By srchapp = By.xpath("//*[@placeholder='Search apps and items...']");
	private By clicklead = By.xpath("//div[@class='al-menu-dropdown-list']/one-app-launcher-menu-item/a[@id='Lead']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		// PageFactory.initElements(driver,this); use @FindBy
	}

	public String homepagetitle() {
		return driver.getTitle();
	}

	public void labelSandBox() throws IOException {
		try {
			boolean SandBox_label;
			Thread.sleep(1500);
			SandBox_label = ut.fluientWaitforElementTobePresent(driver, SandBox);
			if (SandBox_label) {
				String SandboxExpected = configreader.initproperties().getProperty("sandbox");
				String SandboxActual = driver.findElement(SandBox).getText();
				assertEquals(SandboxExpected, SandboxActual);
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void profilecheck() throws IOException {
		try {
			boolean profile_flag;
			profile_flag = ut.elementclickable(driver, profileclick, 120);
			if (profile_flag) {
				driver.findElement(profileclick).click();
				Thread.sleep(3000);
				/*
				 * Thread.sleep(1000);
				 * profile_flag=ut.fluientWaitforElementTobePresent(driver,profiletxt);
				 * System.out.println("Profileflag is"+ profile_flag);
				 */
				/*
				 * if(profile_flag) { Profilecheck=driver.findElement(profiletxt).getText(); }
				 */
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout() throws InterruptedException {
		boolean flag_logout = false;
		// Thread.sleep(1000);
		flag_logout = ut.elementclickable(driver, profilelogout, 120);
		// flag_logout = ut.WaitForElePresent(driver,profilelogout, 3);
		if (flag_logout) {
			Thread.sleep(2000);
			driver.findElement(profilelogout).click();
		} else {
			Assert.assertTrue(flag_logout);
		}

	}

	public void clickapplauncher() {
		try {
			boolean flag_Applauncher, flag_srchapp = false;
			// flag_Applauncher=ut.fluientWaitforElementTobePresent(driver, Applauncher);
			flag_Applauncher = ut.elementclickable(driver, Applauncher, 120);
			if (flag_Applauncher) {
				driver.findElement(Applauncher).click();
				Thread.sleep(2000);
				flag_srchapp = ut.elementclickable(driver, srchapp, 120);
				if (flag_srchapp) {
					driver.findElement(srchapp).click();
				} else {
					Assert.assertTrue(flag_srchapp);
				}
				Assert.assertTrue(flag_Applauncher);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendobject(String string) throws Exception {
		try {
			driver.findElement(srchapp).sendKeys(string);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// Clicking object from dropdown
	public LeadPage clickObject(String string) {
		try {
			boolean flag_clickLead;
			flag_clickLead = ut.fluientWaitforElementTobePresent(driver, clicklead);
			if (flag_clickLead) {
				List<WebElement> ele = driver.findElements(By.xpath(
						"//div[@class='al-menu-dropdown-list']/one-app-launcher-menu-item/a/div/lightning-formatted-rich-text/span/p"));
				Thread.sleep(1500);
				System.out.println(ele.size());
				for (int i = 1; i <= ele.size(); i++) {
					lead_headeractual = driver.findElement(By.xpath(
							"//div[@class='al-menu-dropdown-list']/one-app-launcher-menu-item/a/div/lightning-formatted-rich-text/span/p["
									+ i + "]"))
							.getText();
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					if (lead_headeractual.equalsIgnoreCase("Leads")) {
						driver.findElement(By.xpath(
								"//div[@class='al-menu-dropdown-list']/one-app-launcher-menu-item/a/div/lightning-formatted-rich-text/span/p["
										+ i + "]"))
								.click();
						Thread.sleep(2000);
						return new LeadPage(driver);
					}
				}
			}
			/*
			 * for (WebElement clickobject : ele) {
			 * 
			 * System.out.println(ele.get(index)
			 * 
			 * String clickingobject = clickobject.getText();
			 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 * System.out.println("before Leads clicks"); if
			 * (clickingobject.equalsIgnoreCase(Leads)) { clickobject.click();
			 * driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // break; } }
			 * }
			 */
			else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Global search on Homepage
		public void globalsearch(WebDriver driver, By by, String text) throws Exception {
			try {
				boolean textserach_flag;
				textserach_flag = ut.fluientWaitforElementTobePresent(driver, by);
				if (textserach_flag) {

					driver.findElement(by).sendKeys(text);

					driver.findElement(by).sendKeys(Keys.ENTER);

					// WebElement ele=(new WebDriverWait(driver, 10))
					// .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='listContent']")));
					//
					// WebElement getdata =
					// driver.findElement(By.xpath("//div[@class='listContent']/ul//div[2]/span"));
					//
					// String dropdown_txt = getdata.getAttribute("title");
					//
					// System.out.println(dropdown_txt);
					//
					// if (dropdown_txt.equalsIgnoreCase(text))
					// {
					// driver.findElement(By.xpath("//div[@class='listContent']/ul//div[2]/span")).click();
					// }

				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		}

		// Get Lead cell check and click same
		public void GetAndClickLeadName(WebDriver driver, String name) throws Exception {
			try {
				boolean element_flag;
				boolean leadname;

				element_flag = ut.fluientWaitforElementTobePresent(driver, HomePage.Table);

				if (element_flag) {

					// driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);

					WebElement table = (new WebDriverWait(driver, 60)).until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//div[@class='resultsMultiWrapper']//tbody/tr/th/span/a")));

					String testname = table.getText();
					Assert.assertEquals(testname, name);

					Thread.sleep(1000);

					// System.out.println("Clicking elemnt in table");
					driver.findElement(By.xpath("//div[@class='resultsMultiWrapper']//tbody/tr/th/span/a")).click();
					// System.out.println("Clicke on element in table");

				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		}

		// Tab Click
		public void tabclick(WebDriver driver) throws Exception {
			try {
				boolean tab_flag;
				// String str="Leads";
				tab_flag = ut.fluientWaitforElementTobePresent(driver, HomePage.Tab);
				if (tab_flag) {
					List<WebElement> ele = driver.findElements(By.xpath(
							"//nav[@class='slds-context-bar__secondary navCenter']/div/one-app-nav-bar-item-root/a/span"));
					for (WebElement tabname : ele) {
						String tabs = tabname.getText();
						// System.out.println(tabs);

						if (tabs.equalsIgnoreCase("Leads")) {
							JavascriptExecutor je = (JavascriptExecutor) driver;
							je.executeScript("arguments[0].click();", tabname);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		public void table(WebDriver driver, String name) throws Exception {
			try {
				/*int flag = 0;
				int count=0;*/
			
				WebElement table = driver.findElement(
						By.xpath("//div[@class='listViewContent slds-table--header-fixed_container']//table/tbody"));

				Thread.sleep(2000);

				// No.of Columns
				// List col = driver.findElements(By.xpath("//div[@class='listViewContent
				// slds-table--header-fixed_container']//thead/tr/th"));
				// No.of rows
				// List rows = driver.findElements(By.xpath("//div[@class='listViewContent
				// slds-table--header-fixed_container']//table/tbody/tr"));

				List<WebElement> rows = table.findElements(By.tagName("tr"));// get the rows
				
				//System.out.println(rowcount);
				
				//int rowc =rowcount.size();
				
				text_loop: 
				for (int row = 1; row <=rows.size(); row++) // iterate over row
				{
					List<WebElement> cols = rows.get(row).findElements(By.tagName("td"));// for row we need to iterarte
					
					int columnscount = cols.size();
					
					driver.findElement(By.xpath("//div[@class='listViewContent slds-table--header-fixed_container']/div[1]")).sendKeys(Keys.PAGE_DOWN);
					Thread.sleep(1500);
					
					Column_LOOP:
					for (int column = 1; column <=columnscount; column++) {
					WebElement test=driver.findElement(By.xpath("//div[@class='listViewContent slds-table--header-fixed_container']//table/tbody/tr["
								+ row + "]/td[" + (column + 2) + "]/span/a"));
					
					String click_txt=test.getText();
					
					System.out.println(click_txt);
			    	
					if (click_txt.equalsIgnoreCase(name))
					 	{
						 driver.findElement(By.xpath("//div[@class='listViewContent slds-table--header-fixed_container']//table/tbody/tr["
									+ row + "]/td[" + (column + 2) + "]/span/a")).click();
							break text_loop;
				    	}
			    	 break Column_LOOP;
					
					}
					
					//count++;
//							  }
//						}while((flag == 0) || ((count)==150));
//						
//						if (flag == 1) {
//							System.out.println("Element has been found.!!");
//						} else {
//							System.out.println("Element has not been found.!!");
//						}
					//}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		// spinner
		// public static boolean waitTillSpinnerDisable(WebDriver driver, By by)
		// {
		// FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver);
		// fWait.withTimeout(10, TimeUnit.SECONDS);
		// fWait.pollingEvery(250, TimeUnit.MILLISECONDS);
		// fWait.ignoring(NoSuchElementException.class);
		//
		// Function<WebDriver, Boolean> func = new Function<WebDriver, Boolean>()
		// {
		// @Override
		// public Boolean apply(WebDriver driver)
		// {
		// WebElement element = driver.findElement(by);
		// System.out.println(element.getCssValue("display"));
		// if(element.getCssValue("display").equalsIgnoreCase("none"))
		// {
		// return true;
		// }
		// return false;
		// }
		// };
		// return fWait.until(func);
		// }

	
}
