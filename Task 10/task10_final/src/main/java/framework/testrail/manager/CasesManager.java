package framework.testrail.manager;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class CasesManager {

	private static final String GET_CASE_URL = "%s/api/v%d/get_case/%d";

	public HttpResponse<JsonNode> getCase(String url, int caseId, int apiVersion) {
		return Unirest.get(String.format(GET_CASE_URL, url, apiVersion, caseId))
				.header("Content-Type", "application/json").asJson();
	}

}
