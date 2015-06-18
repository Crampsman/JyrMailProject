package dk.telenor.javamail.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

public class ImapMessageReader {

	public  Store createStore(String host, String storeType, String user,
			String password) {

		Properties properties = new Properties();

		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", "993");
		properties.put("mail.imap.starttls.enable", "true");

		Session emailSession = Session.getDefaultInstance(properties);

		Store store = null;

		try {

			store = emailSession.getStore(storeType);
			store.connect(host, user, password);

		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return store;
	}

	public  Folder getFolder(String name, Store store) {

		Folder emailFolder = null;

		try {
			emailFolder = store.getFolder(name);
			emailFolder.open(Folder.READ_ONLY);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emailFolder;
	}

	public  Message[] getMessage(Folder folder) {

		Message[] messages = null;

		try {
			messages = folder.getMessages();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return messages;
	}

	
}
