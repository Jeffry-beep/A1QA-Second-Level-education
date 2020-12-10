package framework.testrail.manager;

import framework.testrail.entities.Result;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public class APIManager {

	private final AttachmentsManager attachmentsManager;
	private final CasesManager casesManager;
	private final ResultsManager resultsManager;
	private final RunsManager runsManager;

	public APIManager() {
		attachmentsManager = new AttachmentsManager();
		casesManager = new CasesManager();
		resultsManager = new ResultsManager();
		runsManager = new RunsManager();
	}

	public HttpResponse<JsonNode> addResult(String url, Result result, int apiVersion) {
		return resultsManager.addResult(url, result, apiVersion);
	}

	public HttpResponse<JsonNode> addResultForCase(String url, Result result, int runId, int caseId, int apiVersion) {
		return resultsManager.addResultForCase(url, result, runId, caseId, apiVersion);
	}

	public HttpResponse<JsonNode> addAttachmentToResult(String url, int resultId, String attachmentPath,
			int apiVersion) {
		return attachmentsManager.addAttachmentToResult(url, resultId, attachmentPath, apiVersion);
	}

	public HttpResponse<JsonNode> getResultsForRun(String url, int runId, int apiVersion) {
		return resultsManager.getResultsForRun(url, runId, apiVersion);
	}

	public HttpResponse<JsonNode> getResultsForCase(String url, int runId, int caseId, int apiVersion) {
		return resultsManager.getResultsForCase(url, runId, caseId, apiVersion);
	}

	public HttpResponse<JsonNode> getCase(String url, int caseId, int apiVersion) {
		return casesManager.getCase(url, caseId, apiVersion);
	}

	public HttpResponse<JsonNode> getRun(String url, int runId, int apiVersion) {
		return runsManager.getRun(url, runId, apiVersion);
	}

}
