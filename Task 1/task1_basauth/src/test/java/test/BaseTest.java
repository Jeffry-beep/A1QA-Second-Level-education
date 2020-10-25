package test;

import org.apache.commons.configuration2.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import framework.loader.PropertiesConfigurationLoader;

public abstract class BaseTest {

	private static final Configuration TEST_CONFIGURATION = PropertiesConfigurationLoader
			.getConfiguration("src/test/resources/config.properties");
	private static final String URL_KEY = "url";
	private static final String USERNAME_KEY = "username";
	private static final String PASSWORD_KEY = "password";

	protected Browser browser;

	@BeforeClass
	public void beforeClass() {
		browser = AqualityServices.getBrowser();
		browser.maximize();
		Logger.getInstance().info("Try to open the Basic Authorization page and log in");
		browser.goTo(String.format(TEST_CONFIGURATION.getString(URL_KEY), TEST_CONFIGURATION.getString(USERNAME_KEY),
				TEST_CONFIGURATION.getString(PASSWORD_KEY)));
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}

}
