package test;

import org.testng.annotations.Test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.forms.Form;
import form.IFramePage;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;

public class EnterTextInFrame {

	private Browser browser;
	private Form framePage;

	@BeforeClass
	public void beforeClass() {
		browser = AqualityServices.getBrowser();
		browser.maximize();
		framePage = new IFramePage();
	}

	@Test
	public void test() {
		Logger.getInstance().info("Try to open the IFrame page");
		browser.goTo("http://the-internet.herokuapp.com/iframe");
		assertTrue(framePage.state().waitForDisplayed(), "Failed to open the page");
		Logger.getInstance().info("Try to clear and type frame");
		browser.getDriver().switchTo().frame("mce_0_ifr");
		IFramePage iframePage = ((IFramePage) framePage);
		iframePage.clearAndTypeFrame();
		assertTrue(iframePage.isFrameTextVisible(), "Text isn't visible");
		Logger.getInstance().info("Try to select text and click on a bold button");
		iframePage.selectFrameText();
		browser.getDriver().switchTo().defaultContent();
		iframePage.clickFrameBoldButton();
		browser.getDriver().switchTo().frame("mce_0_ifr");
		assertTrue(iframePage.isFrameTextBold(), "Text isn't bold");
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}

}
