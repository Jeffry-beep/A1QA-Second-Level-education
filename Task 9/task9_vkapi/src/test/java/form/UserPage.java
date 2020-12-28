package form;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.Button;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.TextBox;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class UserPage extends Form {

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
	private static final String POST_LIKE_COUNT_LOC = "div[@class*='like_button_count']";
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

	@SuppressWarnings("unused")
	private List<ITextBox> getPosts() {
		Logger.getInstance().debug("Getting user posts");
		return userPostsBlock.findChildElements(By.cssSelector(USER_POSTS_LOC), ElementType.TEXTBOX);
	}

	private ITextBox getPost(int ownderId, int postId) {
		Logger.getInstance().debug("Getting a user post");
		return super.getElementFactory().getTextBox(By.id(String.format(POST_ID_FORMAT, ownderId, postId)),
				"User post");
	}

	public String getPostCreator(int userId, int postId) {
		Logger.getInstance().debug("Getting a post creator");
		return getPost(userId, postId).findChildElement(By.cssSelector(POST_CREATOR_LOC), TextBox.class).getText();
	}

	public String getPostText(int userId, int postId) {
		Logger.getInstance().debug("Getting a post text");
		return getPost(userId, postId).findChildElement(By.cssSelector(POST_TEXT_LOC), TextBox.class).getText();
	}

	public String getPostPhotoId(int userId, int postId) {
		Logger.getInstance().debug("Getting a post photo id");
		return getPost(userId, postId).findChildElement(By.cssSelector(POST_PHOTO_LOC), TextBox.class)
				.getAttribute("data-photo-id");
	}

	public boolean waitForPostPhotoDisplayed(int userId, int postId) {
		Logger.getInstance().debug("Waitting for a post photo to be displayed");
		return getPost(userId, postId).findChildElement(By.cssSelector(POST_PHOTO_LOC), TextBox.class).state()
				.waitForDisplayed();
	}

	@SuppressWarnings("unused")
	private ITextBox getPostLikeWrap(ITextBox post) {
		Logger.getInstance().debug("Getting the post like wrap");
		return post.findChildElement(By.cssSelector(POST_LIKE_WRAP_LOC), TextBox.class);
	}

	public void clickPostRespliesButton(ITextBox post) {
		Logger.getInstance().debug("Getting the post replies button");
		IButton button = post.findChildElement(By.cssSelector(POST_REPLIES_BUTTON_LOC), Button.class);
		Logger.getInstance().debug("Scrolling to the button center");
		button.getJsActions().scrollToTheCenter();
		Logger.getInstance().debug("Clicking the button");
		button.click();
	}

	public boolean isPostReplyFormDisplayed(ITextBox post) {
		Logger.getInstance().debug("Checking that a post reply form is displayed");
		return post.findChildElement(By.cssSelector(POST_REPLIY_FORM_LOC), TextBox.class).state().isDisplayed();
	}

	public void fillPostReplyField(ITextBox post, String message) {
		Logger.getInstance().debug("Filling post reply field");
		post.findChildElement(By.cssSelector(POST_REPLIY_FIELD_LOC), TextBox.class).clearAndType(message);
	}

	public void clickPostReplySendButon(ITextBox post) {
		Logger.getInstance().debug("Clicking the post reply send button");
		post.findChildElement(By.cssSelector(POST_REPLIY_SEND_BUTTON_LOC), Button.class).click();
	}

	public void clickPostLikeButton(int userId, int postId) {
		Logger.getInstance().debug("Getting the post like button");
		IButton button = getPost(userId, postId).findChildElement(By.cssSelector(POST_LIKE_BUTTON_LOC), Button.class);
		Logger.getInstance().debug("Scrolling to the button center");
		button.getJsActions().scrollToTheCenter();
		Logger.getInstance().debug("Clicking the button");
		button.click();
	}

	public boolean waitForPostLikeCountDisplayed(int userId, int postId) {
		Logger.getInstance().debug("Waitting for the post like count is displayed");
		return getPost(userId, postId).findChildElement(By.cssSelector(POST_LIKE_COUNT_LOC), Button.class).state()
				.waitForNotDisplayed();
	}

	public void clickNextPostCommentsButton(int userId, int postId) {
		Logger.getInstance().debug("Getting the next post comments button");
		IButton button = getPost(userId, postId).findChildElement(By.cssSelector(NEXT_POST_COMMENTS_BUTTON_LOC),
				Button.class);
		Logger.getInstance().debug("Scrolling to the button center");
		button.getJsActions().scrollToTheCenter();
		Logger.getInstance().debug("Clicking the button");
		button.click();
	}

	public void scrollToComment(int userId, int commentId) {
		Logger.getInstance().debug("Scrolling to the center of the comment");
		getPost(userId, commentId).findChildElement(By.cssSelector(FIRST_POST_REPLY_LOC), TextBox.class).getJsActions()
				.scrollToTheCenter();
	}

	public String getCommentCreator(int userId, int commentId) {
		Logger.getInstance().debug("Getting a comment creator");
		return getPost(userId, commentId).findChildElement(By.cssSelector(COMMENT_CREATOR_LOC), TextBox.class)
				.getText();
	}

	public String getCommentText(int userId, int commentId) {
		Logger.getInstance().debug("Getting a comment text");
		return getPost(userId, commentId).findChildElement(By.cssSelector(COMMENT_TEXT_LOC), TextBox.class).getText();
	}

	public void waitForCommentTextDisplayed(int userId, int commentId, String text) {
		Logger.getInstance().debug("Waitting for the post text to be displayed");
		try {
			AqualityServices.getConditionalWait().waitForTrue(() -> getPost(userId, commentId)
					.findChildElement(By.cssSelector(COMMENT_TEXT_LOC), TextBox.class).getText().equals(text));
		} catch (TimeoutException e) {
			Logger.getInstance().debug("The post text isn't displayed", e);
		}
	}

	public boolean waitForPostNotDisplayed(int userId, int postId) {
		return getPost(userId, postId).state().waitForNotDisplayed();
	}

}
