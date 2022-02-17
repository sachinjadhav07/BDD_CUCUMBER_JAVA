package stepdefinitions;

import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageobjects.LoginPage;
import utilities.TestBase;

public class LoginPageStep extends TestBase{
	public LoginPage lp;
	
	
	@Given("user Launch Browser")
	public void user_launch_browser() throws Exception {
		launchBrowser();
		lp = new LoginPage();
	}

	@When("user opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
	}

	@When("user enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		lp.enterUsername(email);
		lp.enterPassword(password);
	}

	@When("user click on Login")
	public void user_click_on_login() throws Exception {
		lp.clickLogin();
		Thread.sleep(30000);
	}

	@When("page title should be {string}")
	public void page_title_should_be(String title) throws Exception {
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue(false);
		}else {
			Assert.assertEquals(title, driver.getTitle());
		}
		Thread.sleep(10000);
	}

	@When("user click on logout")
	public void user_click_on_logout() throws Exception {
	         
		lp.clickLogout();
		Thread.sleep(10000);
	}
	
	@When("close the Broswer")
	public void close_the_broswer() {
	    closeBrowser();
	}
}
