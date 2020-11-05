package es.upm.dit.apsv.webLab.cris.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAOImplementation;
import es.upm.dit.apsv.webLab.cris.model.Researcher;

@WebServlet({"/LoginServlet","/"})
public class LoginServlet extends HttpServlet {

	final String ADMIN = "root";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Researcher researcher = ResearcherDAOImplementation.getInstance().readAsUser(email, password);
		if (ADMIN.equals(email) && ADMIN.equals(password)) {
			Researcher root = new Researcher();
			root.setId(1);
			req.getSession().setAttribute("userAdmin", "true");
			req.getSession().setAttribute("user", root);
			resp.sendRedirect(req.getContextPath() + "/AdminServlet");
		} else if (null != researcher) {
			req.getSession().setAttribute("userAdmin", "false");
			req.getSession().setAttribute("user", researcher);
			resp.sendRedirect(req.getContextPath() + "/ResearcherServlet" + "?id=" + researcher.getId());
		} else {
			req.setAttribute("message", "Invalid user or password");
			getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);
		}
	}

}
