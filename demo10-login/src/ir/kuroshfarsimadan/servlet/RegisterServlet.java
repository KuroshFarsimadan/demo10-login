package ir.kuroshfarsimadan.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ir.kuroshfarsimadan.bean.InvalidWebUserException;
import ir.kuroshfarsimadan.bean.WebUser;
import ir.kuroshfarsimadan.dao.DAOException;
import ir.kuroshfarsimadan.dao.webuser.UserNameReservedException;
import ir.kuroshfarsimadan.dao.webuser.WebUserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	public static final String FRONT_PAGE = "WEB-INF/jsp/register.jsp";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		try {
			// Create a webuser with salt and hash
			WebUser wu = new WebUser(username, password, password2);

			// Add a a webuser to the database
			WebUserDAO dao = new WebUserDAO();
			dao.add(wu);

			// Redirect back with a success message
			response.sendRedirect("site?success=y");

		} catch (InvalidWebUserException e) {
			backWithErrorMessage(e.getMessage(), username, request, response);
		} catch (UserNameReservedException e) {
			String errorMessage = "Username " + username + " has been already reserver, choose another username!";
			backWithErrorMessage(errorMessage, username, request, response);
		} catch (DAOException e) {
			throw new ServletException("Database error ", e);
		} catch (NoSuchAlgorithmException e) {
			throw new ServletException("Hashing algorithm cannot be found.", e);
		}

	}

	private void backWithErrorMessage(String message, String username, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("error", message);
		request.setAttribute("prev_reg_username", username);
		request.getRequestDispatcher(FRONT_PAGE).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(FRONT_PAGE).forward(request, response);
	}

}
