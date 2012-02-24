package ru.iteco.trainings.hibernate;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

import ru.iteco.trainings.common.Employee;
import ru.iteco.trainings.common.EmployeeDao;

public class EmployeeDaoBean implements EmployeeDao {
	private Session session;
	
	public EmployeeDaoBean() {
		AnnotationConfiguration config = new AnnotationConfiguration().configure();
		config.addAnnotatedClass(Employee.class);
		
		SessionFactory sf = config.buildSessionFactory();
		session = sf.getCurrentSession();
	}

	public int create(Employee emp) {
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		return emp.getNumber();
	}

	public Employee retrieve(int empNumber) {
		session.beginTransaction();
		Employee emp = (Employee) session.get(Employee.class, empNumber);
		session.getTransaction().commit();
		return emp;
	}

	public void update(Employee emp) {
		session.beginTransaction();
		session.update(emp);
		session.getTransaction().commit();
	}

	public void delete(Employee emp) {
		session.beginTransaction();
		session.delete(emp);
		session.getTransaction().commit();
	}
}
