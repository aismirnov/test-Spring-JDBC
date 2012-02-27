package ru.iteco.trainings.common;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false)
	private int number;
	
	@Column(name="NAME", nullable=false)
	@Size(max=50)
	private String name;
	
	@Column(name="JOB_TITLE", nullable=false)
	@Size(max=150)
	private String jobTitle;
	
	@Column(name="ADMISSION_DATE", nullable=false)
	private Date admissionDate;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="DEPT_ID")
	private Department dept;

	public Employee(String name, String jobTitle, Date admissionDate) {
		this.name = name;
		this.jobTitle = jobTitle;
		this.admissionDate = admissionDate;
	}
	
	public Employee() {
		
	}
	
	public String toString() {
		return getNumber() + " " + getName() + " " + getJobTitle()
				+ " " + getAdmissionDate() + " " + getDept();
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

	public Department getDept() {
		return dept == null ? new Department("") : dept;
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

	public void setDept(Department dept) {
		this.dept = dept;
	}
}
