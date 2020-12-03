package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.GamePage;

public class HideHelpSectionTest extends BaseTest {

	@Test
	public void test() {
		GamePage gamePage = new GamePage();
		assertTrue(gamePage.state().waitForDisplayed(), "Failed click on the game start link");
		assertTrue(gamePage.isLoginFormDisplayed(), "Failed click on the game start link");

		Logger.getInstance().info("Try to hide a help section");
		gamePage.clickSendToBottomButton();
		assertTrue(gamePage.isHelpSectionHidden(), "Failed hide the help section");
	}

}
