package ru.iteco.trainings.springjdbc;

public interface EmployeeDao {
	void create(Employee emp);
	Employee retrieve(int empNumber);
	void update(Employee emp);
	void delete(Employee emp);
}
