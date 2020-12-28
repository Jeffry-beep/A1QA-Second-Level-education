package framework.testrail.client.settings;

public class APIClientSettings {

	private final String baseUrl;
	private final String apiUrl;
	private final String username;
	private final String password;
	private final int apiVersion;

	public APIClientSettings(String baseUrl, String apiUrl, String username, String password, int apiVersion) {
		this.baseUrl = baseUrl;
		this.apiUrl = apiUrl;
		this.username = username;
		this.password = password;
		this.apiVersion = apiVersion;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getApiUrl() {
		return apiUrl;
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
		return "APIClientSettings [baseUrl=" + baseUrl + ", apiUrl=" + apiUrl + ", username=" + username + ", password="
				+ password + ", apiVersion=" + apiVersion + "]";
	}

}
