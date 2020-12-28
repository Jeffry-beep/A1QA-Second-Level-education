package framework.testrail.client.settings;

import java.util.Objects;

public class APIClientSettings {

	private final String url;
	private final String username;
	private final String password;

	public APIClientSettings(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, url, username);
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
		APIClientSettings other = (APIClientSettings) obj;
		return Objects.equals(password, other.password) && Objects.equals(url, other.url)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "APIClientSettings [url=" + url + ", username=" + username + ", password=" + password + "]";
	}

}
