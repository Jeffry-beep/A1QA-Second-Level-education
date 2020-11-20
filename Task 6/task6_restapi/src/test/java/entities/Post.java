package entities;

import java.util.Objects;

public class Post {

	protected int userId;
	protected int id;
	protected String title;
	protected String body;

	public Post() {

	}

	public Post(int userId, String title, String body) {
		this.userId = userId;
		this.title = title;
		this.body = body;
	}

	public Post(int userId, int id, String title, String body) {
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, id, title, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Post other = (Post) obj;
		return Objects.equals(body, other.body) && id == other.id && Objects.equals(title, other.title)
				&& userId == other.userId;
	}

	@Override
	public String toString() {
		return "BasePost [userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + "]";
	}

}
