package server;

import java.util.Date;

public class ChatMessage {

	// local attributes
	private int messageId;
	private int complaintId;
	private String studentSenderId;
	private String staffSenderId;
	private String senderType;
	private String message;
	private Date timestamp;
	
	// constructors
	
	// default
	public ChatMessage() {
		this.messageId = 0000;
		this.complaintId = 0000;
		this.studentSenderId = "";
		this.staffSenderId = "";
		this.senderType = "";
		this.message = "";
		this.timestamp = new Date();
	}
	
	// primary
	public ChatMessage(int messageId, int complaintId, String studentSenderId, String staffSenderId, String senderType,
			String message, Date timestamp) {
		super();
		this.messageId = messageId;
		this.complaintId = complaintId;
		this.studentSenderId = studentSenderId;
		this.staffSenderId = staffSenderId;
		this.senderType = senderType;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	// copy
	public ChatMessage(ChatMessage chatMessage) {
		super();
		this.messageId = chatMessage.messageId;
		this.complaintId = chatMessage.complaintId;
		this.studentSenderId = chatMessage.studentSenderId;
		this.staffSenderId = chatMessage.staffSenderId;
		this.senderType = chatMessage.senderType;
		this.message = chatMessage.message;
		this.timestamp = chatMessage.timestamp;
	}
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public String getStudentSenderId() {
		return studentSenderId;
	}
	public void setStudentSenderId(String studentSenderId) {
		this.studentSenderId = studentSenderId;
	}
	public String getStaffSenderId() {
		return staffSenderId;
	}
	public void setStaffSenderId(String staffSenderId) {
		this.staffSenderId = staffSenderId;
	}
	public String getSenderType() {
		return senderType;
	}
	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	// toString
	@Override
	public String toString() {
		return "ChatMessage [messageId=" + messageId + ", complaintId=" + complaintId + ", studentSenderId="
				+ studentSenderId + ", staffSenderId=" + staffSenderId + ", senderType=" + senderType + ", message="
				+ message + ", timestamp=" + timestamp + "]";
	}
	
}
