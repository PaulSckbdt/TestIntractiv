package com.cda.testIntractiv.model;

import java.util.Iterator;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import lombok.Data;

@Data
public class Password {

	private static String sel = "PoIvreEtSel";
	private static int iterations = 10000;
	private static int keyLength = 512;
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	public static String getHash(String password) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), sel.getBytes(), iterations, keyLength);
			SecretKey key = skf.generateSecret(spec);
			byte[] res = key.getEncoded();
			return bytesToHex(res);
		} catch (Exception e) {
		}
		return null;
	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

	public Compliance testCompliance(String password) {

		Compliance compliance = new Compliance();

		if (password.length() < 8) {
			compliance.setValid(false);
			compliance.setReason("at least 8 characters");
		} else if (!password.matches(".*[0-9].*")) {
			compliance.setValid(false);
			compliance.setReason("at least one digit");
		} else if (!(password.contains("!") || password.contains("#") || password.contains("$")
				|| password.contains("%") || password.contains("&") || password.contains("@"))) {
			compliance.setValid(false);
			compliance.setReason("at least one special character (!,#,$,%,&,@)");
		} else if (!containUpperCase(password)) {
			compliance.setValid(false);
			compliance.setReason("at least one uppercase letter");
		} else {
			compliance.setValid(true);
			compliance.setReason("password is compliant");
		}

		return compliance;

	}

	public static boolean containUpperCase(String pwd) {
		boolean isUpper = false;
		for (int i = 0; i < pwd.length(); i++) {
			if (Character.isUpperCase(pwd.charAt(i))) {
				isUpper = true;
			}
		}

		return isUpper;

	}

}
