package server;

public class Student {

	// local variable of student class
	private String studentId;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;
	private String password;
	
	// constructors
	
	// default
	public Student() {
		studentId = "";
		firstName = "";
		lastName = "";
		email = "";
		contactNumber = "";
		password = "";
	}
	
	// primary
	public Student(String studentId, String firstName, String lastName, String email, String contactNumber, String password) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.password = password;
	}

	// copy
	public Student(Student student) {
		studentId = student.studentId;
		firstName = student.firstName;
		lastName = student.lastName;
		email = student.email;
		contactNumber = student.contactNumber;
		password = student.password;
	}

	// getters and setter
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//toString
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", contactNumber=" + contactNumber + ", password=" + password + "]";
	}
	
}
