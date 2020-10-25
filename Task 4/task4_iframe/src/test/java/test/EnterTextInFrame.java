package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.InputFramePage;

public class EnterTextInFrame extends BaseTest {

	private static final String EXPECTED_INPUT_FRAME_TITLE = "An iFrame containing the TinyMCE WYSIWYG Editor";

	@Test
	public void test() {
		InputFramePage inputFramePage = new InputFramePage();
		assertTrue(inputFramePage.state().waitForDisplayed(), "Failed to open the page");
		assertEquals(inputFramePage.getInputFrameTitle(), EXPECTED_INPUT_FRAME_TITLE, "Failed to open the page");
		Logger.getInstance().info("Try to clear and type frame");
		inputFramePage.clearAndTypeFrame(browser);
		assertTrue(inputFramePage.isFrameTextVisible(), "Text isn't visible");
		Logger.getInstance().info("Try to select text and click on a bold button");
		inputFramePage.selectFrameText();
		inputFramePage.clickFrameBoldButton(browser);
		assertTrue(inputFramePage.isFrameTextBold(browser), "Text isn't bold");
	}

}
