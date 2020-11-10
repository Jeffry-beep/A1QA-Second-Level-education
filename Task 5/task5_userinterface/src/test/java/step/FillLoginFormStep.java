package step;

import form.GamePage;

public class FillLoginFormStep {

	private final GamePage gamePage;

	public FillLoginFormStep(GamePage gamePage) {
		this.gamePage = gamePage;
	}

	public void execute(String password, String email, String domain, String tlds) {
		gamePage.setPassword(password);
		gamePage.setEmail(email);
		gamePage.setDomain(domain);
		gamePage.setGenericTLDs(tlds);
		gamePage.acceptTermsConditions();
		gamePage.clickNextButton();
	}

}
