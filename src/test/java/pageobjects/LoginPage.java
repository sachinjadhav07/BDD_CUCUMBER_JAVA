package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.TestBase;

public class LoginPage extends TestBase{
//	public WebDriver adriver;
//
//	public LoginPage(WebDriver driver) {
//		super();
//		adriver = driver;
//		PageFactory.initElements(driver, this);
//	}
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "Email")
	@CacheLookup
	WebElement email;

	@FindBy(id = "Password")
	@CacheLookup
	WebElement password;

	@FindBy(xpath = "//button[@class='button-1 login-button']")
	@CacheLookup
	WebElement loginBtn;

	@FindBy(xpath = "//a[@href='/logout']")
	@CacheLookup
	WebElement logoutBtn;

	public void enterUsername(String userName) {
		email.clear();
		email.sendKeys(userName);
	}

	public void enterPassword(String pwd) {
		password.clear();
		password.sendKeys(pwd);
	}

	public void clickLogin() {
		loginBtn.click();
	}

	public void clickLogout() {
		waitForElement(logoutBtn, 30);
		Actions build = new Actions(driver);
		build.moveToElement(logoutBtn).build().perform();
		logoutBtn.click();
	}

}
