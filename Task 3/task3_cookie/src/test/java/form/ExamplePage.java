package form;

import org.openqa.selenium.By;

import aquality.selenium.forms.Form;

public class ExamplePage extends Form {

	public ExamplePage() {
		super(By.xpath("//h1[text()[contains(.,'Example Domain')]]"), "Example domain page");
	}

}
