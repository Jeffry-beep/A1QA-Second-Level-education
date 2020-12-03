package framework.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;

public class RobotUtils {

	public static final By UPLOADED_IMAGE_LOCATOR = By.cssSelector("div[class='avatar-and-interests__avatar-image']");

	private RobotUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static void uploadImage(String fileLocation) {
		StringSelection strSelection = new StringSelection(fileLocation);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(strSelection, null);
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			AqualityServices.getConditionalWait().waitFor(
					ExpectedConditions.visibilityOfElementLocated(UPLOADED_IMAGE_LOCATOR), Duration.ofSeconds(15));
		} catch (AWTException e) {
			Logger.getInstance().warn("Problems with windows toolkit");
		}
	}

}
