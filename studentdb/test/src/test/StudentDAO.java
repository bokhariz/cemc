package test;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO {
	
	boolean insertStudent(Student student) throws SQLException;
	ArrayList<Student> findStudentLastName(String lastName);
 
}
