package test;
import java.sql.SQLException;

public interface StudentDAO {
	//List<Student> findAll();
    //List<Student> findById();
    //List<Student> findByName();
    boolean insertStudent(Student student) throws SQLException;
    //boolean updateStudent(Student student);
    //boolean deleteStudent(Student student);
}
