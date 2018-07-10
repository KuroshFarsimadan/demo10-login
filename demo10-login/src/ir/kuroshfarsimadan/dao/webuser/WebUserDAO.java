package ir.kuroshfarsimadan.dao.webuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ir.kuroshfarsimadan.bean.WebUser;
import ir.kuroshfarsimadan.dao.DAO;
import ir.kuroshfarsimadan.dao.DAOException;

public class WebUserDAO extends DAO {

	public WebUserDAO() throws DAOException {
		super();
	}

	/**
	 * Add a webuser to database
	 * 
	 * @param wu
	 *            WebUser information that will be added
	 * @throws UserNameReservedException
	 *             If the database already has a webuser with the same username
	 * @throws DAOException
	 *             If an database error occurred
	 */
	public void add(WebUser wu) throws DAOException, UserNameReservedException {

		// Opening the connection
		Connection conn = openConnection();

		try {

			// Executing the search
			// Initializing the sql-statement
			// Checking that the username does not exist in the database
			String sql = "select id from webuser where username = ?";
			PreparedStatement stmnt = conn.prepareStatement(sql);

			stmnt.setString(1, wu.getUsername());
			ResultSet rs = stmnt.executeQuery();
			if (rs.next()) {
				System.out.println("add(WebUser wu) rs.next(): " + rs.next());
				throw new UserNameReservedException();
			}

			String sql2 = "insert into webuser(username, password_hash, salt) values(?,?,?)";
			PreparedStatement stmntInsert = conn.prepareStatement(sql2);
			// Filling out the missing information for the webuser addition
			stmntInsert.setString(1, wu.getUsername());
			stmntInsert.setString(2, wu.getPasswordHash());
			stmntInsert.setString(3, wu.getSalt());

			// Executing the statement
			stmntInsert.executeUpdate();

			System.out.println("Added a webuser to database: " + wu);
		} catch (Exception e) {
			// An error occured
			throw new DAOException("Adding a webuser caused an error.", e);
		} finally {
			// Close the connection
			closeConnection(conn);
		}

	}

	public WebUser search(String username) throws DAOException {
		WebUser wu;
		Connection conn = openConnection();
		try {
			// Checking that a person with a particular username exists
			PreparedStatement usernameSearch = conn
					.prepareStatement("select id, username, salt, password_hash from webuser where username = ?");
			usernameSearch.setString(1, username);
			ResultSet rs = usernameSearch.executeQuery();
			if (rs.next()) {
				// Found
				wu = new WebUser(rs.getInt("id"), rs.getString("username"), rs.getString("salt"),
						rs.getString("password_hash"));
			} else {
				// Not found
				// Generating empty user
				wu = new WebUser(-1, "-", "-", "-");
			}
		} catch (SQLException e) {
			// An exception occurred
			throw new DAOException("Database caused an error", e);
		} finally {
			// Finally always close the connection
			closeConnection(conn);
		}
		return wu;
	}

}
