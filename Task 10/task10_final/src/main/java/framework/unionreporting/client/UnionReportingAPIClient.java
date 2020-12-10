package framework.unionreporting.client;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import aquality.selenium.core.logging.Logger;
import framework.utils.FileUtils;
import kong.unirest.ContentType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class UnionReportingAPIClient {

	private static final String GET_TOKEN_URL = "%s/token/get%s";
	private static final String ADD_TEST_URL = "%s/test/put%s";
	private static final String ADD_TEST_LOG_URL = "%s/test/put/log%s";
	private static final String ADD_TEST_ATTACHMENT_URL = "%s/test/put/attachment%s";
	private static final String PARAMETERS_SEPARATOR = "&";

	private final String apiUrl;

	public UnionReportingAPIClient(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getToken(int variant) {
		Logger.getInstance().debug("Sending the request to get a token");
		return Unirest.post(String.format(GET_TOKEN_URL, apiUrl, String.format("?variant=%s", variant))).asString()
				.getBody();
	}

	public String addTest(String baseUrl, String projectName, String testName, String methodName, String env) {
		return addTest(baseUrl, RandomStringUtils.randomNumeric(10), projectName, testName, methodName, env);
	}

	public String addTest(String baseUrl, String sid, String projectName, String testName, String methodName,
			String env) {
		return Unirest
				.post(String.format(ADD_TEST_URL, baseUrl,
						StringUtils.joinWith(PARAMETERS_SEPARATOR, String.format("?SID=%s", sid),
								String.format("projectName=%s", projectName), String.format("testName=%s", testName),
								String.format("methodName=%s", methodName), String.format("env=%s", env))))
				.asString().getBody();
	}

	public HttpResponse<String> addTestLog(String baseUrl, String testId, List<String> logs) {
		return Unirest.post(String.format(ADD_TEST_LOG_URL, baseUrl, String.format("?testId=%s", testId)))
				.field("content", logs).asString();
	}

	public HttpResponse<String> addTestImgAttachment(String baseUrl, String testId, String filePath) {
		return Unirest
				.post(String.format(ADD_TEST_ATTACHMENT_URL, baseUrl,
						String.join(PARAMETERS_SEPARATOR, String.format("?testId=%s", testId),
								String.format("contentType=%s", ContentType.IMAGE_PNG.getMimeType()))))
				.field("content", Base64.encodeBase64String(FileUtils.getContentAsArray(filePath))).asString();
	}
}
