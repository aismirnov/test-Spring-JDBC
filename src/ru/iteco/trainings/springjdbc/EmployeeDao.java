package ru.iteco.trainings.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class EmployeeDao implements EmployeeCRUD {
	
	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		DataSource dataSource = transactionManager.getDataSource();
	    jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void create(Employee emp) {
		
	}
	
	public Employee retrieve(int empNumber) {
		Employee emp = this.jdbcTemplate.queryForObject(
				sqlQueryForRetrieveEmpByNumber,
		        new Object[]{empNumber},
		        new RowMapper<Employee>() {
		        	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
						Employee employee = new Employee();
						employee.setNumber(rs.getInt("EMPNO"));
						employee.setName(rs.getString("ENAME"));
						employee.setJobTitle(rs.getString("JOB_TITLE"));
						return employee;
					}
		        });
		return emp;
	}
	
	public void update(Employee emp) {
		
	}
	
	public void delete(Employee emp) {
		
	}
	
	
	public List<Employee> getEmployeeList() {
		List<Employee> employees = this.jdbcTemplate.query(
				"select EMPNO, ENAME, JOB_TITLE from Employee",
				new RowMapper<Employee>() {
					public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
						Employee employee = new Employee();
						employee.setNumber(rs.getInt("EMPNO"));
						employee.setName(rs.getString("ENAME"));
						employee.setJobTitle(rs.getString("JOB_TITLE"));
						return employee;
					}
				});
		return employees;
	}

	public static void main(String argv[]) {
		ApplicationContext context = new FileSystemXmlApplicationContext("src\\context.xml");
		EmployeeDao ed = (EmployeeDao)context.getBean("employeeDao");
		List<Employee> empList = ed.getEmployeeList();
		for(Employee emp: empList) {
			System.out.println(emp);
		}
		System.out.println();
		System.out.println(ed.retrieve(7369));
	}
	
	private JdbcTemplate jdbcTemplate;
	private String sqlQueryForRetrieveEmpByNumber;
	
	public void setSqlQueryForRetrieveEmpByNumber(String sqlQuery) {
		this.sqlQueryForRetrieveEmpByNumber = sqlQuery;
	}
}
