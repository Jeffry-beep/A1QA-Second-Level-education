package framework.testrail.client;

import framework.testrail.client.settings.APIClientSettings;
import framework.testrail.entities.Result;
import framework.testrail.manager.APIManager;
import kong.unirest.HttpResponse;
import kong.unirest.HttpStatus;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class TestRailAPIClient {

	private static final String LOGIN_URL = "%s/index.php?/auth/login";

	private final APIManager apiManager;
	private final APIClientSettings clientSettings;

	public TestRailAPIClient(APIClientSettings clientSettings) {
		apiManager = new APIManager();
		this.clientSettings = clientSettings;
	}

	public HttpResponse<String> logIn() {
		HttpResponse<String> response = Unirest.get(String.format(LOGIN_URL, clientSettings.getBaseUrl()))
				.basicAuth(clientSettings.getUsername(), clientSettings.getPassword()).asString();
		if (response.getStatus() == HttpStatus.OK) {
			Unirest.config().setDefaultBasicAuth(clientSettings.getUsername(), clientSettings.getPassword());
		}
		return response;
	}

	public HttpResponse<JsonNode> addResult(Result result) {
		return apiManager.addResult(clientSettings.getApiUrl(), result, clientSettings.getApiVersion());
	}

	public HttpResponse<JsonNode> addResultForCase(Result result, int runId, int caseId) {
		return apiManager.addResultForCase(clientSettings.getApiUrl(), result, runId, caseId,
				clientSettings.getApiVersion());
	}

	public HttpResponse<JsonNode> addAttachmentToResult(int resultId, String attachmentPath) {
		return apiManager.addAttachmentToResult(clientSettings.getApiUrl(), resultId, attachmentPath,
				clientSettings.getApiVersion());
	}

	public HttpResponse<JsonNode> getResultsForRun(int runId) {
		return apiManager.getResultsForRun(clientSettings.getApiUrl(), runId, clientSettings.getApiVersion());
	}

	public HttpResponse<JsonNode> getResultsForCase(int runId, int caseId) {
		return apiManager.getResultsForCase(clientSettings.getApiUrl(), runId, caseId, clientSettings.getApiVersion());
	}

	public HttpResponse<JsonNode> getCase(int caseId) {
		return apiManager.getCase(clientSettings.getApiUrl(), caseId, clientSettings.getApiVersion());
	}

	public HttpResponse<JsonNode> getRun(int runId) {
		return apiManager.getRun(clientSettings.getApiUrl(), runId, clientSettings.getApiVersion());
	}

}
