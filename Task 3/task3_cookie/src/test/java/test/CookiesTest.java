package test;

import static org.testng.Assert.assertTrue;

import org.apache.commons.configuration2.Configuration;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.ExamplePage;
import framework.loader.TestConfigurationLoader;
import step.AddCookiesStep;

public class CookiesTest extends BaseTest {

	private static final Configuration TEST_CONFIGURATION = TestConfigurationLoader.getTestConfiguration();
	private static final String URL_KEY = "url";
	private static final String NAME_DELETING_COOKIE = "example_key_1";
	private static final Cookie DELETED_COOKIE = new Cookie("example_key_1", "example_value_1");
	private static final String NAME_UPDATING_COOKIE = "example_key_3";
	private static final String NEW_COOKIE_VALUE = "example_value_300";

	@Test
	public void test() {
		Logger.getInstance().info("Try to open the page");
		browser.goTo(TEST_CONFIGURATION.getString(URL_KEY));
		ExamplePage examplePage = new ExamplePage();
		assertTrue(examplePage.state().isDisplayed(), "Failed open the paige");

		Logger.getInstance().info("Try to add cookies");
		AddCookiesStep addCookiesStep = new AddCookiesStep();
		addCookiesStep.addCookies(browser);
		assertTrue(addCookiesStep.isAllCookiesAdd(browser), "Failed add cookies");

		Logger.getInstance().info("Try to delete cookie");
		browser.getDriver().manage().deleteCookieNamed(NAME_DELETING_COOKIE);
		assertTrue(!browser.getDriver().manage().getCookies().contains(DELETED_COOKIE), "Failed delete cookie");

		Logger.getInstance().info("Try to update cookie");
		browser.getDriver().manage().deleteCookieNamed(NAME_UPDATING_COOKIE);
		browser.getDriver().manage().addCookie(new Cookie(NAME_UPDATING_COOKIE, NEW_COOKIE_VALUE));
		assertTrue(
				browser.getDriver().manage().getCookieNamed(NAME_UPDATING_COOKIE).getValue().equals(NEW_COOKIE_VALUE),
				"Failed update cookie");

		Logger.getInstance().info("Try to delete all cookies");
		browser.getDriver().manage().deleteAllCookies();
		assertTrue(browser.getDriver().manage().getCookies().isEmpty(), "Failed delete all cookies");
	}

}
