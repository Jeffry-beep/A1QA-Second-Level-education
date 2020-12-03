package framework.testrail.manager;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class RunsManager {

	private static final String GET_RUN_URL = "%s/index.php?/api/v2/get_run/%d";

	public HttpResponse<JsonNode> getRun(String url, int runId) {
		return Unirest.get(String.format(GET_RUN_URL, url, runId)).header("Content-Type", "application/json").asJson();
	}

}
