package ir.kuroshfarsimadan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ir.kuroshfarsimadan.bean.WebUser;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/site")
public class SiteServlet extends HttpServlet {

	public static final String FRONT_PAGE = "WEB-INF/jsp/dashboard.jsp";
	private static final String INSIDE_PAGE = "WEB-INF/jsp/secure/inside.jsp";

	public static final String SESSION_ATTR_WEBUSER = "user";

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SiteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieving webuser information from the session
		WebUser user = (WebUser) request.getSession().getAttribute(SESSION_ATTR_WEBUSER);

		if (user == null) {
			// If the userinformation is not found, we will throw the user to the dashboard
			request.getRequestDispatcher(FRONT_PAGE).forward(request, response);
		} else {
			// If the userinformation is found, we will let the user pass
			request.getRequestDispatcher(INSIDE_PAGE).forward(request, response);
		}

	}
}
