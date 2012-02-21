package ru.iteco.trainings.springjdbc;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class EmployeeDaoBeanTest {
	@Resource(name="employeeDaoBean")
	private EmployeeDaoBean empDao;
	
	@Test
	public void testRetrieve() {
		Employee newEmp = getSampleEmployee();
		int newEmpNumber = createEmployee(newEmp);
		assertTrue(equal(newEmp, empDao.retrieve(newEmpNumber)));
	}
	
	@Test
	public void testCreate() {
		Employee newEmp = getSampleEmployee();
		int newEmpNumber = createEmployee(newEmp);
		assertTrue(equal(newEmp, empDao.retrieve(newEmpNumber)));
	}
	
	@Test
	public void testDelete() {
		Employee newEmp = getSampleEmployee();
		int newEmpNumber = createEmployee(newEmp);
		newEmp.setNumber(newEmpNumber);
		deleteEmployee(newEmp);
		assertNull(empDao.retrieve(newEmpNumber));
	}
	
	@Test
	public void testUpdate() {
		Employee newEmp = getSampleEmployee();
		int newEmpNumber = createEmployee(newEmp);
		
		newEmp.setName("New Name");
		newEmp.setNumber(newEmpNumber);
		
		empDao.update(newEmp);
		assertTrue(equal(newEmp, empDao.retrieve(newEmpNumber)));
	}
	
	@Test
	@Ignore
	public void testFindEmployees() {
		//this test is not implemented yet
		Employee emp1 = getSampleEmployee("2010-01-01");
		Employee emp2 = getSampleEmployee("2012-01-01");
		List<Employee> empListExpected =
			Arrays.asList(emp1, emp2);
		List<Employee> empList = empDao.findEmployees(new EmployeeMatcherCreator() {
			public EmployeeMatcher createEmployeeMatcher() {
				EmployeeMatcher empMatcher = new EmployeeMatcher();
				empMatcher.setEndDate(Date.valueOf("2010-02-01"));
				empMatcher.setStartDate(Date.valueOf("2010-01-01"));
				return empMatcher;
			}
		});
	}
	
	private Employee getSampleEmployee() {
		return new Employee("testName", "testJob", Date.valueOf("2012-02-21"));
	}
	
	private Employee getSampleEmployee(String date) {
		return new Employee("testName", "testJob", Date.valueOf(date));
	}
	
	private boolean equal(Employee emp1, Employee emp2) {
		if(emp1.getName() == null || 
				emp1.getJobTitle() == null ||
				emp1.getAdmissionDate() == null) {
			return false;
		}
		if(emp1.getName().equals(emp2.getName()) &&
				emp1.getJobTitle().equals(emp2.getJobTitle()) &&
				emp1.getAdmissionDate().equals(emp2.getAdmissionDate())) {
			return true;
		}
		return false;
	}
	
	private int createEmployee(Employee newEmp) {
		int newEmpId = empDao.create(newEmp);
		newEmp.setNumber(newEmpId);
		return newEmpId;
	}
	
	private void deleteEmployee(Employee newEmp) {
		empDao.delete(newEmp);
	}

}
