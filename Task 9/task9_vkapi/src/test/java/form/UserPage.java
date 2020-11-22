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
	private static final String USER_POSTS_LOC = "div[@class='_post']";
	private static final String POST_CREATOR_LOC = "h5[class*='post_author']";
	private static final String POST_TEXT_LOC = "div[class*='wall_post_text']";
	private static final String POST_PHOTO_LOC = "a[class*='image_cover']";
	private static final String POST_LIKE_WRAP_LOC = "div[class*='like_wrap']";
	private static final String POST_REPLIES_BUTTON_LOC = "a[class*='_comment']";
	private static final String POST_REPLIY_FORM_LOC = "div[class*='reply_form']";
	private static final String POST_REPLIY_FIELD_LOC = "div[class*='reply_field']";
	private static final String POST_REPLIY_SEND_BUTTON_LOC = "div[class*='reply_send_button']";
	private static final String POST_LIKE_BUTTON_LOC = "a[class*='_like']";
	private static final String NEXT_POST_COMMENTS_BUTTON_LOC = "a[class*='replies_next']";
	private static final String FIRST_POST_REPLY_LOC = "div[class*='reply']";
	private static final String COMMENT_CREATOR_LOC = "div[class*='reply_author']";
	private static final String COMMENT_TEXT_LOC = "div[class*='reply_text']";
	private static final String POST_ID_FORMAT = "post%d_%d";

	private final ITextBox userPostsBlock = super.getElementFactory().getTextBox(By.id("page_wall_posts"),
			"The block of user posts on his wall");

	public UserPage() {
		super(By.id("page_info_wrap"), "VK user page");
	}

	public List<ITextBox> getPosts() {
		LOGGER.debug("Getting user posts");
		return userPostsBlock.findChildElements(By.cssSelector(USER_POSTS_LOC), ElementType.TEXTBOX);
	}

	public ITextBox getPost(int ownderId, int postId) {
		LOGGER.debug("Getting a user post");
		return super.getElementFactory().getTextBox(By.id(String.format(POST_ID_FORMAT, ownderId, postId)),
				"User post");
	}

	public String getPostCreator(ITextBox post) {
		LOGGER.debug("Getting a post creator");
		return post.findChildElement(By.cssSelector(POST_CREATOR_LOC), TextBox.class).getText();
	}

	public String getPostText(ITextBox post) {
		LOGGER.debug("Getting a post text");
		return post.findChildElement(By.cssSelector(POST_TEXT_LOC), TextBox.class).getText();
	}

	public String getPostPhotoId(ITextBox post) {
		LOGGER.debug("Getting a post photo id");
		return post.findChildElement(By.cssSelector(POST_PHOTO_LOC), TextBox.class).getAttribute("data-photo-id");
	}

	public ITextBox getPostLikeWrap(ITextBox post) {
		LOGGER.debug("Getting the post like wrap");
		return post.findChildElement(By.cssSelector(POST_LIKE_WRAP_LOC), TextBox.class);
	}

	public void clickPostRespliesButton(ITextBox post) {
		LOGGER.debug("Getting the post replies button");
		IButton button = post.findChildElement(By.cssSelector(POST_REPLIES_BUTTON_LOC), Button.class);
		LOGGER.debug("Scrolling to the button center");
		button.getJsActions().scrollToTheCenter();
		LOGGER.debug("Clicking the button");
		button.click();
	}

	public boolean isPostReplyFormDisplayed(ITextBox post) {
		LOGGER.debug("Checking that a post reply form is displayed");
		return post.findChildElement(By.cssSelector(POST_REPLIY_FORM_LOC), TextBox.class).state().isDisplayed();
	}

	public void fillPostReplyField(ITextBox post, String message) {
		LOGGER.debug("Filling post reply field");
		post.findChildElement(By.cssSelector(POST_REPLIY_FIELD_LOC), TextBox.class).clearAndType(message);
	}

	public void clickPostReplySendButon(ITextBox post) {
		LOGGER.debug("Clicking the post reply send button");
		post.findChildElement(By.cssSelector(POST_REPLIY_SEND_BUTTON_LOC), Button.class).click();
	}

	public void clickPostLikeButtonAndWait(ITextBox post) {
		LOGGER.debug("Getting the post like button");
		IButton button = post.findChildElement(By.cssSelector(POST_LIKE_BUTTON_LOC), Button.class);
		LOGGER.debug("Scrolling to the button center");
		button.getJsActions().scrollToTheCenter();
		LOGGER.debug("Clicking the button");
		button.clickAndWait();
	}

	public void clickNextPostCommentsButton(ITextBox post) {
		LOGGER.debug("Getting the next post comments button");
		IButton button = post.findChildElement(By.cssSelector(NEXT_POST_COMMENTS_BUTTON_LOC), Button.class);
		LOGGER.debug("Scrolling to the button center");
		button.getJsActions().scrollToTheCenter();
		LOGGER.debug("Clicking the button");
		button.click();
	}

	public void moveMouseToFirstPostReply(ITextBox post) {
		LOGGER.debug("Moving to the first post reply");
		post.findChildElement(By.cssSelector(FIRST_POST_REPLY_LOC), TextBox.class).getMouseActions()
				.moveMouseToElement();
	}

	public String getCommentCreator(ITextBox comment) {
		LOGGER.debug("Getting a comment creator");
		return comment.findChildElement(By.cssSelector(COMMENT_CREATOR_LOC), TextBox.class).getText();
	}

	public String getCommentText(ITextBox comment) {
		LOGGER.debug("Getting a comment text");
		return comment.findChildElement(By.cssSelector(COMMENT_TEXT_LOC), TextBox.class).getText();
	}

}
