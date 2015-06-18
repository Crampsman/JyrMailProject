package com.goliev.jyrmail.util;


import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AesScramblerPassword {

	private static final String ALGORITHM = "AES";

	private static final byte[] KEY_VALUE = new byte[] { 'S', 'e', 'c', 'r',
			'e', 't', 'B', 'u', 'l', 'l', 'S', 'h', 'e', 'e', 't', 'K' };

	public static String encryptPassword(String passwordToEncrypt)
			throws Exception {

		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encValue = cipher.doFinal(passwordToEncrypt.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encValue);

		return encryptedValue;

	}

	public static String decryptPassword(String encryptedPassword)
			throws Exception {

		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder()
				.decodeBuffer(encryptedPassword);
		byte[] decValue = cipher.doFinal(decordedValue);
		String decryptedValue = new String(decValue);

		return decryptedValue;

	}

	private static Key generateKey() {

		Key key = new SecretKeySpec(KEY_VALUE, ALGORITHM);
		return key;
	}
}
