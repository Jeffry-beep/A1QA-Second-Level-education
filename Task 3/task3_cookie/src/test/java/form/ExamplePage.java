package form;

import org.openqa.selenium.By;

import aquality.selenium.forms.Form;

public class ExamplePage extends Form {

	public ExamplePage() {
		this(By.xpath("//h1[text()[contains(.,'Example Domain')]]"), "Example domain page");
	}

	protected ExamplePage(By locator, String name) {
		super(locator, name);
	}

}
