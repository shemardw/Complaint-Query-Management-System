package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCRUDImplement implements StudentCRUD {
    private DataSource dataSource;

    public StudentCRUDImplement() {
        this.dataSource = DatabaseConnector.getDataSource();
    }

    // implements addStudent method
    @Override
    public boolean addStudent(Student student) {
        // declare query as a string and assign SQL argument to it
        String query = "INSERT INTO student (student_id, first_name, last_name, email, contact_number, password) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = dataSource.getConnection(); // retrieves a connection object from the data source
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // add actual values that will replace placeholder
            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getContactNumber());
            preparedStatement.setString(6, student.getPassword());

            // assign number of rows affected by the query
            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) { // if number of affected rows <= 0
            e.printStackTrace();
            return false;
        }
    }
		
    // implement getStudentById method
    @Override
    public Student getStudentById(String studentId) {
    	// defines query as a string and assign SQL argument to it
        String query = "SELECT * FROM student WHERE student_id = ?";
        Student student = null; // initial student object

        try (Connection connection = this.dataSource.getConnection(); // create connection object to connect to db
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);

            // execute the query || store in object result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // retrieves information about student
            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String contactNumber = resultSet.getString("contact_number");
                String password = resultSet.getString("password");

                // add students info to new student object
                student = new Student(studentId, firstName, lastName, email, contactNumber, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // returns student info
        return student;
    }

    // implement getAllStudents method
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

        	// loop query until all students are retrieved
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String contactNumber = resultSet.getString("contact_number");
                String password = resultSet.getString("password");

                Student student = new Student(studentId, firstName, lastName, email, contactNumber, password);
                students.add(student);// student to array students
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // implement updateStudent
    @Override
    public boolean updateStudent(Student student) {
        String query = "UPDATE student SET first_name = ?, last_name = ?, email = ?, contact_number = ?, password = ? WHERE student_id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getContactNumber());
            preparedStatement.setString(5, student.getPassword());
            preparedStatement.setString(6, student.getStudentId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // deleteStudent
    @Override
    public boolean deleteStudent(String studentId) {
        String query = "DELETE FROM student WHERE student_id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
	// Test methods
	public static void main(String[] args) {
	 
	 	// addStudent
	     StudentCRUD studentCRUD = new StudentCRUDImplement();

	    Student newStudent = new Student("001", "Cassandra", "Cain", "cassie.cain@gmail.com", "8767449900", "Cass!ePassword");
	    boolean added = studentCRUD.addStudent(newStudent);
	    System.out.println("Added student: " + added);
	    
	    Student newStudent = new Student("002", "Debbie", "Moore", "mooredb@gmail.com", "8766658100", "Password123");
		boolean added = studentCRUD.addStudent(newStudent);
		System.out.println("Added student: " + added);
	     
	    Student newStudent = new Student("003", "Eddy", "Wang", "wangman@gmail.com", "8768640965", "Password123!");
		boolean added = studentCRUD.addStudent(newStudent);
		System.out.println("Added student: " + added);
	    
	    //getStudentById
	    Student retrievedStudent = studentCRUD.getStudentById("001");
	    System.out.println("Retrieved student: " + retrievedStudent);
	    
	    // getAllStudents
	    List<Student> allStudents = studentCRUD.getAllStudents();
	    System.out.println("All students:");
	    for (Student s : allStudents) {
	    System.out.println(s.getStudentId() + " " + s.getFirstName() + " " + s.getLastName());
	    }
	    
	    // updateStudent
	    Student updatedStudent = new Student("001", "Cassandra", "Cain", "cassie.cain@gmail.com", "8760001111", "Cass!ePassword");
	    boolean updated = studentCRUD.updateStudent(updatedStudent);
	    System.out.println("Updated student: " + updated);
	    
	    Student retrievedStudent2 = studentCRUD.getStudentById("001");
	    System.out.println("Retrieved student: " + retrievedStudent2);
	    
	    // deleteStudent
	    boolean deleted = studentCRUD.deleteStudent("001");
	    System.out.println("Deleted student: " + deleted);
	}*/
    
}
