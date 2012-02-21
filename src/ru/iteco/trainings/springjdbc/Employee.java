package ru.iteco.trainings.springjdbc;

import java.sql.Date;

public class Employee {
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String toString() {
		return getNumber() + " " + getName() + " " + getJobTitle();
	}
	
	private int number;
	private String name;
	private String jobTitle;
	private Date admissionDate;
	
	public Employee(String name, String jobTitle, Date admissionDate) {
		this.name = name;
		this.jobTitle = jobTitle;
		this.admissionDate = admissionDate;
	}
	
	public Employee() {
		
	}	
}
