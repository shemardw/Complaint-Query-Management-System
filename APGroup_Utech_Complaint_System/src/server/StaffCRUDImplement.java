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

public class StaffCRUDImplement implements StaffCRUD {
    private DataSource dataSource;

    public StaffCRUDImplement() {
        this.dataSource = DatabaseConnector.getDataSource();
    }

    // add Staff to database
    @Override
    public boolean addStaff(Staff staff) {
        String query = "INSERT INTO staff (staff_id, first_name, last_name, email, contact_number, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, staff.getStaffId());
            preparedStatement.setString(2, staff.getFirstName());
            preparedStatement.setString(3, staff.getLastName());
            preparedStatement.setString(4, staff.getEmail());
            preparedStatement.setString(5, staff.getContactNumber());
            preparedStatement.setString(6, staff.getPassword());
            preparedStatement.setString(7, staff.getRole());

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // retrieves staff from database by id
    @Override
    public Staff getStaffById(String staffId) {
        String query = "SELECT * FROM staff WHERE staff_id = ?";
        Staff staff = null;

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, staffId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String contactNumber = resultSet.getString("contact_number");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                staff = new Staff(staffId, firstName, lastName, email, contactNumber, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    // retrieves all staff from database
    @Override
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String staffId = resultSet.getString("staff_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String contactNumber = resultSet.getString("contact_number");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                Staff staff = new Staff(staffId, firstName, lastName, email, contactNumber, password, role);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffList;
    }
    
    // updates information in staff
    @Override
    public boolean updateStaff(Staff staff) {
        String query = "UPDATE staff SET first_name = ?, last_name = ?, email = ?, contact_number = ?, password = ?, role = ? WHERE staff_id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setString(3, staff.getEmail());
            preparedStatement.setString(4, staff.getContactNumber());
            preparedStatement.setString(5, staff.getPassword());
            preparedStatement.setString(6, staff.getRole());
            preparedStatement.setString(7, staff.getStaffId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // deletes staff
    @Override
    public boolean deleteStaff(String staffId) {
        String query = "DELETE FROM staff WHERE staff_id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, staffId);

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
        StaffCRUD staffCRUD = new StaffCRUDImplement();

        // Test addStaff
        Staff staff1 = new Staff("SF001", "Jessie", "Jones", "jesse@gmail.com", "8766549810", "password123", "advisor");
        Staff staff2 = new Staff("SF002", "Mia", "Gomez", "mia.gomez@yahoo.com", "8764801100", "124password", "supervisor");
        System.out.println("Added staff 1: " + staffCRUD.addStaff(staff1));
        System.out.println("Added staff 2: " + staffCRUD.addStaff(staff2));

        // Test getStaffById
        Staff retrievedStaff = staffCRUD.getStaffById("001");
        System.out.println("Retrieved staff: " + retrievedStaff);

        // Test getAllStaff
        List<Staff> allStaff = staffCRUD.getAllStaff();
	    System.out.println("All students:");
	    for (Staff s : allStaff) {
	    System.out.println(s.getStaffId() + " " + s.getFirstName() + " " + s.getLastName());
	    }

        // Test updateStaff
        staff1.setFirstName("Johnathan");
        System.out.println("Updated staff: " + staffCRUD.updateStaff(staff1));
        Staff retrievedUpdatedStaff = staffCRUD.getStaffById("SF001");
        System.out.println("Retrieved updated staff: " + retrievedUpdatedStaff);

        // Test deleteStaff
        System.out.println("Deleted staff: " + staffCRUD.deleteStaff("SF002"));
        System.out.println("All staff after deletion:");
        for (Staff s : staffCRUD.getAllStaff()) {
            System.out.println(s.getStaffId() + " " + s.getFirstName() + " " + s.getLastName());
        }
    }*/
}
