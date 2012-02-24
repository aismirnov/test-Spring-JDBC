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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((admissionDate == null) ? 0 : admissionDate.hashCode());
		result = prime * result
				+ ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		if (admissionDate == null) {
			if (other.admissionDate != null) {
				return false;
			}
		} else if (!admissionDate.equals(other.admissionDate)) {
			return false;
		}
		if (jobTitle == null) {
			if (other.jobTitle != null) {
				return false;
			}
		} else if (!jobTitle.equals(other.jobTitle)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}	
}
