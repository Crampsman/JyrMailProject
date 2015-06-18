package com.goliev.jyrmail.dto;


import java.sql.Timestamp;
import java.util.Date;

public class TextMessageDTO {

	private String mailFrom;
	private long userId;
	private long messageId;
	private long directoryId;
	private String subject;
	private String text;
	private String mailTo;
	private Timestamp createDate = new Timestamp(new Date().getTime());


	public TextMessageDTO() {
		
	}

	public TextMessageDTO(String mailFrom, long userId, long messageId,
			long directoryId, String subject, String text, String mailTo) {

		this.mailFrom = mailFrom;
		this.userId = userId;
		this.messageId = messageId;
		this.directoryId = directoryId;
		this.subject = subject;
		this.text = text;
		this.mailTo = mailTo;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public long getDirectoryId() {
		return directoryId;
	}

	public void setDirectoryId(long directoryId) {
		this.directoryId = directoryId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	

	public Timestamp getCreateDate() {
		return createDate;
	}
	

	public void setCreateDate(long time) {
		createDate = new Timestamp(time);
	}
	
	public void setCreateDate(Timestamp time) {
		createDate = time;
	}

	@Override
	public String toString() {
		return "TextMessageDTO [mailFrom=" + mailFrom + ", userId=" + userId
				+ ", messageId=" + messageId + ", directoryId=" + directoryId
				+ ", subject=" + subject + ", text=" + text + ", mailTo="
				+ mailTo + ", createDate=" + createDate + "]";
	}


}
