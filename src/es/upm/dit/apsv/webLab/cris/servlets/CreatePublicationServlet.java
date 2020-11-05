package es.upm.dit.apsv.webLab.cris.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.cris.dao.PublicationDAOImplementation;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAOImplementation;
import es.upm.dit.apsv.webLab.cris.model.Publication;
import es.upm.dit.apsv.webLab.cris.model.Researcher;

@WebServlet("/CreatePublicationServlet")
public class CreatePublicationServlet extends HttpServlet {
	
	private ResearcherDAO researcherDao = ResearcherDAOImplementation.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String firstAuthorId = req.getParameter("first_author");
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String authors = req.getParameter("authors");
		String eid = req.getParameter("eid");
		String publicationName = req.getParameter("publication_name");
		String publicationDate = req.getParameter("publication_date");
		Researcher r = researcherDao.read(firstAuthorId);
		
		Publication p = new Publication();
		p.setFirstAuthor(firstAuthorId);
		p.setId(id);
		p.setTitle(title);
		p.setAuthors(Arrays.asList(authors.split(";")));
		p.setEid(eid);
		p.setPublicationName(publicationName);
		p.setPublicationDate(publicationDate);
		p = PublicationDAOImplementation.getInstance().create(p);
		
		r.getPublications().add(id);
		researcherDao.update(r);
		resp.sendRedirect(req.getContextPath() + "/PublicationServlet?id=" + p.getId());

	}
}
