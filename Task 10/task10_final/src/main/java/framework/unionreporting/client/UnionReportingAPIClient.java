package framework.unionreporting.client;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import aquality.selenium.core.logging.Logger;
import framework.utils.FileUtils;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class UnionReportingAPIClient {

	private static final String GET_TOKEN_URL = "%s/token/get?variant=%s";
	private static final String ADD_TEST_URL = "%s/test/put?SID=%s&projectName=%s&testName=%s&methodName=%s&env=%s";
	private static final String ADD_TEST_LOG_URL = "%s/test/put/log?testId=%s";
	private static final String ADD_TEST_ATTACHMENT_URL = "%s/test/put/attachment?testId=%s&contentType=%s";

	private final String apiUrl;

	public UnionReportingAPIClient(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getToken(int variant) {
		Logger.getInstance().debug("Sending the request to get a token");
		return Unirest.post(String.format(GET_TOKEN_URL, apiUrl, variant)).asString().getBody();
	}

	public String addTest(String baseUrl, String sid, String projectName, String testName, String methodName,
			String env) {
		return Unirest.post(String.format(ADD_TEST_URL, baseUrl, sid, projectName, testName, methodName, env))
				.asString().getBody();
	}

	public String addTest(String baseUrl, String projectName, String testName, String methodName, String env) {
		return Unirest.post(String.format(ADD_TEST_URL, baseUrl, RandomStringUtils.randomNumeric(10), projectName,
				testName, methodName, env)).asString().getBody();
	}

	public HttpResponse<String> addTestLog(String baseUrl, String testId, List<String> logs) {
		return Unirest.post(String.format(ADD_TEST_LOG_URL, baseUrl, testId)).field("content", logs).asString();
	}

	public HttpResponse<String> addTestImgAttachment(String baseUrl, String testId, String filePath) {
		return Unirest.post(String.format(ADD_TEST_ATTACHMENT_URL, baseUrl, testId, "image/png"))
				.field("content", Base64.encodeBase64String(FileUtils.getContentAsArray(filePath))).asString();
	}
}
