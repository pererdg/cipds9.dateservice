package cipds9.dateservice.misc;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class StringCipher {

	public static byte[] encrypt(String str) throws Exception {
		byte[] bytes = str.getBytes("UTF-8");
		Cipher aes = getCipher(Cipher.ENCRYPT_MODE);
		byte[] cipStr = aes.doFinal(bytes);
		return cipStr;
	}

	public static String decrypt(byte[] cipStr) throws Exception {
		Cipher aes = getCipher(Cipher.DECRYPT_MODE);
		byte[] bytes = aes.doFinal(cipStr);
		String str = new String(bytes, "UTF-8");
		return str;
	}

	private static Cipher getCipher(int cipherMode) throws Exception {
		String pass = "UNA BONAR PARAULA DE PAS, BEN LLARGA, QUE NO SIGUI LEGIBLE, AMB MAJÚSCULES, MINÚSCULES I NÚMEROS";
		MessageDigest digest = MessageDigest.getInstance("SHA");
		digest.update(pass.getBytes("UTF-8"));
		SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
		Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
		aes.init(cipherMode, key);
		return aes;
	}
}
