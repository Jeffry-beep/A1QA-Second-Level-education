package form;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class InputFrame extends Form {

	private static final String INPUT_FRAME_LOCATOR = "mce_0_ifr";
	private static final int LENGTH_RANDOM_STRING = 12;
	private static final String KEY_A = "a";
	private static final String ENTERED_TEXT_BOX_LOCATOR = ".//p/strong";
	private static final String EXPECTED_FONT_WEIGHT = "700";

	private final ITextBox textBox = super.getElementFactory().getTextBox(By.id("tinymce"), "Text area");
	private final IButton boldButton = super.getElementFactory().getButton(By.id("mceu_3"), "Bold button");

	public InputFrame() {
		super(By.id(INPUT_FRAME_LOCATOR), "Heroku IFrame page frame");
	}

	public void switchToFrame(Browser browser) {
		browser.getDriver().switchTo().frame(INPUT_FRAME_LOCATOR);
	}

	public void switchToDefaultContent(Browser browser) {
		browser.getDriver().switchTo().defaultContent();
	}

	public void clearAndType() {
		textBox.clearAndType(RandomStringUtils.randomAlphanumeric(LENGTH_RANDOM_STRING));
	}

	public boolean isTextVisible() {
		return textBox.state().isDisplayed() && !textBox.getText().isEmpty();
	}

	public void selectText() {
		textBox.sendKeys(Keys.chord(Keys.CONTROL, KEY_A));
	}

	public void clickBoldButton() {
		boldButton.click();
	}

	public boolean isTextBold() {
		ITextBox frameText = textBox.findChildElement(By.xpath(ENTERED_TEXT_BOX_LOCATOR), ITextBox.class);
		String fontWeight = frameText.getCssValue("font-weight");
		return fontWeight.equals("bold") || fontWeight.equals(EXPECTED_FONT_WEIGHT);
	}

}
