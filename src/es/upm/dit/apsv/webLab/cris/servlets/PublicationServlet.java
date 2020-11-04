package es.upm.dit.apsv.webLab.cris.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.cris.dao.PublicationDAOImplementation;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAOImplementation;
import es.upm.dit.apsv.webLab.cris.model.Publication;

@WebServlet("/PublicationServlet")
public class PublicationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String publicationId = req.getParameter("id");
		Publication publication = PublicationDAOImplementation.getInstance().read(publicationId);
		req.getSession().setAttribute("publication", publication);
		ResearcherDAO rdao = ResearcherDAOImplementation.getInstance();
		req.getSession().setAttribute("firstAuthor", rdao.read(publication.getFirstAuthor()));
		req.getSession().setAttribute("authors", rdao.parseResearchers(publication.getAuthors()));
		getServletContext().getRequestDispatcher("/PublicationView.jsp").forward(req, resp);
	}
}
