package test;

import org.testng.annotations.BeforeClass;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import framework.loader.PropertiesConfigurationLoader;

import org.testng.annotations.AfterClass;

public abstract class BaseTest {

	private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";
	private static final String URL_KEY = "url";
	protected Browser browser;

	@BeforeClass
	public void beforeClass() {
		browser = AqualityServices.getBrowser();
		browser.maximize();
		Logger.getInstance().info("Try to open the IFrame page");
		browser.goTo(PropertiesConfigurationLoader.getConfiguration(CONFIG_FILE_PATH).getString(URL_KEY));
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}
}
