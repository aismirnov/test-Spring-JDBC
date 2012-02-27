package ru.iteco.trainings.common;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Dept")
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false)
	private int id;
	
	@Column(name = "NAME", nullable=false)
	@Size(max=200)
	private String name;

	public Department(String name) {
		this.name = name;
	}
	
	public Department() {
		
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Department)) {
			return false;
		}
		Department other = (Department) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
}
