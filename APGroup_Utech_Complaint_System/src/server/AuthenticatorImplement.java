package server;

public class AuthenticatorImplement implements Authenticator {

	private StudentCRUDImplement studentCRUD;
    private StaffCRUDImplement staffCRUD;

    public AuthenticatorImplement() {
        studentCRUD = new StudentCRUDImplement();
        staffCRUD = new StaffCRUDImplement();
    }

    // authenticates student using studentId and password
    @Override
    public boolean authenticatorStudent(String studentId, String password) {

    	// retrieved student by id
    	Student student = studentCRUD.getStudentById(studentId);
    	
    	// checks if student exist and password matches
    	return student != null && student.getPassword().equals(password);
    }

    // authenticates staff using staffId and password
    @Override
    public boolean authenticatorStaff(String staffId, String password) {

    	// retrieved staff by id
    	Staff staff = staffCRUD.getStaffById(staffId);
    	
    	// checks if staff exist and password matches
    	return staff != null && staff.getPassword().equals(password);
    }
}
