package dk.telenor.javamail.smtp;

import java.io.IOException;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import dk.telenor.javamail.util.ImapMessageReader;
import dk.telenor.javamail.util.SmtpMessageSender;

@Component
public class SmtpMessageTransfer  {

	private static final String SMTP_HOST = "smtp.gmail.com";
	private static final String IMAP_HOST = "imap.gmail.com";
	private static final int SMTP_PORT = 465;
	private static final String STORE_TYPE = "imaps";
	private static final String CHARSET = "utf-8";
	private static final String TYPE = "plain";

	private SmtpMessageSender messageSender = new SmtpMessageSender();

	private ImapMessageReader messageReader = new ImapMessageReader();

	public void sendMessageToSmtp(String text, String subject, String mailTo, String email, String password) throws MessagingException, IOException {

		Session session = messageSender.createSession(SMTP_HOST, SMTP_PORT,
				email, password);

		MimeMessage message = messageSender.createMimeMessage(session, subject,
				email, mailTo);

		messageSender.addText(message, text, CHARSET, TYPE);

		messageSender.sendMimeMessage(message);
	}

	public Message[] getMessagesFromSmtp(String email, String password) throws MessagingException, IOException {

		Store store = messageReader.createStore(IMAP_HOST, STORE_TYPE, email, password);

		Folder folder = messageReader.getFolder("INBOX", store);
		Message[] messages = messageReader.getMessage(folder);
		
		return messages;
	}
	
	public  String getMessageText(Part part) throws MessagingException, IOException {

		if (part.isMimeType("text/*")) {
			
			String s = (String) part.getContent();
			return s;
		}

		if (part.isMimeType("multipart/alternative")) {
			
			Multipart mp = (Multipart) part.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getMessageText(bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getMessageText(bp);
					if (s != null)
						return s;
				} else {
					return getMessageText(bp);
				}
			}
			return text;
		} else if (part.isMimeType("multipart/*")) {
			
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getMessageText(mp.getBodyPart(i));
				if (s != null)
					return s;
			}
		}
		return "Empty text";

	}

	

}
