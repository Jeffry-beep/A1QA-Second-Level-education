package form;

import org.openqa.selenium.By;

import aquality.selenium.forms.Form;

public class PersonalDetailsForm extends Form {

	public PersonalDetailsForm() {
		super(By.cssSelector("div[class='personal-details__form']"), "Personal details form");
	}

}
