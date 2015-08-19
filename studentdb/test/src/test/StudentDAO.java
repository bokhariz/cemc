package test;
import java.util.ArrayList;

public interface StudentDAO {
	
	boolean insertStudent(Student student);
	ArrayList<Student> findStudentLastName(String lastName);
 
}
