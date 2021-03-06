package framework.testrail.entities;

import java.util.Objects;

public class Result {

	private ResultStatus resultStatus;
	private int createdBy;
	private int testId;
	private long createdOn;
	private int assginedToId;
	private String comment;

	public Result() {

	}

	public Result(ResultStatus resultStatus, int createdBy, int testId, long createdOn, int assginedToId,
			String comment) {
		this.resultStatus = resultStatus;
		this.createdBy = createdBy;
		this.testId = testId;
		this.createdOn = createdOn;
		this.assginedToId = assginedToId;
		this.comment = comment;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public int getAssginedToId() {
		return assginedToId;
	}

	public void setAssginedToId(int assginedToId) {
		this.assginedToId = assginedToId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assginedToId, comment, createdBy, createdOn, resultStatus, testId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Result other = (Result) obj;
		return assginedToId == other.assginedToId && Objects.equals(comment, other.comment)
				&& createdBy == other.createdBy && createdOn == other.createdOn && resultStatus == other.resultStatus
				&& testId == other.testId;
	}

	@Override
	public String toString() {
		return "Result [resultStatus=" + resultStatus + ", createdBy=" + createdBy + ", testId=" + testId
				+ ", createdOn=" + createdOn + ", assginedToId=" + assginedToId + ", comment=" + comment + "]";
	}

}
