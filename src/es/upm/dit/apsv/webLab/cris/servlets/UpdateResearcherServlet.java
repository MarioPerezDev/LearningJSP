package es.upm.dit.apsv.webLab.cris.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAOImplementation;
import es.upm.dit.apsv.webLab.cris.model.Researcher;

@WebServlet("/UpdateResearcherServlet")
public class UpdateResearcherServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResearcherDAO rdao = ResearcherDAOImplementation.getInstance();
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String lastName = req.getParameter("lastName");
		String eid = req.getParameter("eid");
		String scopusUrl = req.getParameter("scopusUrl");
		
		Researcher r = rdao.read(id);
		r.setName(name);
		r.setLastName(lastName);
		r.setEid(eid);
		r.setScopusUrl(scopusUrl);
		
		rdao.update(r);
		
		resp.sendRedirect(req.getContextPath() + "/ResearcherServlet?id=" + r.getId());
	}
}
