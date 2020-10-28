package form;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;

public class AvatarAndInterestsForm extends Form {

	public static final By INTEREST_LOCATOR = By.cssSelector("span[class='checkbox__box']");
	public static final By DOWNLOAD_IMAGE_BUTTON_LOCATOR = By.cssSelector(
			"button[class='align__cell avatar-and-interests__avatar-upload-button button button--solid button--blue']");
	public static final By UPLOAD_IMAGE_LINK_LOCATOR = By.cssSelector("a[class='avatar-and-interests__upload-button']");
	public static final By NEXT_BUTTON_LOCATOR = By
			.cssSelector("button[class='button button--stroked button--white button--fluid']");
	public static final By UPLOADED_IMAGE_LOCATOR = By.cssSelector("div[class='avatar-and-interests__avatar-image']");

	private List<ICheckBox> interestCheckBoxes;
	private IButton downloadImageButton;
	private ILink uploadImageLink;
	private IButton nextButton;

	public AvatarAndInterestsForm() {
		super(By.cssSelector("div[class='avatar-and-interests-page']"), "Avatar and interests form");
	}

	public void initializeElements() {
		interestCheckBoxes = super.getElementFactory().findElements(INTEREST_LOCATOR, ElementType.CHECKBOX);
		downloadImageButton = super.getElementFactory().getButton(DOWNLOAD_IMAGE_BUTTON_LOCATOR,
				"Download image button");
		uploadImageLink = super.getElementFactory().getLink(UPLOAD_IMAGE_LINK_LOCATOR, "Upload image link");
		nextButton = super.getElementFactory().getButton(NEXT_BUTTON_LOCATOR, "Next button");
	}

	public void chooseRandomInterests(int amount) {
		int[] interestIndexes = new Random().ints(amount, 0, interestCheckBoxes.size() - 1).toArray();
		interestCheckBoxes.get(interestCheckBoxes.size() - 1).check();
		for (int i = 0; i < amount; i++) {
			interestCheckBoxes.get(interestIndexes[i]).check();
		}
	}

	public void clickDownloadImageButton() {
		downloadImageButton.click();
	}

	public void clickUploadImageLink() {
		uploadImageLink.click();
	}

	public void clickNextButton() {
		nextButton.click();
	}

	public void uploadImage(String fileLocation) {
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
			waitAvatarImageUpload();
		} catch (AWTException e) {
			Logger.getInstance().warn("Problems with windows toolkit");
		}
	}

	private void waitAvatarImageUpload() {
		new WebDriverWait(AqualityServices.getBrowser().getDriver(), 15)
				.until(ExpectedConditions.visibilityOfElementLocated(UPLOADED_IMAGE_LOCATOR));
	}

}
