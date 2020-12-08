package framework.testrail.manager;

import java.util.HashMap;
import java.util.Map;

import framework.testrail.entities.Result;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class ResultsManager {

	private static final String GET_RESULTS_FOR_RUN_URL = "%s/index.php?/api/v2/get_results_for_run/%d";
	private static final String GET_RESULTS_FOR_CASE_URL = "%s/index.php?/api/v2/get_results_for_case/%d/%d";
	private static final String ADD_RESULT_URL = "%s/index.php?/api/v2/add_result/%d";
	private static final String ADD_RESULT_FOR_CASE_URL = "%s/index.php?/api/v2/add_result_for_case/%d/%d";

	public HttpResponse<JsonNode> addResult(String url, Result result) {
		return Unirest.post(String.format(ADD_RESULT_URL, url, result.getTestId()))
				.header("Content-Type", "application/json").body(new JSONObject(getFields(result))).asJson();
	}

	public HttpResponse<JsonNode> addResultForCase(String url, Result result, int runId, int caseId) {
		return Unirest.post(String.format(ADD_RESULT_FOR_CASE_URL, url, runId, caseId))
				.header("Content-Type", "application/json").body(new JSONObject(getFields2(result))).asJson();
	}

	private Map<String, Object> getFields2(Result result) {
		Map<String, Object> fields = new HashMap<>();
		fields.put("status_id", result.getResultStatus().getStatusId());
		fields.put("created_by", result.getCreatedBy());
		fields.put("created_on", result.getCreatedOn());
		fields.put("assignedto_id", result.getAssginedToId());
		fields.put("comment", result.getComment());
		return fields;
	}

	private Map<String, Object> getFields(Result result) {
		Map<String, Object> fields = new HashMap<>();
		fields.put("status_id", result.getResultStatus().getStatusId());
		fields.put("created_by", result.getCreatedBy());
		fields.put("test_id", result.getTestId());
		fields.put("created_on", result.getCreatedOn());
		fields.put("assignedto_id", result.getAssginedToId());
		fields.put("comment", result.getComment());
		return fields;
	}

	public HttpResponse<JsonNode> getResultsForRun(String url, int runId) {
		return Unirest.get(String.format(GET_RESULTS_FOR_RUN_URL, url, runId))
				.header("Content-Type", "application/json").asJson();
	}

	public HttpResponse<JsonNode> getResultsForCase(String url, int runId, int caseId) {
		return Unirest.get(String.format(GET_RESULTS_FOR_CASE_URL, url, runId, caseId))
				.header("Content-Type", "application/json").asJson();
	}

}
