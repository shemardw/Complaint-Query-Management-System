package server;

public class StudentServicesSupervisor extends Staff {

    // constructors

    // default
    public StudentServicesSupervisor() {
        super();
    }

    // primary
    public StudentServicesSupervisor(String staffId, String firstName, String lastName, String email, String contactNumber, String password, String role) {
        super(staffId, firstName, lastName, email, contactNumber, password, "supervisor");
    }

    // copy
    public StudentServicesSupervisor(StudentServicesSupervisor supervisor) {
        super(supervisor);
    }
}
