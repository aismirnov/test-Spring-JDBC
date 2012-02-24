package ru.iteco.trainings.springjdbc;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ru.iteco.trainings.common.Employee;


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
		createEmployee(newEmp);
		assertThat(empDao.retrieve(newEmp.getNumber()), matches(newEmp));
	}
	
	@Test
	public void testCreate() {
		Employee newEmp = getSampleEmployee();
		createEmployee(newEmp);
		assertThat(empDao.retrieve(newEmp.getNumber()), matches(newEmp));
	}
	
	@Test
	public void testDelete() {
		Employee newEmp = getSampleEmployee();
		createEmployee(newEmp);
		deleteEmployee(newEmp);
		assertNull(empDao.retrieve(newEmp.getNumber()));
	}
	
	@Test
	public void testUpdate() {
		Employee newEmp = getSampleEmployee();
		createEmployee(newEmp);
		
		newEmp.setName("New Name");
		
		empDao.update(newEmp);
		assertThat(empDao.retrieve(newEmp.getNumber()), matches(newEmp));
	}
	
	@Ignore
	@Test
	public void testFindEmployees() {
		Employee emp1 = getSampleEmployee("2010-01-01");
		Employee emp2 = getSampleEmployee("2011-01-01");
		createEmployee(emp1);
		createEmployee(emp2);
		List<Employee> empListExpected = Arrays.asList(emp1, emp2);
		List<Employee> empList = empDao.findEmployees(new EmployeeMatcherCreator() {
			public EmployeeMatcher createEmployeeMatcher() {
				EmployeeMatcher empMatcher = new EmployeeMatcher();
				empMatcher.setStartDate(Date.valueOf("2008-01-01"));
				empMatcher.setEndDate(Date.valueOf("2012-02-01"));
				return empMatcher;
			}
		});
		
		assertTrue(listsAreEqual(empListExpected, empList));
	}
	
	private Employee getSampleEmployee() {
		return new Employee("testName", "testJob", Date.valueOf("2012-02-21"));
	}
	
	private Employee getSampleEmployee(String date) {
		return new Employee("testName", "testJob", Date.valueOf(date));
	}
	
	public static Matcher<Employee> matches(final Employee expected){

	    return new BaseMatcher<Employee>() {

	        protected Employee theExpected = expected;

	        public boolean matches(Object o) {
	        	Employee actual = (Employee) o;
	        	
	        	boolean namesAreEqual = actual.getName().equals(theExpected.getName());
	    		boolean jobsAreEqual = actual.getJobTitle().equals(theExpected.getJobTitle());
	    		boolean datesAreEqual = actual.getAdmissionDate().equals(theExpected.getAdmissionDate());
	    		
	    		return namesAreEqual && jobsAreEqual && datesAreEqual;
	        }

	        public void describeTo(Description description) {
	            description.appendText(theExpected.toString());
	        }
	    };
	}
	
	
	private boolean listsAreEqual(List<Employee> empListExpected, List<Employee> empList) {
		Iterator<Employee> itActual = empList.iterator();
		Iterator<Employee> itExpected = empListExpected.iterator();
		return false;
	}
	
	private void createEmployee(Employee newEmp) {
		empDao.create(newEmp);
	}
	
	private void deleteEmployee(Employee newEmp) {
		empDao.delete(newEmp);
	}
}
