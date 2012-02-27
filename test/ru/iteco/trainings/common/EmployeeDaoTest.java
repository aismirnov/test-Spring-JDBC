package ru.iteco.trainings.common;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.sql.Date;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

public class EmployeeDaoTest {
	protected EmployeeDao empDao;
	
	@Test
	public void testCreate() {
		Employee newEmp = getSampleEmployee();
		createEmployee(newEmp);
		assertThat(empDao.retrieve(newEmp.getNumber()), matches(newEmp));
	}
	
	@Test
	public void testRetrieve() {
		Employee newEmp = getSampleEmployee();
		createEmployee(newEmp);
		assertThat(empDao.retrieve(newEmp.getNumber()), matches(newEmp));
	}
	
	@Test
	public void testUpdate() {
		Employee newEmp = getSampleEmployee();
		createEmployee(newEmp);
		
		newEmp.setName("New Name");
		
		empDao.update(newEmp);
		assertThat(empDao.retrieve(newEmp.getNumber()), matches(newEmp));
	}

	@Test
	public void testDelete() {
		Employee newEmp = getSampleEmployee();
		createEmployee(newEmp);
		deleteEmployee(newEmp);
		assertNull(empDao.retrieve(newEmp.getNumber()));
	}
	
	protected Employee getSampleEmployee() {
		return new Employee("testName", "testJob", Date.valueOf("2012-02-27"));
	}
	
	protected Employee getSampleEmployee(Date date) {
		return new Employee("testName", "testJob", date);
	}

	protected void createEmployee(Employee newEmp) {
		empDao.create(newEmp);
	}
	
	private void deleteEmployee(Employee newEmp) {
		empDao.delete(newEmp);
	}

	protected static Matcher<Employee> matches(final Employee expected){

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
}
