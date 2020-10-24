package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;

public abstract class BaseTest {

	protected Browser browser;

	@BeforeClass
	public void beforeClass() {
		browser = AqualityServices.getBrowser();
		browser.maximize();
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}

}
