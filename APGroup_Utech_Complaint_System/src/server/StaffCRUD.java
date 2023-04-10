package server;

import java.util.List;

public interface StaffCRUD {
	
	// the CRUD operation for student
	boolean addStaff(Staff staff);
	Staff getStaffById(String staffId);
	List<Staff> getAllStaff();
	boolean updateStaff(Staff staff);
	boolean deleteStaff(String staffId);
}
