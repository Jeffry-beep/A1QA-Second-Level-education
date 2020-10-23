package test;

import org.testng.annotations.BeforeClass;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;

import org.testng.annotations.AfterClass;

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
