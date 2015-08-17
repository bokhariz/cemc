package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StudentDBDriver {

	public static void main(String[] args) throws ParseException, SQLException {
	
		String lastName = "";
		String firstName = "";
		String rawDate ="";
		StudentDB sDB = new StudentDB();
		GregorianCalendar bDate = new GregorianCalendar(); // default object
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );
		
		Scanner in = new Scanner (System.in);
		System.out.print("How many students?  ");
		int numStudents= in.nextInt();
		in.nextLine(); // clear buffer
		
		for (int i=0; i<numStudents; i++){
			// obtain user input 
			System.out.print ("First Name: ");
			firstName = in.nextLine();
			System.out.print ("Last Name: ");
			lastName = in.nextLine();
			System.out.print ("Birth Date (yyyy/MM/dd): ");
			rawDate = in.nextLine();
			date = format.parse(rawDate); // format the date to ensure it is correct
			bDate.setTime( date ); // Convert the formatted date to a calendar object
			System.out.println("Creating a student");
			
			// create a new student object
			Student s = new Student(firstName, lastName, bDate);
			
			// insert the student object in the database table
			sDB.insertStudent(s);
			
			// display the student object for validation by user
			System.out.println(s);
		
		}
		
		System.out.println("Find a student by last name: ");
		String lname = in.nextLine();
		
		ArrayList <Student> students = sDB.findStudentLastName(lname);
		
		for (Student student : students){
			System.out.println("**Students found**");
			System.out.println(student);
		}
		
		
		in.close();
	}

}
