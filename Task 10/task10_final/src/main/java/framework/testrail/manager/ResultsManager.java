package framework.testrail.manager;

import java.util.HashMap;
import java.util.Map;

import framework.testrail.entities.Result;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class ResultsManager {

	private static final String ADD_RESULT_URL = "%s/api/v%d/add_result/%d";
	private static final String ADD_RESULT_FOR_CASE_URL = "%s/api/v%d/add_result_for_case/%d/%d";
	private static final String GET_RESULTS_FOR_RUN_URL = "%s/api/v%d/get_results_for_run/%d";
	private static final String GET_RESULTS_FOR_CASE_URL = "%s/api/v%d/get_results_for_case/%d/%d";

	public HttpResponse<JsonNode> addResult(String url, Result result, int apiVersion) {
		return Unirest.post(String.format(ADD_RESULT_URL, url, apiVersion, result.getTestId()))
				.header("Content-Type", "application/json").body(new JSONObject(getResultProperties(result))).asJson();
	}

	public HttpResponse<JsonNode> addResultForCase(String url, Result result, int runId, int caseId, int apiVersion) {
		return Unirest.post(String.format(ADD_RESULT_FOR_CASE_URL, url, apiVersion, runId, caseId))
				.header("Content-Type", "application/json")
				.body(new JSONObject(getResultPropertiesWithoutTestId(result))).asJson();
	}

	private Map<String, Object> getResultProperties(Result result) {
		Map<String, Object> properties = getResultPropertiesWithoutTestId(result);
		properties.put("test_id", result.getTestId());
		return properties;
	}

	private Map<String, Object> getResultPropertiesWithoutTestId(Result result) {
		Map<String, Object> properties = new HashMap<>();
		properties.put("status_id", result.getResultStatus().getStatusId());
		properties.put("created_by", result.getCreatedBy());
		properties.put("created_on", result.getCreatedOn());
		properties.put("assignedto_id", result.getAssginedToId());
		properties.put("comment", result.getComment());
		return properties;
	}

	public HttpResponse<JsonNode> getResultsForRun(String url, int runId, int apiVersion) {
		return Unirest.get(String.format(GET_RESULTS_FOR_RUN_URL, url, apiVersion, runId))
				.header("Content-Type", "application/json").asJson();
	}

	public HttpResponse<JsonNode> getResultsForCase(String url, int runId, int caseId, int apiVersion) {
		return Unirest.get(String.format(GET_RESULTS_FOR_CASE_URL, url, apiVersion, runId, caseId))
				.header("Content-Type", "application/json").asJson();
	}

}
