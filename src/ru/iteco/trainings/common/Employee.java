package ru.iteco.trainings.common;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(name="EMPNO")
	private int number;
	
	@NotNull
	@Size(max=50)
	@Column(name="ENAME")
	private String name;
	
	@NotNull
	@Size(max=150)
	@Column(name="JOB_TITLE")
	private String jobTitle;
	
	@NotNull
	@Column(name="ADMISSION_DATE")
	private Date admissionDate;

	public Employee(String name, String jobTitle, Date admissionDate) {
		this.name = name;
		this.jobTitle = jobTitle;
		this.admissionDate = admissionDate;
	}
	
	public Employee() {
		
	}
	
	public String toString() {
		return getNumber() + " " + getName() + " " + getJobTitle() + " " + getAdmissionDate();
	}
	
	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
}
