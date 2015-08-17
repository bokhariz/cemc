package test;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Student {
	private String lastName;
	private String firstName;
	private Calendar birthDate;
	private int studentID;
	static int nextStudentID=0;
	
public Student(String aFirstName, String aLastName, Calendar aBirthDate ){
	studentID = nextID();
	lastName = aLastName;
	firstName = aFirstName;
	birthDate = aBirthDate;
}

public Student(int id, String aFirstName, String aLastName, Calendar aBirthDate ){
	studentID = id;
	lastName = aLastName;
	firstName = aFirstName;
	birthDate = aBirthDate;
}

private static int nextID(){
	nextStudentID++;
	return nextStudentID;
}

public int getID(){
	return studentID;
}

public String getFirstName(){
	return firstName;
}

public String getLastName(){
	return lastName;
}

public Calendar getDate(){
	return birthDate;
}


private int getAge(){
	Calendar cal = new GregorianCalendar();
	int cYear = cal.get(Calendar.YEAR);
	int cMonth = cal.get(Calendar.MONTH);
	int cDay = cal.get(Calendar.DAY_OF_MONTH);
	
	int bYear = birthDate.get(Calendar.YEAR);
	int bMonth = birthDate.get(Calendar.MONTH);
	int bDay = birthDate.get(Calendar.DAY_OF_MONTH);
	
	int studentAge = cYear-bYear+1; // determine initial age (assume birthday has passed)
	if (bMonth<=cMonth) {
		if ((bMonth==cMonth) && (bDay <cDay)) { // same month but not the day yet
			studentAge--; 
			} 
		else { studentAge--; } // not the same month yet
	}
	
	return studentAge;
}

public String toString(){
	String fmtName = "";
	fmtName = "ID: " + studentID + "  " + firstName.trim() + ", " + lastName.trim() +
			" Age is: " + getAge();
	fmtName += "\nYear : " + birthDate.get(Calendar.YEAR) + "  Month : " + (birthDate.get(Calendar.MONTH)+ 1);
	fmtName += "  Day : " + birthDate.get(Calendar.DAY_OF_MONTH);
	return fmtName;
}
}
