package pages;

import static org.junit.Assert.assertEquals;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;
import utils.Util;

public class LeadPage {

	private WebDriver driver;
	private LeadPage Leadp;
	private HomePage hp;
	Util ut = new Util();
	ConfigReader config = new ConfigReader();
	private String headeractual;


	List<String> expected_name_contacttype = Arrays.asList("Name", "Contact Type");
	List<String> actual_name_contacttype = new ArrayList<>();
	
	List<String> expected_contact_dropdown = Arrays.asList("--None--", "Phone", "Chat", "Web", "Walk-In", "SMS");
	List<String> actual_contact_dropdown = new ArrayList<>();
	
	List<String> expected_Email_Phone_MarketingSource = Arrays.asList("Email or Phone is required for this lead.",
			"Please select a marketing source.");
	List<String> actual_Email_Phone_MarketingSource = new ArrayList<>();
	
	List<String> expected_error_email = Arrays.asList("Email");
	List<String> actual_error_email = new ArrayList<>();
	
	List<String> expectedmarket = Arrays.asList("Zillow", "Yahoo");

	//private By appfinderframe = By.xpath("//div[@class='panel-content scrollable']");
	private By LeadPageHeader = By.xpath("//*[@class='slds-breadcrumb__item slds-line-height--reset']/span");
	private By NewLead_btn = By.xpath("//*[@data-aura-class='oneActionsRibbon forceActionsContainer']/li/a/div");
	// private By NewLead_btn = By.xpath("//*[@title='New']");

	private By NewLead_window = By.xpath("//h2[@class='slds-modal__title slds-hyphenate slds-text-heading--medium']");
	//private By Salutation_dropdown = By.xpath("//input[@placeholder='None']");
	private By Save_btn = By.xpath("//button[@name='SaveEdit']");
	private By contact_error = By.xpath("//*[@class='errorsList slds-list_dotted slds-m-left_medium']");
	private By close_error = By.xpath("//*[@title='Close error dialog']");
	private By lastname = By.xpath("//input[@placeholder='Last Name']");
	private By Contacttype_drpdwn = By.xpath(
			"//lightning-combobox[@class='slds-form-element_stacked slds-form-element slds-has-error'] //label[text()='Contact Type']/following-sibling::div/lightning-base-combobox/div/div/input");
	private By Contacttype_label = By.xpath("//*[text()='Contact Type']");
	private By Emailid = By.xpath("//*[@inputmode='email']");
	private By MarketingSource = By.xpath(
			"//label[text()='What marketing source? (6)']/following-sibling::div/lightning-base-combobox/div/div[2]");
	private By Phoneno = By.xpath("//input[@name='Phone']");
	//private By leadsearch = By.xpath("//input[@name='Lead-search-input']");
	private By leadsView = By.xpath("//*[@class='triggerLinkTextAndIconWrapper slds-p-right--x-large']");
	private By leadsrecentview = By.xpath("//div[@class='uiInput uiAutocomplete uiInput--default uiInput--picklist']");
	//private By Myleadsframe = By.xpath("//div[@class='panel-content scrollable']");

	

	public LeadPage(WebDriver driver) {
		this.driver = driver;
	}

	//Return Leadpage driver;
	
