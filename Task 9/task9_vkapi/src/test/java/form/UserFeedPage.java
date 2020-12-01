package form;

import org.openqa.selenium.By;

import aquality.selenium.forms.Form;

public class UserFeedPage extends Form {

	private final SideBar sideBar = new SideBar();

	public UserFeedPage() {
		super(By.id("main_feed"), "VK user feed page");
	}

	public void clickMyPageButton() {
		sideBar.clickMyPageButton();
	}

}
