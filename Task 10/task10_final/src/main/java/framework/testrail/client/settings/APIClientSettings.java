package framework.testrail.client.settings;

public class APIClientSettings {

	private final String url;
	private final String username;
	private final String password;
	private final int apiVersion;

	public APIClientSettings(String url, String username, String password, int apiVersion) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.apiVersion = apiVersion;
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

	public int getApiVersion() {
		return apiVersion;
	}

	@Override
	public String toString() {
		return "APIClientSettings [url=" + url + ", username=" + username + ", password=" + password + ", apiVersion="
				+ apiVersion + "]";
	}

}
