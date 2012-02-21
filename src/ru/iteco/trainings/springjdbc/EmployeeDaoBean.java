package ru.iteco.trainings.springjdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class EmployeeDaoBean implements EmployeeDao {
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private String sqlQueryToCreateEmp;
	private String sqlQueryToRetrieveEmp;
	private String sqlQueryToUpdateEmp;
	private String sqlQueryToDeleteEmp;
	private String sqlQueryToFindEmp;
	private String sqlQueryToGetEmpList;

	public void setDataSource(DataSource dataSource) {
	    jdbcTemplate = new JdbcTemplate(dataSource);
	    namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int create(Employee emp) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String empName = emp.getName();
		final String empJob = emp.getJobTitle();
		final Date empDate = emp.getAdmissionDate();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sqlQueryToCreateEmp, new String[] {"EMPNO"});
		            ps.setString(1, empName);
		            ps.setString(2, empJob);
		            ps.setString(3, empDate.toString());
		            return ps;
		        }
		    },
		    keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public Employee retrieve(int empNumber) {
		if(this.jdbcTemplate.queryForInt(
				"select count(*) from Employee where EMPNO = ?", empNumber) == 0) {
			return null;
		}
		
		Employee emp = this.jdbcTemplate.queryForObject(
				sqlQueryToRetrieveEmp,
				new Object[]{empNumber}, employeeRowMapper.INSTANCE);
		return emp;
	}
	
	public void update(Employee emp) {
		this.jdbcTemplate.update(sqlQueryToUpdateEmp,
				emp.getName(), emp.getJobTitle(), emp.getAdmissionDate().toString(), emp.getNumber());
	}
	
	public void delete(Employee emp) {
		this.jdbcTemplate.update(sqlQueryToDeleteEmp,
				emp.getNumber());
	}
	
	
	public List<Employee> getEmployeeList() {
		List<Employee> employees = this.jdbcTemplate.query(
				sqlQueryToGetEmpList, employeeRowMapper.INSTANCE);
		return employees;
	}
	
	public List<Employee> findEmployees(String name) {
		List<Employee> employees = this.jdbcTemplate.query(
				sqlQueryToFindEmp, employeeRowMapper.INSTANCE, name);
		return employees;
	}

	
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

	public void setSqlQueryToFindEmp(String sqlQueryToFindEmp) {
		this.sqlQueryToFindEmp = sqlQueryToFindEmp;
	}
	
	public void setSqlQueryToGetEmpList(String sqlQueryToGetEmpList) {
		this.sqlQueryToGetEmpList = sqlQueryToGetEmpList;
	}
	
	
	private enum employeeRowMapper implements RowMapper<Employee> {
		INSTANCE;
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employee = new Employee();
			employee.setNumber(rs.getInt("EMPNO"));
			employee.setName(rs.getString("ENAME"));
			employee.setJobTitle(rs.getString("JOB_TITLE"));
			employee.setAdmissionDate(rs.getDate("ADMISSION_DATE"));
			
			return employee;
		}
	}
}
