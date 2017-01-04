package meanwhile.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
	public static String encryptBySHA256(String authorization) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {}
		messageDigest.update(authorization.getBytes());
		byte[] byteData = messageDigest.digest();
		StringBuilder stringBuilder = new StringBuilder();
		int length = byteData.length;
		for (int i = 0; i < length; i++) {
			 stringBuilder.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuilder.toString();
	}
}
