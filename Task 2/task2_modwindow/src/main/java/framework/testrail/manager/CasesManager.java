package framework.testrail.manager;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class CasesManager {

	private static final String GET_CASE_URL = "%s/index.php?/api/v2/get_case/%d";

	public HttpResponse<JsonNode> getCase(String url, int caseId) {
		return Unirest.get(String.format(GET_CASE_URL, url, caseId)).header("Content-Type", "application/json")
				.asJson();
	}

}
