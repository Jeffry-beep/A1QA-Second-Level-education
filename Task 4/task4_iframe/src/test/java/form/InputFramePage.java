package form;

import org.openqa.selenium.By;

import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class InputFramePage extends Form {

	private final InputFrame frame = new InputFrame();
	private final ITextBox frameTitle = super.getElementFactory().getTextBox(By.xpath(".//*[@id='content']/div/h3"),
			"Input frame title");

	public InputFramePage() {
		super(By.id("mceu_13"), "Heroku IFrame page");
	}

	public String getInputFrameTitle() {
		return frameTitle.getText();
	}

	public void clearAndTypeFrame(Browser browser) {
		frame.switchToFrame(browser);
		frame.clearAndType();
	}

	public boolean isFrameTextVisible() {
		return frame.isTextVisible();
	}

	public void selectFrameText() {
		frame.selectText();
	}

	public void clickFrameBoldButton(Browser browser) {
		frame.switchToDefaultContent(browser);
		frame.clickBoldButton();
	}

	public boolean isFrameTextBold(Browser browser) {
		frame.switchToFrame(browser);
		return frame.isTextBold();
	}

}
