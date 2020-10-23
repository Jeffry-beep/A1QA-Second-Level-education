package form;

import org.openqa.selenium.By;

import aquality.selenium.browser.Browser;
import aquality.selenium.forms.Form;

public class InputFramePage extends Form {

	private static final String EXPECTED_INPUT_FRAME_TITLE = "An iFrame containing the TinyMCE WYSIWYG Editor";
	private final InputFrame frame = new InputFrame();

	public InputFramePage() {
		super(By.id("mceu_13"), "Heroku IFrame page");
	}

	public boolean isInputFrameTitleCorrect() {
		return super.getElementFactory().getTextBox(By.xpath(".//*[@id='content']/div/h3"), "Input frame title")
				.getText().equals(EXPECTED_INPUT_FRAME_TITLE);
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
