/**
 * 
 */
package ir.kuroshfarsimadan.bean;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import ir.kuroshfarsimadan.security.Encrypter;

/**
 * @author User
 *
 */
public class WebUser {

	private int id;
	private String username;
	private String salt;
	private String passwordHash;

	private static final String HASHING_ALGORITHM = Encrypter.SHA512;
	private static final int HASHING_ROUNDS = 100;

	/**
	 * Creates a new webuser object with username and password. Generates the salt and 
	 * hashesh the password automatically using the Encrypter class.
	 * 
	 * @param username Username
	 * @param password Human readable password (not hashed)
	 * @param password2 Human readable password (not hashed)
	 * @throws NoSuchAlgorithmException If the hashing algorithm is not found
	 * @throws UnsupportedEncodingException If the salt or hash cannot be encoded into a text
	 * @throws InvalidWebUserException If the data validation was unsuccessful
	 */
	public WebUser(String username, String password, String password2)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidWebUserException {
		super();

		validate(username, password, password2);

		this.username = username;

		// Generating salt
		String salt = Encrypter.generateSalt();
		setSalt(salt);
		// Encrypting the password
		String encryptedPassword = Encrypter.encrypter(password, getSalt(), HASHING_ALGORITHM, HASHING_ROUNDS);
		setPasswordHash(encryptedPassword);
	}

	public WebUser(int id, String username, String salt, String passwordHash) {
		super();
		this.id = id;
		this.username = username;
		this.salt = salt;
		this.passwordHash = passwordHash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	// Validating the username and password, then comparing the passwords
	private void validate(String username, String password, String password2) throws InvalidWebUserException {
		if (username == null || username.trim().length() < 3) {
			throw new InvalidWebUserException("The username must be at least have 3 characters.");
		} else if (password == null || password.trim().length() < 6) {
			throw new InvalidWebUserException("The password must at least have 6 characters.");
		} else if (!password.equals(password2)) {
			throw new InvalidWebUserException("The passwords do not match.");
		}
	}

	// Encrypts the given password with the object salt and compares it with object password hash
	public boolean comparePassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println("comparePassword(String password) password: " + password);
		String encryptedPassword = Encrypter.encrypter(password, getSalt(), HASHING_ALGORITHM, HASHING_ROUNDS);
		System.out.println("comparePassword(String password) encryptedPassword: " + encryptedPassword);
		System.out.println("comparePassword(String password) encryptedPassword.equals(this.passwordHash): " + encryptedPassword.equals(this.passwordHash));
		return encryptedPassword.equals(this.passwordHash);
	}

	@Override
	public String toString() {
		return "WebUser [id=" + id + ", username=" + username + ", salt=" + salt + ", passwordHash=" + passwordHash
				+ "]";
	}
}
