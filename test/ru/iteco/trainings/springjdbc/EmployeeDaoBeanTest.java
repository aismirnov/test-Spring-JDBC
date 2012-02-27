package ru.iteco.trainings.springjdbc;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ru.iteco.trainings.common.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class EmployeeDaoBeanTest extends EmployeeDaoTest {
	EmployeeDaoBean empDaoBean;
	@Autowired
	public void setDao(EmployeeDao dao) {
		this.empDao = dao;
		empDaoBean = (EmployeeDaoBean) dao;
	}
	
	@Test
	public void testFindEmployees() {
		Employee emp = getSampleEmployee();
		
		final String sampleUniqueName = Long.toString(System.currentTimeMillis());
		emp.setName(sampleUniqueName);
		createEmployee(emp);
		
		List<Employee> empList = empDaoBean.findEmployees(new EmployeeMatcherCreator() {
			public EmployeeMatcher createEmployeeMatcher() {
				EmployeeMatcher empMatcher = new EmployeeMatcher();
				empMatcher.setNameToFind(sampleUniqueName);
				return empMatcher;
			}
		});
		assertEquals(1, empList.size());
	}
}
