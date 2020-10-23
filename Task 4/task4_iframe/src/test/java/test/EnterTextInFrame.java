package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.InputFramePage;
import framework.loader.TestConfigurationLoader;

public class EnterTextInFrame extends BaseTest {

	@Test
	public void test() {
		Logger.getInstance().info("Try to open the IFrame page");
		browser.goTo(TestConfigurationLoader.getTestConfiguration().getString("url"));
		InputFramePage inputFramePage = new InputFramePage();
		assertTrue(inputFramePage.state().waitForDisplayed() && inputFramePage.isInputFrameTitleCorrect(),
				"Failed to open the page");
		Logger.getInstance().info("Try to clear and type frame");
		inputFramePage.clearAndTypeFrame(browser);
		assertTrue(inputFramePage.isFrameTextVisible(), "Text isn't visible");
		Logger.getInstance().info("Try to select text and click on a bold button");
		inputFramePage.selectFrameText();
		inputFramePage.clickFrameBoldButton(browser);
		assertTrue(inputFramePage.isFrameTextBold(browser), "Text isn't bold");
	}

}
