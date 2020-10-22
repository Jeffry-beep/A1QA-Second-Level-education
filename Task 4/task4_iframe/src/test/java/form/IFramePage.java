package form;

import org.openqa.selenium.By;

import aquality.selenium.forms.Form;

public class IFramePage extends Form {

	private final IFrame frame = new IFrame();

	public IFramePage() {
		this(By.id("mceu_13"), "Heroku IFrame page");
	}

	protected IFramePage(By locator, String name) {
		super(locator, name);
	}

	public void clearAndTypeFrame() {
		frame.clearAndType();
	}

	public boolean isFrameTextVisible() {
		return frame.isTextVisible();
	}

	public void selectFrameText() {
		frame.selectText();
	}

	public void clickFrameBoldButton() {
		frame.clickBoldButton();
	}

	public boolean isFrameTextBold() {
		return frame.isTextBold();
	}

}
