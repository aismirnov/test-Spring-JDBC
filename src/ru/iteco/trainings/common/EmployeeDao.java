package ru.iteco.trainings.common;


public interface EmployeeDao {
	int create(Employee emp);
	Employee retrieve(int empNumber);
	void update(Employee emp);
	void delete(Employee emp);
}
