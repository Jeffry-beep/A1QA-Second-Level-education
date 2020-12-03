
package test;

import org.testng.annotations.BeforeClass;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import framework.loader.PropertiesConfigurationLoader;

import org.apache.commons.configuration2.Configuration;

import org.testng.annotations.AfterClass;

public abstract class BaseTest {

	private static final String URL_KEY = "url";

	public static final Configuration SUITE_CONFIGURATION = PropertiesConfigurationLoader
			.getConfiguration("src/test/resources/config.properties");
	public static final Browser BROWSER = AqualityServices.getBrowser();

	@BeforeClass
	public void beforeClass() {
		BROWSER.maximize();
		Logger.getInstance().info("Opening a site");
		BROWSER.goTo(SUITE_CONFIGURATION.getString(URL_KEY));
	}

	@AfterClass
	public void afterClass() {
		BROWSER.quit();
	}
}