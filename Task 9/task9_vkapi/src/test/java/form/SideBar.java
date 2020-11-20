package form;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;

public class SideBar extends Form {

	private final IButton myPageButton = super.getElementFactory().getButton(By.id("l_pr"), "My page button");

	public SideBar() {
		super(By.id("side_bar"), "VK welcome page");
	}

	public void clickMyPageButtonAndWait() {
		myPageButton.clickAndWait();
	}

}
