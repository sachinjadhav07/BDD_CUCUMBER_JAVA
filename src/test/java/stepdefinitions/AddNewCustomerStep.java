package stepdefinitions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.github.javafaker.Faker;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AddNewCustomers;
import utilities.TestBase;

public class AddNewCustomerStep extends TestBase {
	public AddNewCustomers addCust;
	public Faker faker;
	
	@When("user click on add customers menu item")
	public void user_click_on_add_customers_menu_item() throws Exception {
		Thread.sleep(30000);
		addCust = new AddNewCustomers();
		addCust.clickOnCustomerMenu();
		Thread.sleep(30000);
	}

	@When("user click on add new button")
	public void user_click_on_add_new_button() throws Exception {
		addCust.clickOnCustomer();
		Thread.sleep(10000);
	}

	@Then("user is view add new customer page")
	public void user_is_view_add_new_customer_page() throws Exception {
		addCust.clickOnAddNewCustBtn();
		Thread.sleep(30000);
	}

	@When("user enters customers details")
	public void user_enters_customers_details() {
		faker =  new Faker();
		String email = faker.internet().emailAddress();
		String password = faker.internet().password();
		String firstName = faker.address().firstName();
		String lastName = faker.address().lastName();
		String optionText = "Guest";
		String adminText = faker.address().fullAddress().toString();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy"); 
		   LocalDateTime now = LocalDateTime.now();
		addCust.enterMailId(email);
		addCust.enterPassword(password);
		addCust.enterFirstName(firstName);
		addCust.enterLastName(lastName);
		addCust.clickMaleRadioBtn();
		addCust.enterDOB(dtf.format(now));
		addCust.clickOnCustomerRole();
		addCust.selectListOption(optionText);
		addCust.enterAdmintext(adminText);
		
		
	}

	@When("click on save button")
	public void click_on_save_button() throws Exception {
		addCust.clickOnSaveBtn();
		Thread.sleep(30000);
	}

	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully."));
	}
}
