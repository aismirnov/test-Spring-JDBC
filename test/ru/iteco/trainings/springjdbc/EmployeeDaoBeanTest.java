package ru.iteco.trainings.springjdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional

public class EmployeeDaoBeanTest {
	private static Employee emp1, emp2, emp3;
	private static EmployeeDaoBean empDao;
	
	@BeforeClass
	public static void init() {
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:context.xml");
		empDao = (EmployeeDaoBean)context.getBean("employeeDaoBean");
		/*
		emp1 = new Employee("John Smith", "Clerk", new Date("21.02.2012"));
		emp2 = new Employee("Joe Allen", "Salesman", new Date("21.02.2012"));
		emp3 = new Employee("Mary Lou", "Director", new Date("21.02.2012")); */
	}
	
	@Test
	public void testRetrieve() {
		Employee newEmployee = getSampleEmployee();
		int newEmpNumber = createEmployee(newEmployee);
		assertEquals(newEmployee, empDao.retrieve(newEmpNumber));
	}
	
	@Test
	public void testCreate() {
		Employee newEmployee = getSampleEmployee();
		int newEmpNumber = createEmployee(newEmployee);
		assertEquals(newEmployee, empDao.retrieve(newEmpNumber));
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
		assertEquals(newEmp, empDao.retrieve(newEmpNumber));
	}
	
	@Ignore
	@Test
	public void testFindEmployees() {
		final String sampleEmployeeName = "testName";
		Employee newEmp = getSampleEmployee();
		newEmp.setName(sampleEmployeeName);
		createEmployee(newEmp);
		List<Employee> empList = Arrays.asList(newEmp);
		assertEquals(empList, empDao.findEmployees(sampleEmployeeName));
		deleteEmployee(newEmp);
	}
	
	private Employee getSampleEmployee() {
		return new Employee("testName", "testJob", Date.valueOf("2012-02-21"));
	}
	
	private int createEmployee(Employee newEmp) {
		return empDao.create(newEmp);
	}
	
	private void deleteEmployee(Employee newEmp) {
		empDao.delete(newEmp);
	}

}
