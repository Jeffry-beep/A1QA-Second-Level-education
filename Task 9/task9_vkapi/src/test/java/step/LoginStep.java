package step;

import form.WelcomePage;

public class LoginStep {

	private static final WelcomePage PAGE = new WelcomePage();

	public static void login(String email, String password) {
		PAGE.fillEmail(email);
		PAGE.fillPassword(password);
		PAGE.clickLoginButton();
	}

}
