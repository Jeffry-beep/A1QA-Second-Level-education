package test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.forms.Form;
import form.ExamplePage;
import step.AddCookiesStep;

public class CookiesTest {

	private Browser browser;
	private Form examplePage;
	private AddCookiesStep addCookiesStep;

	@BeforeClass
	public void beforeClass() {
		browser = AqualityServices.getBrowser();
		browser.maximize();
		examplePage = new ExamplePage();
		addCookiesStep = new AddCookiesStep();
	}

	@Test
	public void test() {
		Logger.getInstance().info("Try to open the page");
		browser.goTo("http://example.com/");
		assertTrue(examplePage.state().isDisplayed(), "Failed open the paige");
		Logger.getInstance().info("Try to add cookies");
		addCookiesStep.addCookies(browser);
		assertTrue(addCookiesStep.isAllCookiesAdd(browser), "Failed add cookies");
		Logger.getInstance().info("Try to delete cookie");
		browser.getDriver().manage().deleteCookieNamed("example_key_1");
		assertTrue(!browser.getDriver().manage().getCookies().contains(new Cookie("example_key_1", "example_value_1")),
				"Failed delete cookie");
		Logger.getInstance().info("Try to delete cookie");
		browser.getDriver().manage().deleteCookieNamed("example_key_3");
		browser.getDriver().manage().addCookie(new Cookie("example_key_3", "example_value_300"));
		assertTrue(browser.getDriver().manage().getCookieNamed("example_key_3").getValue().equals("example_value_300"),
				"Failed update cookie");
		Logger.getInstance().info("Try to delete all cookies");
		browser.getDriver().manage().deleteAllCookies();
		assertTrue(browser.getDriver().manage().getCookies().isEmpty(), "Failed delete all cookies");
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}
}
