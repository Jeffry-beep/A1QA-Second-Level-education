package entity;

public enum TestStatus {

	UNFINISHED(0, "Unfinished"), PASSED(1, "Passed"), FAILED(2, "Failed"), SKIPPED(3, "Skipped"),
	IN_PROGRESS(4, "In progress");

	private final int statusId;
	private final String description;

	private TestStatus(int statusId, String description) {
		this.statusId = statusId;
		this.description = description;
	}

	public int getStatusId() {
		return statusId;
	}

	public String getStatusIdAsString() {
		return String.valueOf(statusId);
	}

	public String getDescription() {
		return description;
	}

}
