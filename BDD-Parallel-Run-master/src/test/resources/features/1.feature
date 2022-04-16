@Smoke1
Feature: Login

#@Login
Scenario: Login and Logout 
Given User is on Login Page 
When User gets the Title of the Page
Then page Title should be "Login | Salesforce"
When User enters valid UserName ""
When User enters valid Password ""
And User clicks on Login btn
Then user navigates to home page and title is "Home | Salesforce"
And User clicks on profile
And User clicks to logout