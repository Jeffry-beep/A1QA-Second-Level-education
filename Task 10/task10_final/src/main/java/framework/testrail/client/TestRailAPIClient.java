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

	public HttpResponse<JsonNode> logIn() {
		HttpResponse<JsonNode> response = Unirest.get(String.format(LOGIN_URL, clientSettings.getUrl()))
				.basicAuth(clientSettings.getUsername(), clientSettings.getPassword()).asJson();
		if (response.getStatus() == HttpStatus.OK) {
			Unirest.config().setDefaultBasicAuth(clientSettings.getUsername(), clientSettings.getPassword());
		}
		return response;
	}

	public HttpResponse<JsonNode> addResult(Result result) {
		return apiManager.addResult(clientSettings.getUrl(), result);
	}

	public HttpResponse<JsonNode> addResultForCase(Result result, int runId, int caseId) {
		return apiManager.addResultForCase(clientSettings.getUrl(), result, runId, caseId);
	}

	public HttpResponse<JsonNode> addAttachmentToResult(int resultId, String attachmentPath) {
		return apiManager.addAttachmentToResult(clientSettings.getUrl(), resultId, attachmentPath);
	}

	public HttpResponse<JsonNode> getResultsForRun(int runId) {
		return apiManager.getResultsForRun(clientSettings.getUrl(), runId);
	}

	public HttpResponse<JsonNode> getResultsForCase(int runId, int caseId) {
		return apiManager.getResultsForCase(clientSettings.getUrl(), runId, caseId);
	}

	public HttpResponse<JsonNode> getCase(int caseId) {
		return apiManager.getCase(clientSettings.getUrl(), caseId);
	}

	public HttpResponse<JsonNode> getRun(int runId) {
		return apiManager.getRun(clientSettings.getUrl(), runId);
	}

}
