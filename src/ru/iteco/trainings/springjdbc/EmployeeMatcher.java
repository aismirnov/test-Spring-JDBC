package ru.iteco.trainings.springjdbc;

import java.sql.Date;

public class EmployeeMatcher {
	private String nameToFind;
	private String jobTitleToFind;
	private Date startDate;
	private Date endDate;
	
	
	public String getNameToFind() {
		return nameToFind;
	}
	public void setNameToFind(String nameToFind) {
		this.nameToFind = nameToFind;
	}
	public String getJobTitleToFind() {
		return jobTitleToFind;
	}
	public void setJobTitleToFind(String jobTitleToFind) {
		this.jobTitleToFind = jobTitleToFind;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
