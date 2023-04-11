package client;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import server.ChatMessage;
import server.ChatMessageCRUDImplement;

class ChatMessagesTableModel extends AbstractTableModel {
    private List<ChatMessage> chatMessages;
    private final String[] columnNames = {"Message ID", "Complaint ID", "Student Sender ID", "Staff Sender ID", "Sender Type", "Message", "Timestamp"};

    public ChatMessagesTableModel(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @Override
    public int getRowCount() {
        return chatMessages.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ChatMessage message = chatMessages.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return message.getMessageId();
            case 1:
                return message.getComplaintId();
            case 2:
                return message.getStudentSenderId();
            case 3:
                return message.getStaffSenderId();
            case 4:
                return message.getSenderType();
            case 5:
                return message.getMessage();
            case 6:
                return message.getTimestamp();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
