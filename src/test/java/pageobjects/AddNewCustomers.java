package pageobjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.TestBase;

public class AddNewCustomers extends TestBase {

	public AddNewCustomers() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ("(//p[normalize-space(text())='Customers'])[1]"))
	@CacheLookup
	WebElement custormersMenu;

	@FindBy(xpath = ("(//p[normalize-space(text())='Customers'])[2]"))
	@CacheLookup
	WebElement custormerTab;

	@FindBy(xpath = ("//a[@href='/Admin/Customer/Create']"))
	@CacheLookup
	WebElement addNewCustomerBtn;

	@FindBy(id = ("Email"))
	@CacheLookup
	WebElement emailTxtbox;

	@FindBy(id = ("Password"))
	@CacheLookup
	WebElement passwordTxtbox;

	@FindBy(id = ("FirstName"))
	@CacheLookup
	WebElement firstNameTxtbox;

	@FindBy(id = ("LastName"))
	@CacheLookup
	WebElement lastNameTxtbox;

	@FindBy(id = ("Gender_Male"))
	@CacheLookup
	WebElement genderMaleRadioBtn;

	@FindBy(id = ("Gender_Female"))
	@CacheLookup
	WebElement genderFemaleRadioBtn;

	@FindBy(id = ("DateOfBirth"))
	@CacheLookup
	WebElement dobTxtBox;

	@FindBy(xpath = ("(//*[@role='listbox']//input)[2]"))
	@CacheLookup
	WebElement custRoleTxtBox;

	@FindBy(id = ("SelectedCustomerRoleIds"))
	@CacheLookup
	WebElement custRoleList;

	@FindBy(id = ("AdminComment"))
	@CacheLookup
	WebElement adminTextArea;

	@FindBy(xpath = ("//*[@name='save']"))
	@CacheLookup
	WebElement saveBtn;

	@FindBy(xpath = ("//*[@class='alert alert-success alert-dismissable']//button"))
	@CacheLookup
	WebElement confirmMsg;

	public void clickOnCustomerMenu() {
		custormersMenu.click();
	}

	public void clickOnCustomer() {
		custormerTab.click();
	}

	public void clickOnCustomerRole() {
		action = new Actions(driver);
		action.moveToElement(custRoleTxtBox).build().perform();
		custRoleTxtBox.clear();
		custRoleTxtBox.click();
	}

	public void clickOnAddNewCustBtn() {
		addNewCustomerBtn.click();
	}

	public void clickOnSaveBtn() {
		action = new Actions(driver);
		action.moveToElement(saveBtn).build().perform();
		saveBtn.click();
	}

	public void verifyConfirmMsg() {
		String actualMsg = confirmMsg.getText();
		Assert.assertEquals(actualMsg, " The new customer has been added successfully. ");
	}

	public void enterMailId(String text) {
		emailTxtbox.click();
		emailTxtbox.clear();
		emailTxtbox.sendKeys(text);
	}

	public void enterPassword(String text) {
		passwordTxtbox.click();
		passwordTxtbox.clear();
		passwordTxtbox.sendKeys(text);
	}

	public void enterDOB(String text) {
		dobTxtBox.click();
		dobTxtBox.clear();
		dobTxtBox.sendKeys(text);
	}

	public void enterFirstName(String text) {
		firstNameTxtbox.click();
		firstNameTxtbox.clear();
		firstNameTxtbox.sendKeys(text);
	}

	public void enterLastName(String text) {
		lastNameTxtbox.click();
		lastNameTxtbox.clear();
		lastNameTxtbox.sendKeys(text);
	}

	public void enterAdmintext(String text) {
		adminTextArea.click();
		adminTextArea.clear();
		adminTextArea.sendKeys(text);
	}

	public void clickMaleRadioBtn() {
		genderMaleRadioBtn.click();
	}

	public void clickFemaleRadioBtn() {
		genderFemaleRadioBtn.click();
	}

	public void selectListOption(String visibleText) {
		
		Select objSelect = new Select(custRoleList);
		List<WebElement> elementCount = objSelect.getOptions();
		System.out.println(elementCount.size());
		for (WebElement list : elementCount) {
			if (list.getText().contains(visibleText)) {
				list.click();
			}

		}
	}

}
