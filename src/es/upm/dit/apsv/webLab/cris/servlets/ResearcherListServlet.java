package es.upm.dit.apsv.webLab.cris.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAOImplementation;

@WebServlet("/ResearcherListServlet")
public class ResearcherListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("researcher_list", ResearcherDAOImplementation.getInstance().readAll());
		getServletContext().getRequestDispatcher("/ListResearcherView.jsp").forward(req, resp);
	}
}
