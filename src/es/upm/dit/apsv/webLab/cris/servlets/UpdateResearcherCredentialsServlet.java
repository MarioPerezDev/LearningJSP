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

@WebServlet("/UpdateResearcherCredentialsServlet")
public class UpdateResearcherCredentialsServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String id = req.getParameter("uid");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			ResearcherDAO rdao = ResearcherDAOImplementation.getInstance();
			
			Researcher r = rdao.read(id);
			r.setEmail(email);
			r.setPassword(password);
			rdao.create(r);
			resp.sendRedirect(req.getContextPath() + "/AdminServlet");
	}

}
