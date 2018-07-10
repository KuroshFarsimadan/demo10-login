package ir.kuroshfarsimadan.dao.webuser;

public class UserNameReservedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNameReservedException() {
		super("Username has been already reserved for another webuser in the database.");
	}
}
