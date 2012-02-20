package ru.iteco.trainings.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class EmployeeDao implements EmployeeCRUD {
	
	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		DataSource dataSource = transactionManager.getDataSource();
	    jdbcTemplate = new JdbcTemplate(dataSource);
	    namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public void create(Employee emp) {
		this.jdbcTemplate.update(sqlQueryToCreateEmp,
				emp.getNumber(), emp.getName(), emp.getJobTitle());
	}
	
	public Employee retrieve(int empNumber) {
		Employee emp = this.jdbcTemplate.queryForObject(
				sqlQueryToRetrieveEmp,
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
		this.jdbcTemplate.update(sqlQueryToUpdateEmp,
				emp.getNumber(), emp.getName(), emp.getJobTitle(), emp.getNumber());
	}
	
	public void delete(Employee emp) {
		this.jdbcTemplate.update(sqlQueryToDeleteEmp,
				emp.getNumber());
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
	
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("src\\context.xml");
		EmployeeDao empDao = (EmployeeDao)context.getBean("employeeDao");
		Employee newEmp = new Employee(111, "222", "333");
		empDao.delete(newEmp);
		
	}

	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private String sqlQueryToCreateEmp;
	private String sqlQueryToRetrieveEmp;
	private String sqlQueryToUpdateEmp;
	private String sqlQueryToDeleteEmp;
	
	public void setSqlQueryToCreateEmp(String sqlQueryToCreateEmp) {
		this.sqlQueryToCreateEmp = sqlQueryToCreateEmp;
	}

	public void setSqlQueryToRetrieveEmp(String sqlQueryToRetrieveEmp) {
		this.sqlQueryToRetrieveEmp = sqlQueryToRetrieveEmp;
	}

	public void setSqlQueryToUpdateEmp(String sqlQueryToUpdateEmp) {
		this.sqlQueryToUpdateEmp = sqlQueryToUpdateEmp;
	}

	public void setSqlQueryToDeleteEmp(String sqlQueryToDeleteEmp) {
		this.sqlQueryToDeleteEmp = sqlQueryToDeleteEmp;
	}
	
}
