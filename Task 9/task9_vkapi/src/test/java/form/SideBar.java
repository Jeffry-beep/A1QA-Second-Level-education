package form;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;

public class SideBar extends Form {

	private static final Logger LOGGER = LogManager.getLogger(SideBar.class);
	
	private final IButton myPageButton = super.getElementFactory().getButton(By.id("l_pr"), "My page button");

	public SideBar() {
		super(By.id("side_bar"), "VK welcome page");
	}

	public void clickMyPageButtonAndWait() {
		LOGGER.debug("Clicking 'My page' button and wait");
		myPageButton.clickAndWait();
	}

}
