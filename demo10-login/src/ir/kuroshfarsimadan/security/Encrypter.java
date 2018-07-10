package ir.kuroshfarsimadan.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

/**
 * This class can be used as a helper class in hashing password.
 * This class will also provide a service for generating salt.
 */
public class Encrypter {

	// Different possible encryption / hashing algorithms
	public static final String MD5 = "MD5";
	public static final String SHA1 = "SHA-1";
	public static final String SHA256 = "SHA-256";
	public static final String SHA384 = "SHA-384";
	public static final String SHA512 = "SHA-512";

	/**
	 * Hash the text with a specific hashing algorithm and salt for n times.
	 * 
	 * @param hasheableText A text (e.g. password), that we want to salt (UTF-8)
	 * @param salt text (UTF-8), which will be used to make hashingalgorithm unique
	 * @param hashingAlgorithm One of the available hashing algorithms like SHA-512 (Encrypter.SHA512)
	 * @param hashRounds The rounds that will be used for hashing algorithm
	 * @return hashedText Password in base64-encoded string 
	 * @throws NoSuchAlgorithmException If the chosen hashing algorithm is not found
	 * @throws UnsupportedEncodingException If the salt or text that needs to be hashed cannot be converted to UTF-8:sta bit
	 */
	public static String encrypter(String hasheableText, String salt, String hashingAlgorithm, int hashRounds) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance(hashingAlgorithm);
		md.reset();
		md.update(salt.getBytes("UTF-8"));
		byte[] input = md.digest(hasheableText.getBytes("UTF-8"));
		for (int i = 0; i < hashRounds; i++) {
			md.reset();
			input = md.digest(input);
		}
		String hashedText = DatatypeConverter.printBase64Binary(input);
		return hashedText;
	}
	
	
	/**
	 * Generates 8 bit-length salth with SHA1PRNG algorithm, which will be encoded
	 * using the base64 algorithm
	 * 
	 * @return Generated salt, which is 8 characters long
	 * @throws NoSuchAlgorithmException If the SHA1PRNG algorithm is not usable / found
	 */
	public static String generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] saltedBinary = new byte[8];
		random.nextBytes(saltedBinary);
		String saltedString = DatatypeConverter.printBase64Binary(saltedBinary);
		return saltedString;
	}
}
