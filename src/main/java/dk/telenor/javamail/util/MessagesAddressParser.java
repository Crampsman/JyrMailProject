package dk.telenor.javamail.util;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import dk.telenor.javamail.dto.TextMessageDTO;

public class MessagesAddressParser {

	public static String getFromEmailAddress(Message message) {

		String email = "";

		try {
			Address[] address = message.getFrom();
			email = address == null ? null : ((InternetAddress) address[0])
					.getAddress();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;

	}

	public static String getToEmailAddress(Message message) {

		String email = "";

		try {
			Address[] address = message.getAllRecipients();
			email = address == null ? null : ((InternetAddress) address[0])
					.getAddress();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;

	}
}
