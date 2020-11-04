package es.upm.dit.apsv.webLab.cris.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.cris.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.cris.dao.PublicationDAOImplementation;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAOImplementation;
import es.upm.dit.apsv.webLab.cris.model.Publication;
import es.upm.dit.apsv.webLab.cris.model.Researcher;

@WebServlet("/UpdatePublicationServlet")
public class UpdatePublicationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstAuthorId = req.getParameter("first_author");
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String authors = req.getParameter("authors");
		String eid = req.getParameter("eid");
		String publicationName = req.getParameter("publication_name");
		String publicationDate = req.getParameter("publication_date");
		ResearcherDAO rdao = ResearcherDAOImplementation.getInstance();
		PublicationDAO pdao = PublicationDAOImplementation.getInstance();
		Publication p = pdao.read(id);
		
		Researcher oldAuthor = rdao.read(p.getFirstAuthor());
		oldAuthor.getPublications().remove(id);
		rdao.update(oldAuthor);
		Researcher newAuthor = rdao.read(firstAuthorId);
		newAuthor.getPublications().add(id);
		rdao.update(newAuthor);
		
		p.setFirstAuthor(firstAuthorId);
		p.setTitle(title);
		p.setAuthors(Arrays.asList(authors.split(";")));
		p.setEid(eid);
		p.setPublicationName(publicationName);
		p.setPublicationDate(publicationDate);
		pdao.update(p);
		resp.sendRedirect(req.getContextPath() + "/PublicationServlet?id=" + p.getId());
	}
}
