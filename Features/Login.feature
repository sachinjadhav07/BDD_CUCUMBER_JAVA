Feature: Login

Scenario: Login using valid credentials
	Given user Launch Browser
	When user opens URL "https://admin-demo.nopcommerce.com/admin/"
	And user enters email as "admin@yourstore.com" and password as "admin"
	And user click on Login
	And page title should be "Dashboard / nopCommerce administration"
	And user click on logout
	And page title should be "Your store. Login"
	And close the Broswer
	
	
  
  Scenario Outline: Login with different credentials
    Given user Launch Browser
     When user opens URL "https://admin-demo.nopcommerce.com/admin/"
      And user enters email as "<email>" and password as "<password>"
      And user click on Login
      And page title should be "Dashboard / nopCommerce administration"
      And user click on logout
      And page title should be "Your store. Login"
      And close the Broswer
  
    Examples: 
      | email               | password | 
      | admin@yourstore.com | admin    | 
      | admin@yourstore.com | admin    | 