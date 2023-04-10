package server;

public class StudentServicesAdvisor extends Staff {

	// constructors

    // default
    public StudentServicesAdvisor() {
        super();
    }

    // primary
    public StudentServicesAdvisor(String staffId, String firstName, String lastName, String email, String contactNumber, String password, String role) {
        super(staffId, firstName, lastName, email, contactNumber, password, "advisor");
    }

    // copy
    public StudentServicesAdvisor(StudentServicesAdvisor advisor) {
        super(advisor);
    }
}
