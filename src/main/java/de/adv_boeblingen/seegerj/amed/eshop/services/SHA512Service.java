package de.adv_boeblingen.seegerj.amed.eshop.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tapestry5.ioc.annotations.ServiceId;

@ServiceId("sha512")
public class SHA512Service
		implements CryptService {

	@Override
	public String crypt(String input) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hash = md.digest(input.getBytes());
		return byteArrayToHexString(hash);
	}

	private static String byteArrayToHexString(byte[] b) {
		String result = "";
		for (byte element : b) {
			int value = (element & 0xff) + 0x100;
			result += Integer.toString(value, 16).substring(1);
		}
		return result;
	}
}
