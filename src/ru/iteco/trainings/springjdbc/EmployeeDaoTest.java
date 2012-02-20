package ru.iteco.trainings.springjdbc;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional */

public class EmployeeDaoTest {
	static ApplicationContext context;
	static EmployeeDao empDao;
	
	@BeforeClass
	public static void init() {
		context = new FileSystemXmlApplicationContext("src\\context.xml");
		empDao = (EmployeeDao)context.getBean("employeeDao");
	}
	
	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieve() {
		
		Employee emp = new Employee(7369, "John Smith", "Clerk");
		assertEquals(emp, empDao.retrieve(7369));
		
		emp = new Employee(7499, "Joe Allen", "Salesman");
		assertEquals(emp, empDao.retrieve(7499));
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
