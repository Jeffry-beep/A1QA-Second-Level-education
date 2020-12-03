package form;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import framework.utils.RobotUtils;

public class AvatarAndInterestsForm extends Form {

	public static final By INTEREST_LOCATOR = By.cssSelector("span[class='checkbox__box']");
	public static final By DOWNLOAD_IMAGE_BUTTON_LOCATOR = By.cssSelector(
			"button[class='align__cell avatar-and-interests__avatar-upload-button button button--solid button--blue']");
	public static final By UPLOAD_IMAGE_LINK_LOCATOR = By.cssSelector("a[class='avatar-and-interests__upload-button']");
	public static final By NEXT_BUTTON_LOCATOR = By
			.cssSelector("button[class='button button--stroked button--white button--fluid']");

	private IButton downloadImageButton = super.getElementFactory().getButton(DOWNLOAD_IMAGE_BUTTON_LOCATOR,
			"Download image button");
	private ILink uploadImageLink = super.getElementFactory().getLink(UPLOAD_IMAGE_LINK_LOCATOR, "Upload image link");
	private IButton nextButton = super.getElementFactory().getButton(NEXT_BUTTON_LOCATOR, "Next button");

	public AvatarAndInterestsForm() {
		super(By.cssSelector("div[class='avatar-and-interests-page']"), "Avatar and interests form");
	}

	public void chooseRandomInterests(int amount) {
		List<ICheckBox> interestCheckBoxes = super.getElementFactory().findElements(INTEREST_LOCATOR,
				ElementType.CHECKBOX);
		int[] interestIndexes = new Random().ints(amount, 0, interestCheckBoxes.size() - 1).toArray();
		interestCheckBoxes.get(interestCheckBoxes.size() - 1).click();
		for (int i = 0; i < amount; i++) {
			interestCheckBoxes.get(interestIndexes[i]).check();
		}
	}

	public void uploadImage(String fileLocation) {
		RobotUtils.uploadImage(fileLocation);
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

}
