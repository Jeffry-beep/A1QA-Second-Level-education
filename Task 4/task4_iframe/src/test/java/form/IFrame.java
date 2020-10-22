package form;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class IFrame extends Form {

	private final ITextBox textBox = super.getElementFactory().getTextBox(By.id("tinymce"), "Text area");
	private final IButton boldButton = super.getElementFactory().getButton(By.id("mceu_3"), "Bold button");

	public IFrame() {
		this(By.id("mce_0_ifr"), "Heroku IFrame page frame");
	}

	protected IFrame(By locator, String name) {
		super(locator, name);
	}

	public void clearAndType() {
		textBox.clearAndType(RandomStringUtils.randomAlphanumeric(12));
	}

	public boolean isTextVisible() {
		boolean isVisible = true;
		if (!textBox.state().isDisplayed()) {
			isVisible = false;
		} else if (textBox.getText().isEmpty()) {
			isVisible = false;
		}
		return isVisible;
	}

	public void selectText() {
		textBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	}

	public void clickBoldButton() {
		boldButton.click();
	}

	public boolean isTextBold() {
		ITextBox frameText = textBox.findChildElement(By.xpath(".//p/strong"), ITextBox.class);
		String fontWeight = frameText.getCssValue("font-weight");
		System.out.println(fontWeight);
		return fontWeight.equals("bold") || fontWeight.equals("700");
	}

}
