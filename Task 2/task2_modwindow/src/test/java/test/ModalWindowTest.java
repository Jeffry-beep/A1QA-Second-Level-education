package test;

import org.testng.annotations.Test;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.forms.Form;
import form.JavascriptAlertPage;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;

public class ModalWindowTest {

	private Browser browser;
	private Form javascriptAlertPage;

	@BeforeClass
	public void beforeClass() {
		browser = AqualityServices.getBrowser();
		browser.maximize();
		javascriptAlertPage = new JavascriptAlertPage();
	}

	@Test
	public void test() {
		Logger.getInstance().info("Try to open the paige");
		browser.goTo("http://the-internet.herokuapp.com/javascript_alerts");
		assertTrue(javascriptAlertPage.state().waitForDisplayed(), "Failed open the paige");
		Logger.getInstance().info("Try to click on the JS Alert button");
		JavascriptAlertPage alertPage = ((JavascriptAlertPage) javascriptAlertPage);
		alertPage.clickJSAlertButton();
		assertTrue(browser.getDriver().switchTo().alert().getText().equals("I am a JS Alert"),
				"Failed click on the JS Alert button");
		Logger.getInstance().info("Try to click on OK button in the alert");
		browser.handleAlert(AlertActions.ACCEPT);
		assertTrue(alertPage.checkResult("You successfuly clicked an alert"), "Failed click on OK button");
		Logger.getInstance().info("Try to click on JS Confirm button");
		alertPage.clickJSConfirmButton();
		assertTrue(browser.getDriver().switchTo().alert().getText().equals("I am a JS Confirm"),
				"Failed click on the JS Confirm button");
		Logger.getInstance().info("Try to click on OK button in the alert");
		browser.handleAlert(AlertActions.ACCEPT);
		assertTrue(alertPage.checkResult("You clicked: Ok"), "Failed click on OK button");
		Logger.getInstance().info("Try to click on JS PRompt button");
		alertPage.clickJSPromptButton();
		assertTrue(browser.getDriver().switchTo().alert().getText().equals("I am a JS prompt"),
				"Failed click on the JS Prompt button");
		Logger.getInstance().info("Try to enter text and then click on OK button");
		browser.handlePromptAlert(AlertActions.ACCEPT, "Old friends");
		assertTrue(alertPage.checkResult("You entered: Old friends"));
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}

}
