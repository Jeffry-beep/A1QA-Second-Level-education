package test;

import org.testng.annotations.BeforeClass;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import form.StartPage;
import framework.loader.PropertiesConfigurationLoader;

import static org.testng.Assert.assertTrue;

import org.apache.commons.configuration2.Configuration;
import org.testng.annotations.AfterClass;

public abstract class BaseTest {

	private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";
	private static final String URL_KEY = "url";

	protected Browser browser;
	protected Configuration suiteConfiguration;

	@BeforeClass
	public void beforeClass() {
		suiteConfiguration = PropertiesConfigurationLoader.getConfiguration(CONFIG_FILE_PATH);
		browser = AqualityServices.getBrowser();
		browser.maximize();
		Logger.getInstance().info("Try to open the IFrame page");
		browser.goTo(suiteConfiguration.getString(URL_KEY));
		StartPage startPage = new StartPage();
		assertTrue(startPage.state().waitForDisplayed(), "Failed open the page");

		Logger.getInstance().info("Try click on the game start link");
		startPage.clickStartLink();
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}
}
