/*import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

@accounts
Scenario: Accounts section count
Given user is on Accounts page
Then user gets accounts section
|ORDER HISTORY AND DETAILS|
|MY CREDIT SLIPS|
|MY ADDRESSES|
|MY PERSONAL INFORMATION|
|MY WISHLISTS|
|Home|
And accounts section count should be 6

***************This is the step section********************
@Then("user gets accounts section")
public void user_gets_accounts_section(DataTable sectionsTable) {
	List<String> expAccountSectionsList = sectionsTable.asList();
	System.out.println("Expected accounts section list: " + expAccountSectionsList);
	List<String> actualAccountSectionsList = accountsPage.getAccountsSectionsList();
	System.out.println("Actual accounts section list: " + actualAccountSectionsList);
	Assert.assertTrue(expAccountSectionsList.containsAll(actualAccountSectionsList));
}
@Then("accounts section count should be {int}")
public void accounts_section_count_should_be(Integer expectedSectionCount) {
	Assert.assertTrue(accountsPage.getAccountsSectionCount() == expectedSectionCount);
}
*****************This is the page section***********
public String getAccountsPageTitle() {
	return driver.getTitle();
}

public int getAccountsSectionCount() {
	return driver.findElements(accountSections).size();
}

public List<String> getAccountsSectionsList() {
	List<String> accountsList = new ArrayList<>();
	List<WebElement> accountsHeaderList = driver.findElements(accountSections);
	for (WebElement e : accountsHeaderList) {
		String text = e.getText();
		System.out.println(text);
		accountsList.add(text);
	}

	return accountsList;

}
***************/