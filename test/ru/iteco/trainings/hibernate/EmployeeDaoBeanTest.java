package ru.iteco.trainings.hibernate;

import static org.junit.Assert.*;

import java.sql.Date;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ru.iteco.trainings.common.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:app-config.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class EmployeeDaoBeanTest extends EmployeeDaoTest {
	@Autowired
	public void setDao(EmployeeDao dao) {
		this.empDao = dao;
	}
	
	@Test
	public void testDeptRelation() {
		Employee newEmp = getSampleEmployee();
		newEmp.setDept(new Department("test Department name"));
		createEmployee(newEmp);
		
		Employee actualEmp = empDao.retrieve(newEmp.getNumber());
		assertEquals(actualEmp.getDept(), newEmp.getDept());
	}
}
