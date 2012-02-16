package ru.iteco.trainings.springjdbc;

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
	public String toString() {
		return getNumber() + " " + getName() + " " + getJobTitle();
	}
	
	private int number;
	private String name;
	private String jobTitle;
}
