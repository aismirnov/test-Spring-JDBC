package ru.iteco.trainings.springjdbc;

import java.sql.*;

public class EmployeeDao {

	/**
	 * @param args
	 */
	public static void main(String argv[]) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Lesson22");
			String sqlQuery = "SELECT * from Employee";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			System.out.println("Using Statement:");
			while (rs.next()) {
				int empNo = rs.getInt("EMPNO");
				String eName = rs.getString("ENAME");
				String job = rs.getString("JOB_TITLE");
				System.out.println(empNo + ", " + eName + ", " + job);
			}
		} catch (SQLException se) {
			System.out.println("SQLERROR: " + se.getMessage() + " code: "
					+ se.getErrorCode());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
