package step;

import form.WelcomePage;

public class LoginStep {

	private final String email;
	private final String password;
	private final WelcomePage page;

	public LoginStep(String email, String password, WelcomePage page) {
		this.email = email;
		this.password = password;
		this.page = page;
	}

	public void login() {
		page.fillEmail(email);
		page.fillPassword(password);
		page.clickLoginButtonAndWait();
	}

}
