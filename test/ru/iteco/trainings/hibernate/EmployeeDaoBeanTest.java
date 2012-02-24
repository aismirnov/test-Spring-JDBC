package ru.iteco.trainings.hibernate;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.sql.Date;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.iteco.trainings.common.Employee;
import ru.iteco.trainings.common.EmployeeDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "/app-config.xml" })

public class EmployeeDaoBeanTest {
	private EmployeeDao empDao;
	
	@Autowired
	public void setDao(EmployeeDao dao) {
		this.empDao = dao;
	}
	
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
	
	private Employee getSampleEmployee() {
		return new Employee("testName", "testJob", Date.valueOf("2012-02-21"));
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
	
	private void createEmployee(Employee newEmp) {
		empDao.create(newEmp);
	}
	
	private void deleteEmployee(Employee newEmp) {
		empDao.delete(newEmp);
	}

}
