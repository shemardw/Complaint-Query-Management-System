package server;

public class Staff {

	// local variable of student class
	private String staffId;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;
	private String password;
	private String role;
	
	// constructor
	
	// default
	public Staff() {
		staffId = "";
		firstName = "";
		lastName = "";
		email = "";
		contactNumber = "";
		password = "";
		role = "";
	}
	
	// primary
	public Staff(String staffId, String firstName, String lastName, String email, String contactNumber, String password, String role) {
		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.password = password;
		this.role = role;
	}
	
	// copy
	public Staff(Staff staff) {
		this.staffId = staff.staffId;
		this.firstName = staff.firstName;
		this.lastName = staff.lastName;
		this.email = staff.email;
		this.contactNumber = staff.contactNumber;
		this.password = staff.password;
		this.role = staff.role;
	}
	
	// getter and setters

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// toString
	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", password=" + password + ", role=" + role + "]";
	}
		
}
