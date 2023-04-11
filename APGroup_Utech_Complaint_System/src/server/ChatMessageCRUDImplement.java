package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.*;

public class ChatMessageCRUDImplement implements ChatMessageCRUD {
    private DataSource dataSource;

    public ChatMessageCRUDImplement(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addMessage(ChatMessage message) {
        String query = "INSERT INTO chat_message (message_id, complaint_id, student_sender_id, staff_sender_id, sender_type, message, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, message.getMessageId());
            preparedStatement.setInt(2, message.getComplaintId());
            preparedStatement.setString(3, message.getStudentSenderId());
            preparedStatement.setString(4, message.getStaffSenderId());
            preparedStatement.setString(5, message.getSenderType());
            preparedStatement.setString(6, message.getMessage());
            preparedStatement.setTimestamp(7, new java.sql.Timestamp(message.getTimestamp().getTime()));

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ChatMessage getMessageById(int messageId) {
        String query = "SELECT * FROM chat_message WHERE message_id = ?";
        ChatMessage message = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, messageId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    message = new ChatMessage(
                            resultSet.getInt("message_id"),
                            resultSet.getInt("complaint_id"),
                            resultSet.getString("student_sender_id"),
                            resultSet.getString("staff_sender_id"),
                            resultSet.getString("sender_type"),
                            resultSet.getString("message"),
                            resultSet.getTimestamp("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return message;
    }
    
    @Override
    public List<ChatMessage> getAllStudentChatMessages(String studentId) {
        String query = "SELECT * FROM chat_message WHERE student_sender_id = ?";
        List<ChatMessage> messages = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    messages.add(new ChatMessage(
                            resultSet.getInt("message_id"),
                            resultSet.getInt("complaint_id"),
                            resultSet.getString("student_sender_id"),
                            resultSet.getString("staff_sender_id"),
                            resultSet.getString("sender_type"),
                            resultSet.getString("message"),
                            resultSet.getTimestamp("created_at")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public List<ChatMessage> getAllMessages() {
        String query = "SELECT * FROM chat_message";
        List<ChatMessage> messages = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                messages.add(new ChatMessage(
                        resultSet.getInt("message_id"),
                        resultSet.getInt("complaint_id"),
                        resultSet.getString("student_sender_id"),
                        resultSet.getString("staff_sender_id"),
                        resultSet.getString("sender_type"),
                        resultSet.getString("message"),
                        resultSet.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public List<ChatMessage> getMessagesByComplaintId(int complaintId) {
        String query = "SELECT * FROM chat_message WHERE complaint_id = ?";
        List<ChatMessage> messages = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, complaintId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    messages.add(new ChatMessage(
                            resultSet.getInt("message_id"),
                            resultSet.getInt("complaint_id"),
                            resultSet.getString("student_sender_id"),
                            resultSet.getString("staff_sender_id"),
                            resultSet.getString("sender_type"),
                            resultSet.getString("message"),
                            resultSet.getTimestamp("created_at")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public boolean updateMessage(ChatMessage message) {
        String query = "UPDATE chat_message SET complaint_id = ?, student_sender_id = ?, staff_sender_id = ?, sender_type = ?, message = ?, created_at = ? WHERE message_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, message.getComplaintId());
            preparedStatement.setString(2, message.getStudentSenderId());
            preparedStatement.setString(3, message.getStaffSenderId());
            preparedStatement.setString(4, message.getSenderType());
            preparedStatement.setString(5, message.getMessage());
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(message.getTimestamp().getTime()));
            preparedStatement.setInt(7, message.getMessageId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMessage(int messageId) {
        String query = "DELETE FROM chat_message WHERE message_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, messageId);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /*
    // test
    public static void main(String[] args) {
    	DataSource dataSource = DatabaseConnector.getDataSource();
        ChatMessageCRUD chatMessageCRUD = new ChatMessageCRUDImplement(dataSource);

        // addMessage
        ChatMessage newMessage = new ChatMessage(1, 1, "SD002", null, "Student", "My physics test 2 gradez are still missing", new Date());
        boolean added = chatMessageCRUD.addMessage(newMessage);
        System.out.println("Added message: " + added);
        
        ChatMessage newMessage2 = new ChatMessage(2, 1, null, "SF001", "Staff", "Please give me your occurance and name of teacher", new Date());
        boolean added2 = chatMessageCRUD.addMessage(newMessage2);
        System.out.println("Added message: " + added2);
    
        // getMessageById
        ChatMessage retrievedMessage = chatMessageCRUD.getMessageById(1);
        System.out.println("Retrieved message: " + retrievedMessage);

        // getAllMessages
        List<ChatMessage> allMessages = chatMessageCRUD.getAllMessages();
        System.out.println("All messages:");
        for (ChatMessage m : allMessages) {
            System.out.println(m.getMessageId() + " " + m.getSenderType() + " " + m.getMessage());
        }

        // updateMessage
        ChatMessage updatedMessage = new ChatMessage(1, 1, "SD002", null, "Student", "My physics test 2 grade are still missing.", new Date());
        boolean updated = chatMessageCRUD.updateMessage(updatedMessage);
        System.out.println("Updated message: " + updated);

        ChatMessage retrievedMessage2 = chatMessageCRUD.getMessageById(1);
        System.out.println("Retrieved message: " + retrievedMessage2);

        // deleteMessage
        boolean deleted = chatMessageCRUD.deleteMessage(1);
        System.out.println("Deleted message: " + deleted);
    }*/    
    
}