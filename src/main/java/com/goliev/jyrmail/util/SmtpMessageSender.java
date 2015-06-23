package com.goliev.jyrmail.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SmtpMessageSender {

	public  Session createSession(String smtpHost, int smtpPort, String username, String password) {

		Properties props = new Properties();

		props.put("mail.smtp.port", smtpPort);
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		return Session.getInstance(props, createAuthenticator(username, password));
	}

	public  MimeMessage createMimeMessage(Session session, String subject, String mailFrom, String mailTo) throws MessagingException {
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mailFrom));
		message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
		message.setSubject(subject);
		message.setContent(new MimeMultipart());
		return message;
	}

	public  MimeMessage addText(MimeMessage message, String text, String charset, String type) throws IOException,
			MessagingException {
		
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setText(text, charset, type);
		MimeMultipart multipart = (MimeMultipart) message.getContent();
		multipart.addBodyPart(textPart);
		return message;
	}

	public void sendMimeMessage(MimeMessage message) throws MessagingException  {
		Transport.send(message);
	}

	private  Authenticator createAuthenticator(final String username, final String password) {
		return new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};
	}

}
