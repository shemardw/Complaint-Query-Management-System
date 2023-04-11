package server;

import java.util.List;

public interface ChatMessageCRUD {
    boolean addMessage(ChatMessage message);
    ChatMessage getMessageById(int messageId);
    List<ChatMessage> getAllMessages();
    List<ChatMessage> getMessagesByComplaintId(int complaintId);
    boolean updateMessage(ChatMessage message);
	boolean deleteMessage(int messageId);
	List<ChatMessage> getAllStudentChatMessages(String studentId);
}