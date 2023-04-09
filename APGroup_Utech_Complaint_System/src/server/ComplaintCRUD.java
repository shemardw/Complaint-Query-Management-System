package server;

import java.util.List;

public interface ComplaintCRUD {
	
	// Complaint CRUD methods
	boolean addComplaint(Complaint complaint);
    Complaint getComplaintById(int complaintId);
    List<Complaint> getAllComplaints();
    boolean updateComplaint(Complaint complaint);
	boolean deleteComplaint(int i);
}
