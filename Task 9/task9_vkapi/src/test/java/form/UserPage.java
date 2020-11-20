package form;

import java.util.List;

import org.openqa.selenium.By;

import aquality.selenium.elements.Button;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.TextBox;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class UserPage extends Form {

	private final static String POST_ID_FORMAT = "post%d_%d";

	private final ITextBox userPostsBlock = super.getElementFactory().getTextBox(By.id("page_wall_posts"),
			"The block of user posts on his wall");

	public UserPage() {
		super(By.id("page_info_wrap"), "VK user page");
	}

	public List<ITextBox> getPosts() {
		return userPostsBlock.findChildElements(By.cssSelector("div[@class='_post']"), ElementType.TEXTBOX);
	}

	public ITextBox getPost(int ownderId, int postId) {
		return super.getElementFactory().getTextBox(By.id(String.format(POST_ID_FORMAT, ownderId, postId)),
				"User post");
	}

	public String getPostCreator(ITextBox post) {
		return post.findChildElement(By.cssSelector("h5[class*='post_author']"), TextBox.class).getText();
	}

	public String getPostText(ITextBox post) {
		return post.findChildElement(By.cssSelector("div[class*='wall_post_text']"), TextBox.class).getText();
	}

	public String getPostPhotoId(ITextBox post) {
		return post.findChildElement(By.cssSelector("a[class*='image_cover']"), TextBox.class)
				.getAttribute("data-photo-id");
	}

	public ITextBox getPostLikeWrap(ITextBox post) {
		return post.findChildElement(By.cssSelector("div[class*='like_wrap']"), TextBox.class);
	}

	public void clickPostRespliesButton(ITextBox post) {
		IButton button = post.findChildElement(By.cssSelector("a[class*='_comment']"), Button.class);
		button.getMouseActions().moveMouseToElement();
		button.click();
	}

	public boolean isPostReplyFormDisplayed(ITextBox post) {
		return post.findChildElement(By.cssSelector("div[class*='reply_form']"), TextBox.class).state().isDisplayed();
	}

	public void fillPostReplyField(ITextBox post, String message) {
		post.findChildElement(By.cssSelector("div[class*='reply_field']"), TextBox.class).clearAndType(message);
	}

	public void clickPostReplySendButon(ITextBox post) {
		post.findChildElement(By.cssSelector("div[class*='reply_send_button']"), Button.class).click();
	}

	public void clickPostLikeButtonAndWait(ITextBox post) {
		IButton button = post.findChildElement(By.cssSelector("a[class*='_like']"), Button.class);
		button.getMouseActions().moveMouseToElement();
		button.clickAndWait();
	}

	public void clickNextPostCommentsButton(ITextBox post) {
		IButton button = post.findChildElement(By.cssSelector("a[class*='replies_next']"), Button.class);
		button.getMouseActions().moveMouseToElement();
		button.click();
	}

	public void moveMouseToFirstPostReply(ITextBox post) {
		post.findChildElement(By.cssSelector("div[class*='reply']"), TextBox.class).getMouseActions()
				.moveMouseToElement();
	}

	public String getCommentCreator(ITextBox comment) {
		return comment.findChildElement(By.cssSelector("div[class*='reply_author']"), TextBox.class).getText();
	}

	public String getCommentText(ITextBox comment) {
		return comment.findChildElement(By.cssSelector("div[class*='reply_text']"), TextBox.class).getText();
	}

}
