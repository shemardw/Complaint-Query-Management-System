package server;

import java.util.List;

public interface StudentCRUD {
	
	// the CRUD operation for student
	boolean addStudent(Student student); // add new student to database
	Student getStudentById(String studentId); // retrieves student record based on studentId
	List<Student> getAllStudents(); // retrieves all student records from the database
	boolean updateStudent(Student student); // updates student record
	boolean deleteStudent(String studentId); // deletes student record based on studentId
	
}
