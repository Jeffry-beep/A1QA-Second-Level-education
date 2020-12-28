package entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST")
public class Test {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "status_id")
	private Integer statusId;
	@Column(name = "method_name")
	private String methodName;
	@Column(name = "project_id")
	private Integer projectId;
	@Column(name = "session_id")
	private Integer sessionId;
	@Column(name = "start_time")
	private LocalDateTime startTime;
	@Column(name = "end_time")
	private LocalDateTime endTime;
	@Column(name = "env")
	private String env;
	@Column(name = "browser")
	private String browser;
	@Column(name = "author_id")
	private Integer authorId;

	public Test() {

	}

	public Test(int id, String name, Integer statusId, String methodName, int projectId, int sessionId,
			LocalDateTime startTime, LocalDateTime endTime, String env, String browser, int authorId) {
		this.id = id;
		this.name = name;
		this.statusId = statusId;
		this.methodName = methodName;
		this.projectId = projectId;
		this.sessionId = sessionId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.env = env;
		this.browser = browser;
		this.authorId = authorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, methodName, statusId, startTime, endTime);
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
		Test other = (Test) obj;
		return Objects.equals(name, other.name) && Objects.equals(methodName, other.methodName)
				&& Objects.equals(statusId, other.statusId) && Objects.equals(startTime, other.startTime)
				&& Objects.equals(endTime, other.endTime);
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + ", statusId=" + statusId + ", methodName=" + methodName
				+ ", projectId=" + projectId + ", sessionId=" + sessionId + ", startTime=" + startTime + ", endTime="
				+ endTime + ", env=" + env + ", browser=" + browser + ", authorId=" + authorId + "]";
	}

}
