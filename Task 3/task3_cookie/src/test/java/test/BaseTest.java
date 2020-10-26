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

	protected Browser browser;

	@BeforeClass
	public void beforeClass() {
		browser = AqualityServices.getBrowser();
		browser.maximize();
		Logger.getInstance().info("Try to open the page");
		browser.goTo(TEST_CONFIGURATION.getString(URL_KEY));
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}

}
