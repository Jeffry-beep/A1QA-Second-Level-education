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

	public HttpResponse<JsonNode> addResult(String url, Result result) {
		return resultsManager.addResult(url, result);
	}

	public HttpResponse<JsonNode> addAttachmentToResult(String url, int resultId, String attachmentPath) {
		return attachmentsManager.addAttachmentToResult(url, resultId, attachmentPath);
	}

	public HttpResponse<JsonNode> getResultsForRun(String url, int runId) {
		return resultsManager.getResultsForRun(url, runId);
	}

	public HttpResponse<JsonNode> getResultsForCase(String url, int runId, int caseId) {
		return resultsManager.getResultsForCase(url, runId, caseId);
	}

	public HttpResponse<JsonNode> getCase(String url, int caseId) {
		return casesManager.getCase(url, caseId);
	}

	public HttpResponse<JsonNode> getRun(String url, int runId) {
		return runsManager.getRun(url, runId);
	}

}
