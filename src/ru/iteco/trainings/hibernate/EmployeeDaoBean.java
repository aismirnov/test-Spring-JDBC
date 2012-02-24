package ru.iteco.trainings.hibernate;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.iteco.trainings.common.Employee;
import ru.iteco.trainings.common.EmployeeDao;

@Repository("userDao")
@Transactional
public class EmployeeDaoBean implements EmployeeDao {
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Transactional(readOnly = false)
	public void create(Employee emp) {
		hibernateTemplate.save(emp);
	}
	
	public Employee retrieve(int empNumber) {
		return hibernateTemplate.get(Employee.class, empNumber);
	}

	@Transactional(readOnly = false)
	public void update(Employee emp) {
		hibernateTemplate.update(emp);
	}

	@Transactional(readOnly = false)
	public void delete(Employee emp) {
		hibernateTemplate.delete(emp);
	}
}
