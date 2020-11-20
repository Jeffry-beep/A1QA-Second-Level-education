
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

	protected Configuration suiteConfiguration;
	protected Browser browser;

	@BeforeClass
	public void beforeClass() {
		suiteConfiguration = PropertiesConfigurationLoader.getConfiguration("src/test/resources/config.properties");
		browser = AqualityServices.getBrowser();
		browser.maximize();
		Logger.getInstance().info("Opening a site");
		browser.goTo(suiteConfiguration.getString(URL_KEY));
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}
}