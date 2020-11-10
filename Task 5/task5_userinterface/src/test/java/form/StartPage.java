package form;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;

public class StartPage extends Form {

	private static final By START_BUTTON_LOCATOR = By.cssSelector("button[class='start__button']");
	private static final String START_BUTTON_KEY = "Start button";
	private static final By START_LINK_LOCATOR = By.cssSelector("a[class='start__link']");
	private static final String START_LINK_KEY = "Start link";

	private final IButton startButton = super.getElementFactory().getButton(START_BUTTON_LOCATOR, START_BUTTON_KEY);
	private final ILink startLink = super.getElementFactory().getLink(START_LINK_LOCATOR, START_LINK_KEY);

	public StartPage() {
		super(START_BUTTON_LOCATOR, "Start page");
	}
	
	public void clickStartLink() {
		startLink.click();
	}

}
