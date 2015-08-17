package test;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StudentDB implements StudentDAO {
	final static String tableName="APP.STUDENTS";
	final static String url="jdbc:derby:C:\\Users\\Lenovo\\Documents\\cemc2015\\databases\\names;create=true";
	static Connection con = null;
	static Statement st = null;
	static ResultSet rs = null;
	
	public boolean insertStudent(Student student) {
		boolean wasRowInserted = false;
		int num=0; // track if row was inserted or not
		
		try {
			
		con = DriverManager.getConnection(url);
		st = con.createStatement();
		
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?, ?,?)");
		pstmt.setInt(1, student.getID());
		pstmt.setString(2, student.getFirstName());
		pstmt.setString(3, student.getLastName());
		pstmt.setDate(4, convertJavaDateToSqlDate(student.getDate().getTime()));
		
		num = pstmt.executeUpdate();
		if (num == 1) { wasRowInserted = true; } // num is the number of rows inserted
		
		con.close(); // close connection and resources for database
		}
		
		catch (SQLException se){
			System.out.println("Database Error has occurred.");
			System.out.println(se.getMessage());
		}
		
		return wasRowInserted;
	}
	
	private static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
	    return new java.sql.Date(date.getTime());
	}
	
	public ArrayList<Student> findStudentLastName(String lastName) {
		
		ResultSet rs =null;
		int id = 0;
		String fname = "";
		String lname = "";
		Calendar bdate = new GregorianCalendar();
		Date date = null;
		ArrayList<Student> students = new ArrayList<Student>();
		
		
		try {
			
		con = DriverManager.getConnection(url);
		st = con.createStatement();
		 
		PreparedStatement pstmt = con.prepareStatement("SELECT id, fname, lname,bdate from " + tableName + " where lname = ?");
		pstmt.setString(1, lastName);
				
		rs = pstmt.executeQuery();
		
		while (rs.next()){
			id = rs.getInt("ID");
			fname = rs.getString("FNAME");
			lname = rs.getString("LNAME");
			bdate = convertSQLDateToJavaDate(rs.getDate("BDATE"));
			Student s = new Student (id, fname, lname, bdate);
			students.add(s);
		}
		
		con.close(); // close connection and resources for database
		
		}
		
		catch (SQLException se){
			System.out.println("Database Error has occurred.");
			System.out.println(se.getMessage());
		}
		return students;
		
	}
	
	private static Calendar convertSQLDateToJavaDate(java.util.Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
	    return cal;
	}
}