	public void leadpageheader() {
		try {
			boolean flag_LeadHeader;
			flag_LeadHeader = ut.fluientWaitforElementTobePresent(driver, LeadPageHeader);
			if (flag_LeadHeader) {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				headeractual = driver.findElement(LeadPageHeader).getText();
				} else {
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// New lead window
	public void newlead() {
		try {
			boolean flag_newlead;
			Thread.sleep(2000);
			//flag_newlead = ut.fluientWaitforElementTobePresent(driver, NewLead_btn);
			flag_newlead = ut.elementclickable(driver, NewLead_btn, 180);
			if (flag_newlead) {

				driver.findElement(NewLead_btn).click();

				boolean headerchildwindow = ut.switchchildwindow(driver, NewLead_window);
				if (headerchildwindow) {
					ut.WaitForElePresent(driver, Save_btn, 1);
					savebtn();// save was done to check for validation message name,contacttype
				} else {
					Assert.assertTrue(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// validation if name, contact type is not entered
	public void name_contacttype_validation() {
		try {
			boolean flag_name_contact_error;
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			flag_name_contact_error = ut.fluientWaitforElementTobePresent(driver, contact_error);
			if (flag_name_contact_error) {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				WebElement ele = driver
						.findElement(By.xpath("//*[@class='errorsList slds-list_dotted slds-m-left_medium']"));
				List<WebElement> links = ele.findElements(By.tagName("li"));
				for (int i = 0; i < links.size(); i++) {
					actual_name_contacttype.add(links.get(i).getText());
				}
			}
			boolean listmatched = expected_name_contacttype.equals(actual_name_contacttype);
			if (listmatched) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
	}

	// Save btn click
	public void savebtn() {
		try {
			boolean savebtn_flag;
			savebtn_flag = ut.fluientWaitforElementTobePresent(driver, Save_btn);
			if (savebtn_flag) {
				// DataStore.print();
				driver.findElement(Save_btn).click();
				Thread.sleep(2000);

				// driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				// Alert alert = driver.switchTo().alert();
				// alert.dismiss();
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// PhoneNo Creation
	public void PhoneNo() {
		try {
			boolean Phoneno_flag;
			Phoneno_flag = ut.fluientWaitforElementTobePresent(driver, Phoneno);
			if (Phoneno_flag) {
				driver.findElement(Phoneno).click();
				Long phoneno = ut.generateNumber();
				String phoneno_txt = phoneno.toString();
				driver.findElement(Phoneno).sendKeys(phoneno_txt);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Close error popup
	public void closerror() {
		try {
			boolean errorpopup_flag;
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			errorpopup_flag = ut.fluientWaitforElementTobePresent(driver, close_error);
			if (errorpopup_flag) {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(close_error).click();
			} else {
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// function to generate last name
	public void lastname() {
		try {
			String random_lastname = ut.getSaltString();
			String initial_lastname = "vsqatest";
			String lastname_random = random_lastname;
			String random = initial_lastname + lastname_random;
			boolean lastname_flag;
			lastname_flag = ut.fluientWaitforElementTobePresent(driver, lastname);

			if (lastname_flag) {
				DataStore.LeadNamelist.add(random);
				// driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);

				WebElement element = (new WebDriverWait(driver, 25)).until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Last Name']")));
				element.sendKeys(random);
				// driver.findElement(By.xpath("//input[@placeholder='Last
				// Name']")).sendKeys(random);
				// driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Contact Type drop down selection
	public void contacttype_select(String str) throws InterruptedException // function to click the value from drop down
	{
		try {

			boolean cnct_label = driver.findElement(Contacttype_label).isDisplayed();

			if (cnct_label) {
				WebElement ele = driver.findElement(Contacttype_drpdwn);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", ele);

				List<WebElement> list_ele = ele.findElements(By.xpath(
						"//*[text()='Contact Type']/following-sibling::div/lightning-base-combobox/div/div/input/parent::div/parent::div/div[2]/lightning-base-combobox-item/span[2]/span"));
				for (WebElement click_drpdwn : list_ele) {
					String clicked_dropdown = click_drpdwn.getAttribute("title");
					actual_contact_dropdown.add(clicked_dropdown);
				}
				boolean dropdown_matched = actual_contact_dropdown.equals(expected_contact_dropdown);
				if (dropdown_matched) {
					for (WebElement click_drpdwn : list_ele) {
						String clicking_dropdown = click_drpdwn.getAttribute("title");
						if (clicking_dropdown.equalsIgnoreCase(str)) {
							click_drpdwn.click();
							break;
						}
					}
				}
			} else {
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// validation if email phone marketing source is not entered
	public void Email_Phone_MarketingSource_Validation() // Validation of message
	{
		try {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			WebElement ele = driver.findElement(contact_error);
			List<WebElement> links = ele.findElements(By.tagName("li"));
			for (int i = 0; i < links.size(); i++) {
				actual_Email_Phone_MarketingSource.add(links.get(i).getText());
			}
			boolean listmatched = expected_Email_Phone_MarketingSource.equals(actual_Email_Phone_MarketingSource);
			if (listmatched) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Pass valid email id and click from drop down marketing source
	public void MarketingSource_dropdown(String str) throws Exception {
		try {
			boolean errorpopup_flag;

			errorpopup_flag = ut.fluientWaitforElementTobePresent(driver, MarketingSource);
			if (errorpopup_flag) {

				WebElement ele = driver.findElement(MarketingSource);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", ele);

				List<WebElement> element = driver.findElements(By.xpath(
						"//label[text()='What marketing source? (6)']/following-sibling::div/lightning-base-combobox/div/div[2]/lightning-base-combobox-item/span[2]/span"));

				String[] Text = new String[element.size()];
				int i = 0;
				for (WebElement eletoclick : element) {
					Text[i] = eletoclick.getText();
					if (Text[i].equals(str)) {

						JavascriptExecutor je = (JavascriptExecutor) driver;
						je.executeScript("arguments[0].scrollIntoView(true);", eletoclick);
						je.executeScript("arguments[0].click();", eletoclick);
						Thread.sleep(1000);
						break;
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
	}

	// Function to validate and send Emailid,Phoneno
	public void Emailid_Phoneno(String emailid, String phoneno) {
		try {
			boolean emailid_flag;
			boolean phoneno_flag;
			emailid_flag = ut.fluientWaitforElementTobePresent(driver, Emailid);
			phoneno_flag = ut.fluientWaitforElementTobePresent(driver, Phoneno);
			if (emailid_flag && phoneno_flag) {
				driver.findElement(Emailid).sendKeys(emailid);
				driver.findElement(Phoneno).sendKeys(phoneno);
				savebtn();
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				if (driver.findElement(contact_error).isDisplayed()) {
					emailerror_validation(); // call the email error validation function
				}
				// else {
				// savebtn(); // last data for outline and click save
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Email Error
	public void emailerror_validation() {
		try {
			boolean email_flag;
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			email_flag = ut.fluientWaitforElementTobePresent(driver, contact_error);
			if (email_flag) {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				WebElement ele = driver
						.findElement(By.xpath("//*[@class='errorsList slds-list_dotted slds-m-left_medium']"));
				List<WebElement> links = ele.findElements(By.tagName("li"));
				for (int i = 0; i < links.size(); i++) {
					actual_error_email.add(links.get(i).getText());
					System.out.println(actual_error_email);
				}
			}
			boolean listmatched = expected_error_email.equals(actual_error_email);
			if (listmatched) {
				closerror();
			} else {
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Emailid_Contacttype_MarketSource_Validdata(String email, String contacttype, String marketingsource)
			throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

			driver.findElement(Emailid).sendKeys(email);

			// below to select from contact type dropdown
			WebElement ele = driver.findElement(Contacttype_drpdwn);
			ele.click();

			// JavascriptExecutor executor = (JavascriptExecutor) driver;
			// executor.executeScript("arguments[0].click();", ele);

			WebElement element = (new WebDriverWait(driver, 25)).until(ExpectedConditions.presenceOfElementLocated(By
					.xpath("//*[text()='Contact Type']/following-sibling::div/lightning-base-combobox/div/div/input/parent::div/parent::div/div[2]/lightning-base-combobox-item/span[2]/span")));

			List<WebElement> list_ele = ele.findElements(By.xpath(
					"//*[text()='Contact Type']/following-sibling::div/lightning-base-combobox/div/div/input/parent::div/parent::div/div[2]/lightning-base-combobox-item/span[2]/span"));

			for (WebElement click_drpdwn : list_ele) {
				String clicking_dropdown = click_drpdwn.getAttribute("title");
				if (clicking_dropdown.equalsIgnoreCase(contacttype)) {
					click_drpdwn.click();
					break;
				}
			}

			// below to select marketing drop down
			MarketingSource_dropdown(marketingsource);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// function to get lead name and pass to search the lead
	public void searchlead() throws Exception {
		// HomePage hp=new HomePage(driver);
		try {
			boolean element_flag;

			for (int i = 0; i < DataStore.LeadNamelist.size(); i++) {
				System.out.println(DataStore.LeadNamelist.size());

				String Srchdata = DataStore.LeadNamelist.get(i);
				// WebElement element=driver.findElement(By.xpath("//input[@placeholder='Search
				// Leads and more...']"));
				element_flag = ut.fluientWaitforElementTobePresent(driver, HomePage.LeadSearch);

				Thread.sleep(2000);

				// In case to remove thread we can add webdriver wait with expected condition of
				// visibility

				if (element_flag) {
					hp.globalsearch(driver, HomePage.LeadSearch, Srchdata);
					hp.GetAndClickLeadName(driver, Srchdata);
				}

			}
			DataStore.LeadNamelist.clear();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// Lead Tab Click
	public void Leadtabclick() throws Exception {
		try {
			hp.tabclick(driver); // call to click tab
			MyLeads(); // call to click on Leads drop down
			LeadNameClickInTable(); // call to click leadname from table
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// click on MyLeads from dropdown
	public void MyLeads() throws Exception {
		try {
			boolean leadsview, recentlitview;
			String leadname = "My Leads";
			leadsview = ut.fluientWaitforElementTobePresent(driver, leadsView);
			if (leadsview) {

				WebElement leadview = driver.findElement(By.xpath(
						"//*[@class='slds-button slds-button--reset downIcon slds-m-top_xxx-small slds-p-right_xxx-small']"));
				leadview.click(); // click on drop down
				
				Thread.sleep(2500);
				
				ut.fluientWaitforElementTobePresent(driver, leadsrecentview);
				//ut.fluientWaitforElementTobePresent(driver, Myleadsframe);
				//WebElement ele1 = driver.findElement(
				//		By.xpath("//div[@class='uiInput uiAutocomplete uiInput--default uiInput--picklist']/input"));
				WebElement ele1 = driver.findElement(
						By.xpath("//div[@class='panel-content scrollable']/div/div[1]/input"));
				if(ele1.isDisplayed())
				{	
				System.out.println("Clicked to search");
				ele1.click();
				ele1.sendKeys("My Leads");
				Thread.sleep(2000);
				//WebElement and then list
				
				WebElement ele2=driver.findElement(By.xpath("//div[@class='list uiAbstractList forceVirtualAutocompleteMenuList']/div/div/div/ul/li/a/span"));
				
				List<WebElement> ele = driver.findElements(By.tagName("mark"));
									
				for (int i = 0; i < ele.size(); i++) {
					System.out.println(ele.get(i).getText());

					if (ele.get(i).getText().equals("My Leads")) {
						ele.get(i).click();
						// ut.waitForPageLoad(driver);
						break;
					}
				 }
				}	
			} else {
				Assert.assertTrue(false);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// click on LeadName from the Lead Table
	public void LeadNameClickInTable() throws Exception {
		try {
			// boolean myleadtables;
			String leadname = "Harry Poter";
			hp.table(driver, leadname);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	


}
