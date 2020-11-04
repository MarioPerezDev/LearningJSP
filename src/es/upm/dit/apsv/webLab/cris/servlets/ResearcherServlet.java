package es.upm.dit.apsv.webLab.cris.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.cris.dao.PublicationDAOImplementation;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAOImplementation;
import es.upm.dit.apsv.webLab.cris.model.Researcher;

@WebServlet("/ResearcherServlet")
public class ResearcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String researcherId = req.getParameter("id");
		Researcher researcher = ResearcherDAOImplementation.getInstance().read(researcherId);
		req.getSession().setAttribute("researcher", researcher);
		req.getSession().setAttribute("publications", PublicationDAOImplementation.getInstance().parsePublications(researcher.getPublications()));
		
		getServletContext().getRequestDispatcher("/ResearcherView.jsp").forward(req, resp);
	}
}
