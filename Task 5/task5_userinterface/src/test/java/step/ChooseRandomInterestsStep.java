package step;

import form.GamePage;

public class ChooseRandomInterestsStep {

	private final GamePage gamePage;

	public ChooseRandomInterestsStep(GamePage gamePage) {
		this.gamePage = gamePage;
	}

	public void execute(int interestsNumber, String uploadFilePath) {
		gamePage.chooseRandomInterests(interestsNumber);
		gamePage.clickUploadImageLink();
		gamePage.uploadImage(uploadFilePath);
		gamePage.clickAvatarNextButton();
	}

}
