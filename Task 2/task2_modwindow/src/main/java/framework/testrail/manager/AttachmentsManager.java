package framework.testrail.manager;

import java.io.File;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class AttachmentsManager {

	private static final String ADD_ATTACHMENT_TO_RESULT_URL = "%s/index.php?/api/v2/add_attachment_to_result/%d";

	@SuppressWarnings("unchecked")
	public HttpResponse<JsonNode> addAttachmentToResult(String url, int resultId, String attachmentPath) {
		return Unirest.post(String.format(ADD_ATTACHMENT_TO_RESULT_URL, url, resultId)).multiPartContent()
				.field("attachment", new File(attachmentPath)).asEmpty();
	}

}
