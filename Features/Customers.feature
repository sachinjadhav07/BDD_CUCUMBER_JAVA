Feature: add new customer feature
 Scenario: Adding new customer 
 	Given user Launch Browser
	When user opens URL "https://admin-demo.nopcommerce.com/admin/"
	And user enters email as "admin@yourstore.com" and password as "admin"
	And user click on Login
 	When user click on add customers menu item
 	And user click on add new button
 	Then user is view add new customer page
 	When user enters customers details
 	And click on save button
 	Then user can view confirmation message "The new customer has been added successfully."
 	And close the Broswer