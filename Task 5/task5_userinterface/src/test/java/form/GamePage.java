package form;

import java.time.Duration;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class GamePage extends Form {

	private static final By GAME_TIMER_LOCATOR = By.cssSelector("div[class='timer timer--white timer--center']");
	private static final By HELP_TITLE_LOCATOR = By.cssSelector("div[class='help-form__title']");
	private static final By SEND_TO_BOTTOM_BUTTON_LOCATION = By
			.cssSelector("button[class='button button--solid button--blue help-form__send-to-bottom-button']");
	private static final By ACCEPT_COOKIES_BOX_LOCATOR = By.cssSelector("div[class='cookies']");
	private static final By ACCEPT_COOKIES_BUTTON_LOCATOR = By
			.cssSelector("button[class='button button--solid button--transparent']");

	private final LoginForm loginForm = new LoginForm();
	private final AvatarAndInterestsForm avatarAndInterestsForm = new AvatarAndInterestsForm();
	private final PersonalDetailsForm detailsForm = new PersonalDetailsForm();

	private final IButton sendToBottomButton = super.getElementFactory().getButton(SEND_TO_BOTTOM_BUTTON_LOCATION,
			"Send help section to bottom button");
	private final ITextBox helpSection = super.getElementFactory().getTextBox(HELP_TITLE_LOCATOR, "Help section title");
	private final ITextBox acceptCookiesBox = super.getElementFactory().getTextBox(ACCEPT_COOKIES_BOX_LOCATOR,
			"Accept cookies box");
	private final IButton acceptCookiesButton = super.getElementFactory().getButton(ACCEPT_COOKIES_BUTTON_LOCATOR,
			"Accept cookies button");
	private final ITextBox timerBox = super.getElementFactory().getTextBox(GAME_TIMER_LOCATOR, "Game timer box");

	public GamePage() {
		super(GAME_TIMER_LOCATOR, "Game page");
	}

	public boolean isLoginFormDisplayed() {
		return loginForm.state().waitForDisplayed();
	}

	public boolean isAvatarAndInterestsFormDisplayed() {
		return avatarAndInterestsForm.state().waitForDisplayed();
	}

	public boolean isPersonalDetailsFormDisplayed() {
		return detailsForm.state().waitForDisplayed();
	}

	public boolean isHelpSectionHidden() {
		return helpSection.state().waitForNotDisplayed(Duration.ofSeconds(10));
	}

	public boolean isAcceptCookiesBoxNotDisplayed() {
		return acceptCookiesBox.state().waitForNotDisplayed();
	}
	
	public String getTimerTime() {
		return timerBox.getText();
	}

	public void setPassword(String password) {
		loginForm.setPassword(password);
	}

	public void setEmail(String email) {
		loginForm.setEmail(email);
	}

	public void setDomain(String domain) {
		loginForm.setDomain(domain);
	}

	public void setGenericTLDs(String tlds) {
		loginForm.setGenericTLDs(tlds);
	}

	public void acceptTermsConditions() {
		loginForm.acceptTermsConditions();
	}

	public void clickNextButton() {
		loginForm.clickNextButton();
	}

	public void clickDownloadImageButton() {
		avatarAndInterestsForm.clickDownloadImageButton();
	}

	public void clickUploadImageLink() {
		avatarAndInterestsForm.clickUploadImageLink();
	}

	public void clickAvatarNextButton() {
		avatarAndInterestsForm.clickNextButton();
	}

	public void clickSendToBottomButton() {
		sendToBottomButton.click();
	}

	public void clickAcceptCookiesButton() {
		acceptCookiesButton.click();
	}

	public void chooseRandomInterests(int amount) {
		avatarAndInterestsForm.chooseRandomInterests(amount);
	}

	public void uploadImage(String fileLocation) {
		avatarAndInterestsForm.uploadImage(fileLocation);
	}

	public void waitForAcceptCookiesBoxDisplayed() {
		acceptCookiesBox.state().waitForDisplayed(Duration.ofSeconds(10));
	}

}
