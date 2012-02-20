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
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional

public class EmployeeDaoTest {
	private static EmployeeDao empDao;
	private static Employee emp1, emp2, emp3;
	private static Employee newEmp;
	
	@BeforeClass
	public static void init() {
		ApplicationContext context = new FileSystemXmlApplicationContext("src\\context.xml");
		empDao = (EmployeeDao)context.getBean("employeeDao");
		
		emp1 = new Employee(7369, "John Smith", "Clerk");
		emp2 = new Employee(7499, "Joe Allen", "Salesman");
		emp3 = new Employee(7521, "Mary Lou", "Director");
		newEmp = new Employee(111, "222", "333");
	}
	
	@Test
	public void testRetrieve() {
		assertEquals(emp1, empDao.retrieve(7369));
		assertEquals(emp2, empDao.retrieve(7499));
	}
	
	@Test
	@Rollback(true)
	public void testCreate() {
		empDao.create(newEmp);
		assertEquals(newEmp, empDao.retrieve(newEmp.getNumber()));
	}
	
	@Test
	public void testDelete() {
		empDao.delete(newEmp);
		assertTrue(true);
	}
	
	@Test
	public void testGetEmployeeList() {
		List<Employee> empList = Arrays.asList(emp1, emp2, emp3);
		assertEquals(empList, empDao.getEmployeeList());
	}

	@Test
	public void testUpdate() {
		emp1.setName("New Name");
		empDao.update(emp1);
		assertEquals(emp1, empDao.retrieve(emp1.getNumber()));
		
		emp1.setName("John Smith");
		empDao.update(emp1);
		assertEquals(emp1, empDao.retrieve(emp1.getNumber()));
	}

}
