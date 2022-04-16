@Home
Feature: HomePage Login and Logout

#  Background: 
#    Given User has already logined in
#      | username                             | password   |
#      |  |  |

#@Home
 Scenario: Home Page Login and Logout
	Given user logins in
	Then user navigates to home page and title is "Home | Salesforce"
    Then User clicks on profile button
    And User clicks to logout
