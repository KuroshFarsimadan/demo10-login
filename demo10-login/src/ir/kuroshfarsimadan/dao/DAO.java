package ir.kuroshfarsimadan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ir.kuroshfarsimadan.bean.WebUser;

public class DAO {
	

	/**
	 * Constructor for loading the database connection driver
	 */
	public DAO() throws DAOException {
		try {
			Class.forName(DBConnectionProperties.getInstance().getProperty("driver")).newInstance();
		} catch (Exception e) {
			throw new DAOException("The database driver could not loaded.", e);
		}
	}

	/**
	 * Open the database connection
	 * 
	 * @return Opened database connection
	 * @throws Exception if opening the connection is not successful
	 */
	protected Connection openConnection() throws DAOException {

		try {
			return DriverManager.getConnection(DBConnectionProperties.getInstance().getProperty("url"),
					DBConnectionProperties.getInstance().getProperty("username"),
					DBConnectionProperties.getInstance().getProperty("password"));
		} catch (Exception e) {
			throw new DAOException("Opening the database connection failed.", e);
		}
	}

	/**
	 * Closes the database connection
	 * 
	 * @param conn The connection that needs to be closed
	 */
	protected void closeConnection(Connection conn) throws DAOException {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();			
			}
		} catch (Exception e) {
			throw new DAOException("Database connection refuses to close.", e);
		}
	}

}
