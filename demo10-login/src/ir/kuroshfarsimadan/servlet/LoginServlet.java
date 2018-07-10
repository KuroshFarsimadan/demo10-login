package ir.kuroshfarsimadan.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ir.kuroshfarsimadan.bean.WebUser;
import ir.kuroshfarsimadan.dao.DAOException;
import ir.kuroshfarsimadan.dao.webuser.WebUserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			//haetaan käyttäjä tietokannasta
			WebUserDAO dao = new WebUserDAO();
			WebUser wu = dao.search(username);
			
			boolean validUser = wu.comparePassword(password);
			
			if(validUser) {
				request.getSession().setAttribute(SiteServlet.SESSION_ATTR_WEBUSER, wu);
				response.sendRedirect("site");
			} else {
				request.setAttribute("error", "Username or password is invalid!");
				request.setAttribute("prev_login_username", username);
				request.getRequestDispatcher(SiteServlet.FRONT_PAGE).forward(request, response);
			}
		} catch(DAOException e) {
			throw new ServletException("Database exception", e);
		} catch (NoSuchAlgorithmException e) {
			throw new ServletException("Hashingalgorithm was not found.", e);
		}
			
			
			
	}

}