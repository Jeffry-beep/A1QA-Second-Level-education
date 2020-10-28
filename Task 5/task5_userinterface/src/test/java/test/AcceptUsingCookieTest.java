package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.GamePage;

public class AcceptUsingCookieTest extends BaseTest {

	@Test
	public void test() {
		GamePage gamePage = new GamePage();
		assertTrue(gamePage.state().waitForDisplayed(), "Failed click on the game start link");
		assertTrue(gamePage.isLoginFormDisplayed(), "Failed click on the game start link");

		Logger.getInstance().info("Try to accept cookies");
		gamePage.waitForAcceptCookiesBoxDisplayed();
		gamePage.clickAcceptCookiesButton();
		assertTrue(gamePage.isAcceptCookiesBoxNotDisplayed(), "Failed accept cookies");
	}

}
