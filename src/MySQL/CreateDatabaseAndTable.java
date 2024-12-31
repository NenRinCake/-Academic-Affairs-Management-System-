package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseAndTable {
	public CreateDatabaseAndTable() throws Exception{
		//注册驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//连接已有数据库
		String url = "jdbc:mysql://localhost:3306/sys";
		String user = "root";
		String password = "****";
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt = con.createStatement();
		//创建数据库并连接      
		try {
		    stmt.executeUpdate("create database nrc");
		}
		catch(SQLException e) {
		    //System.out.println(e);
		}
		stmt.close();
		con.close();		
		url = "jdbc:mysql://localhost:3306/nrc";
		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement();
		//创建表
		String createStudentLsit = "Create Table StudentList\r\n"
        		+ "(\r\n"
        		+ "ID char(10) NOT NULL PRIMARY KEY,\r\n"
        		+ "name char(8) NOT NULL,\r\n"
        		+ "gender char(2) NOT NULL,\r\n"
        		+ "birthday char(20) NOT NULL,\r\n"
        		+ "ethnic char(10) NOT NULL,\r\n"
        		+ "institute char(10) NOT NULL,\r\n"
        		+ "dept char(10) NOT NULL,\r\n"
        		+ "schoolClass char(10) NOT NULL,\r\n"
        		+ "identity char(10) NOT NULL\r\n"
        		+ ");";
		String createTeacherList = "Create Table TeacherList\r\n"
				+ "(\r\n"
				+ "ID char(10) NOT NULL PRIMARY KEY,\r\n"
				+ "name char(8) NOT NULL,\r\n"
				+ "gender char(2) NOT NULL,\r\n"
				+ "birthday char(20) NOT NULL,\r\n"
				+ "institute char(10) NOT NULL,\r\n"
				+ "title char(10) NOT NULL,\r\n"
				+ "identity char(10) NOT NULL\r\n"
				+ ");";
		String createPasswordList = "Create Table PasswordList\r\n"
				+ "(\r\n"
				+ "name char(10) NOT NULL PRIMARY KEY,\r\n"
				+ "password char(20) NOT NULL,\r\n"
				+ "identity char(10) NOT NULL\r\n"
				+ ");";
		String createCourseList = "Create Table CourseList\r\n"
				+ "(\r\n"
				+ "name char(10) NOT NULL,\r\n"
				+ "teacher char(8) NOT NULL,\r\n"
				+ "schoolClass char(10) NOT NULL,\r\n"
				+ "credit int NOT NULL,\r\n"
				+ "time int NOT NULL,\r\n"
				+ "PRIMARY KEY (name,schoolClass)\r\n"
				+ ");";
		String createGradeList = "Create Table GradeList\r\n"
				+ "(\r\n"
				+ "courseName char(10) NOT NULL,\r\n"
				+ "studentName char(8) NOT NULL,\r\n"
				+ "studentID char(10) NOT NULL,\r\n"
				+ "grade int NOT NULL,\r\n"
				+ "comment char(20) NULL,\r\n"
				+ "PRIMARY KEY (courseName,studentID)\r\n"
				+ ");";
		String insertAdministrator1 = "INSERT INTO PasswordList (name,password,identity)\r\n"
				+ "VALUES ('NenRinCake','0721','管  理  员');";
		String insertAdministrator2 = "INSERT INTO PasswordList (name,password,identity)\r\n"
				+ "VALUES ('Shadow','****','管  理  员');";
		
		try {
		    stmt.executeUpdate(createStudentLsit);
		}
		catch(SQLException e) {
		    //System.out.println(e);
		}		
		
		try {
		    stmt.executeUpdate(createTeacherList);
		}
		catch(SQLException e) {
		    //System.out.println(e);
		}		
		
		try {
		    stmt.executeUpdate(createPasswordList);
		}
		catch(SQLException e) {
		    //System.out.println(e);
		}		
		
		try {
		    stmt.executeUpdate(createCourseList);
		}
		catch(SQLException e) {
		    //System.out.println(e);
		}		
		
		try {
		    stmt.executeUpdate(createGradeList);
		}
		catch(SQLException e) {
		    //System.out.println(e);
		}
		
		try {
			stmt.execute(insertAdministrator1);
		}
		catch(SQLException e) {
			//System.out.println(e);
		}
		
		try {
			stmt.execute(insertAdministrator2);
		}
		catch(SQLException e) {
			//System.out.println(e);
		}
		
		stmt.close();
		con.close();
	}
}
