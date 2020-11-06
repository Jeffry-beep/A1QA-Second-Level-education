package test.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MinWorkingTestTime {

	@Id
	@Column(name = "PROJECT")
	private String project;
	@Column(name = "TEST")
	private String test;
	@Column(name = "MIN_WORKING_TIME")
	private int workingTime;

	public MinWorkingTestTime() {

	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public int getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(int workingTime) {
		this.workingTime = workingTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(project, test, workingTime);
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
		MinWorkingTestTime other = (MinWorkingTestTime) obj;
		return Objects.equals(project, other.project) && Objects.equals(test, other.test)
				&& workingTime == other.workingTime;
	}

	@Override
	public String toString() {
		return "TestMinWorkingTime [project=" + project + ", test=" + test + ", workingTime=" + workingTime + "]";
	}

}
