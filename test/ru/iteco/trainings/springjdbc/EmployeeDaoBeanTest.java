package ru.iteco.trainings.springjdbc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional

public class EmployeeDaoBeanTest {
	private static final int SAMPLE_EMPLOYEE_NUMBER = 111;
	private static EmployeeDaoBean empDao;
	private static Employee emp1, emp2, emp3;
	private static Employee newEmp;
	
	@BeforeClass
	public static void init() {
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:context.xml");
		empDao = (EmployeeDaoBean)context.getBean("employeeDaoBean");
		
		emp1 = new Employee(7369, "John Smith", "Clerk");
		emp2 = new Employee(7499, "Joe Allen", "Salesman");
		emp3 = new Employee(7521, "Mary Lou", "Director");
	}
	
	@Test
	public void testRetrieve() {
		Employee newEmployee = getSampleEmployee(SAMPLE_EMPLOYEE_NUMBER);
		createEmployee(newEmployee);
		assertEquals(newEmployee, empDao.retrieve(newEmployee.getNumber()));
		deleteEmployee(newEmployee);
	}
	
	@Test
	public void testCreate() {
		Employee newEmp = getSampleEmployee(SAMPLE_EMPLOYEE_NUMBER);
		createEmployee(newEmp);
		assertEquals(newEmp, empDao.retrieve(newEmp.getNumber()));
		deleteEmployee(newEmp);
	}
	
	private Employee getSampleEmployee(Integer id) {
		return new Employee(id, "222", "333");
	}
	
	@Test
	public void testGetEmployeeList() {
		List<Employee> empList = Arrays.asList(emp1, emp2, emp3);
		assertEquals(empList, empDao.getEmployeeList());
	}
	
	@Test
	public void testDelete() {
		Employee newEmp = getSampleEmployee(SAMPLE_EMPLOYEE_NUMBER);
		createEmployee(newEmp);
		assertEquals(newEmp, empDao.retrieve(newEmp.getNumber()));
		deleteEmployee(newEmp);
	}
	
	@Test
	public void testUpdate() {
		Employee newEmp = getSampleEmployee(SAMPLE_EMPLOYEE_NUMBER);
		createEmployee(newEmp);
		newEmp.setName("New Name");
		empDao.update(newEmp);
		assertEquals(newEmp, empDao.retrieve(newEmp.getNumber()));
		deleteEmployee(newEmp);
	}
	
	
	private void createEmployee(Employee newEmp) {
		empDao.create(newEmp);
	}
	
	private void deleteEmployee(Employee newEmp) {
		empDao.delete(newEmp);
	}

}
