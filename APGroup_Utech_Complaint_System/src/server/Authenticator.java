package server;

public interface Authenticator {
    boolean authenticatorStudent(String username, String password);
    boolean authenticatorStaff(String username, String password);
    String getStaffRole(String staffId);
}