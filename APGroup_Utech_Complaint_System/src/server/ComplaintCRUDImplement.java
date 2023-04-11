package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

public class ComplaintCRUDImplement implements ComplaintCRUD {
    private DataSource dataSource;

    public ComplaintCRUDImplement() {
        this.dataSource = DatabaseConnector.getDataSource();
    }

    // add Staff to database
    @Override
    public boolean addComplaint(Complaint complaint) {
        String query = "INSERT INTO complaint (student_id, advisor_id, category, description, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, complaint.getStudentId());
            preparedStatement.setString(2, complaint.getStaffId());
            preparedStatement.setString(3, complaint.getCategory());
            preparedStatement.setString(4, complaint.getDescription());
            preparedStatement.setString(5, complaint.getStatus());
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(complaint.getCreateAt().getTime()));
            preparedStatement.setTimestamp(7, new java.sql.Timestamp(complaint.getUpdatedAt().getTime()));

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Complaint getComplaintById(int complaintId) {
        String query = "SELECT * FROM complaint WHERE complaint_id = ?";
        Complaint complaint = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, complaintId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String staffId = resultSet.getString("advisor_id");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                Date createdAt = resultSet.getTimestamp("created_at");
                Date updatedAt = resultSet.getTimestamp("updated_at");

                complaint = new Complaint(complaintId, studentId, staffId, category, description, status, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaint;
    }
    
    @Override
    public List<Complaint> getAllStudentComplaints(String studentId) {
        List<Complaint> complaints = new ArrayList<>();
        String query = "SELECT * FROM complaint WHERE student_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int complaintId = resultSet.getInt("complaint_id");
                String staffId = resultSet.getString("advisor_id");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                Date createdAt = resultSet.getTimestamp("created_at");
                Date updatedAt = resultSet.getTimestamp("updated_at");

                Complaint complaint = new Complaint(complaintId, studentId, staffId, category, description, status, createdAt, updatedAt);
                complaints.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaints;
    }

    @Override
    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        String query = "SELECT * FROM complaint";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int complaintId = resultSet.getInt("complaint_id");
                String studentId = resultSet.getString("student_id");
                String advisorId = resultSet.getString("advisor_id");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                Date createdAt = resultSet.getTimestamp("created_at");
                Date updatedAt = resultSet.getTimestamp("updated_at");

                Complaint complaint = new Complaint(complaintId, studentId, advisorId, category, description, status, createdAt, updatedAt);
                complaints.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaints;
    }

    @Override
    public boolean updateComplaint(Complaint complaint) {
        String query = "UPDATE complaint SET student_id = ?, advisor_id = ?, category = ?, description = ?, status = ?, updated_at = ? WHERE complaint_id = ?";

        try (Connection connection = dataSource.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        	 preparedStatement.setString(1, complaint.getStudentId());
             preparedStatement.setString(2, complaint.getStaffId());
             preparedStatement.setString(3, complaint.getCategory());
             preparedStatement.setString(4, complaint.getDescription());
             preparedStatement.setString(5, complaint.getStatus());
             preparedStatement.setTimestamp(6, new java.sql.Timestamp(complaint.getUpdatedAt().getTime()));
             preparedStatement.setInt(7, complaint.getComplaintId());

             int affectedRows = preparedStatement.executeUpdate();
             return affectedRows > 0;
         } catch (SQLException e) {
             e.printStackTrace();
             return false;
         }
     }

    	@Override
	    public boolean deleteComplaint(int complaintId) {
	        String query = "DELETE FROM complaint WHERE complaint_id = ?";
	
	        try (Connection connection = dataSource.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	
	            preparedStatement.setInt(1, complaintId); // Update this line
	            int affectedRows = preparedStatement.executeUpdate();
	            return affectedRows > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
     
 
    	@Override
    	public List<Complaint> trackComplaints(String userId, String category) {
    	    List<Complaint> complaints = new ArrayList<>();
    	    String query = "SELECT * FROM complaint WHERE student_id = ?";

    	    if (category != null) {
    	        query += " AND category = ?";
    	    }

    	    try (Connection connection = dataSource.getConnection();
    	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

    	        preparedStatement.setString(1, userId);

    	        if (category != null) {
    	            preparedStatement.setString(2, category);
    	        }

    	        ResultSet resultSet = preparedStatement.executeQuery();

    	        while (resultSet.next()) {
    	            int complaintId = resultSet.getInt("complaint_id");
    	            String studentId = resultSet.getString("student_id");
    	            String advisorId = resultSet.getString("advisor_id");
    	            String complaintCategory = resultSet.getString("category");
    	            String description = resultSet.getString("description");
    	            String status = resultSet.getString("status");
    	            Date createdAt = resultSet.getTimestamp("created_at");
    	            Date updatedAt = resultSet.getTimestamp("updated_at");

    	            Complaint complaint = new Complaint(complaintId, studentId, advisorId, complaintCategory, description, status, createdAt, updatedAt);
    	            complaints.add(complaint);
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    	    return complaints;
    	}
    	
    /*
     // Test methods
     public static void main(String[] args) {
    	    ComplaintCRUD complaintCRUD = new ComplaintCRUDImplement();

    	    // Add a new complaint
    	    Complaint newComplaint = new Complaint(1, "SD002", "SF001", "Missing Grade", "No Math Test 2 Grade", "Open", new Date(), new Date());
    	    boolean added = complaintCRUD.addComplaint(newComplaint);
    	    System.out.println("Added complaint: " + added);
    	    
    	    Complaint newComplaint2 = new Complaint(2, "SD002", "SF001", "General Query", "What are thr library hours", "Open", new Date(), new Date());
    	    boolean added2 = complaintCRUD.addComplaint(newComplaint2);
    	    System.out.println("Added complaint: " + added2);

    	    // Get a complaint by ID
    	    Complaint retrievedComplaint = complaintCRUD.getComplaintById(1);
    	    System.out.println("Retrieved complaint: " + retrievedComplaint);

    	    // Get all complaints
    	    List<Complaint> allComplaints = complaintCRUD.getAllComplaints();
    	    System.out.println("All complaints:");
    	    for (Complaint c : allComplaints) {
    	        System.out.println(c.getComplaintId() + " " + c.getStudentId() + " " + c.getStaffId() + " " + c.getCategory() + " " + c.getDescription());
    	    }

    	    // Update a complaint
    	    Complaint updatedComplaint = new Complaint(1, "SD002", "SF001", "Missing Grade", "No Math Test 2 Grade", "Closed", new Date(), new Date());
    	    boolean updated = complaintCRUD.updateComplaint(updatedComplaint);
    	    System.out.println("Updated complaint: " + updated);

    	    Complaint retrievedComplaint2 = complaintCRUD.getComplaintById(1);
    	    System.out.println("Retrieved complaint: " + retrievedComplaint2);

    	    // Delete a complaint
    	    boolean deleted = complaintCRUD.deleteComplaint(2);
    	    System.out.println("Deleted complaint: " + deleted);
     }*/
     
}