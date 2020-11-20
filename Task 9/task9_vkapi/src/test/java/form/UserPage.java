package form;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import aquality.selenium.elements.Button;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.TextBox;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class UserPage extends Form {

	private static final Logger LOGGER = LogManager.getLogger(UserPage.class);
	private final static String POST_ID_FORMAT = "post%d_%d";

	private final ITextBox userPostsBlock = super.getElementFactory().getTextBox(By.id("page_wall_posts"),
			"The block of user posts on his wall");

	public UserPage() {
		super(By.id("page_info_wrap"), "VK user page");
	}

	public List<ITextBox> getPosts() {
		LOGGER.debug("Getting user posts");
		return userPostsBlock.findChildElements(By.cssSelector("div[@class='_post']"), ElementType.TEXTBOX);
	}

	public ITextBox getPost(int ownderId, int postId) {
		LOGGER.debug("Getting a user post");
		return super.getElementFactory().getTextBox(By.id(String.format(POST_ID_FORMAT, ownderId, postId)),
				"User post");
	}

	public String getPostCreator(ITextBox post) {
		LOGGER.debug("Getting a post creator");
		return post.findChildElement(By.cssSelector("h5[class*='post_author']"), TextBox.class).getText();
	}

	public String getPostText(ITextBox post) {
		LOGGER.debug("Getting a post text");
		return post.findChildElement(By.cssSelector("div[class*='wall_post_text']"), TextBox.class).getText();
	}

	public String getPostPhotoId(ITextBox post) {
		LOGGER.debug("Getting a post photo id");
		return post.findChildElement(By.cssSelector("a[class*='image_cover']"), TextBox.class)
				.getAttribute("data-photo-id");
	}

	public ITextBox getPostLikeWrap(ITextBox post) {
		LOGGER.debug("Getting the post like wrap");
		return post.findChildElement(By.cssSelector("div[class*='like_wrap']"), TextBox.class);
	}

	public void clickPostRespliesButton(ITextBox post) {
		LOGGER.debug("Getting the post replies button");
		IButton button = post.findChildElement(By.cssSelector("a[class*='_comment']"), Button.class);
		LOGGER.debug("Moving to the button");
		button.getMouseActions().moveMouseToElement();
		LOGGER.debug("Clicking the button");
		button.click();
	}

	public boolean isPostReplyFormDisplayed(ITextBox post) {
		LOGGER.debug("Checking that a post reply form is displayed");
		return post.findChildElement(By.cssSelector("div[class*='reply_form']"), TextBox.class).state().isDisplayed();
	}

	public void fillPostReplyField(ITextBox post, String message) {
		LOGGER.debug("Filling post reply field");
		post.findChildElement(By.cssSelector("div[class*='reply_field']"), TextBox.class).clearAndType(message);
	}

	public void clickPostReplySendButon(ITextBox post) {
		LOGGER.debug("Clicking the post reply send button");
		post.findChildElement(By.cssSelector("div[class*='reply_send_button']"), Button.class).click();
	}

	public void clickPostLikeButtonAndWait(ITextBox post) {
		LOGGER.debug("Getting the post like button");
		IButton button = post.findChildElement(By.cssSelector("a[class*='_like']"), Button.class);
		LOGGER.debug("Moving to the button");
		button.getMouseActions().moveMouseToElement();
		LOGGER.debug("Clicking the button");
		button.clickAndWait();
	}

	public void clickNextPostCommentsButton(ITextBox post) {
		LOGGER.debug("Getting the next post comments button");
		IButton button = post.findChildElement(By.cssSelector("a[class*='replies_next']"), Button.class);
		LOGGER.debug("Moving to the button");
		button.getMouseActions().moveMouseToElement();
		LOGGER.debug("Clicking the button");
		button.click();
	}

	public void moveMouseToFirstPostReply(ITextBox post) {
		LOGGER.debug("Moving to the first post reply");
		post.findChildElement(By.cssSelector("div[class*='reply']"), TextBox.class).getMouseActions()
				.moveMouseToElement();
	}

	public String getCommentCreator(ITextBox comment) {
		LOGGER.debug("Getting a comment creator");
		return comment.findChildElement(By.cssSelector("div[class*='reply_author']"), TextBox.class).getText();
	}

	public String getCommentText(ITextBox comment) {
		LOGGER.debug("Getting a comment text");
		return comment.findChildElement(By.cssSelector("div[class*='reply_text']"), TextBox.class).getText();
	}

}
