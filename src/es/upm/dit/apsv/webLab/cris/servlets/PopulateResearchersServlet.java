package es.upm.dit.apsv.webLab.cris.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAOImplementation;
import es.upm.dit.apsv.webLab.cris.model.Researcher;

@WebServlet("/PopulateResearchersServlet")
@MultipartConfig
public class PopulateResearchersServlet extends HttpServlet {

	final String EXPECTED_HEADER = "id,name,lastName,scopusUrl,eid";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResearcherDAO rdao = ResearcherDAOImplementation.getInstance();

		Part filePart = req.getPart("file");
		InputStream fileContent = filePart.getInputStream();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(fileContent, "UTF8"));
		String line = bReader.readLine();
		int i = 0;
		if (EXPECTED_HEADER.equals(line))
			while (null != (line = bReader.readLine())) {
				String[] lSplit = line.split(",");
				Researcher r = new Researcher();
				r.setId(lSplit[0]);
				r.setName(lSplit[1]);
				r.setLastName(lSplit[2]);
				r.setScopusUrl(lSplit[3]);
				r.setEid(lSplit[4]);
				if (null == rdao.read(r.getId())) {
					rdao.create(r);
					i++;
				}
			}

		bReader.close();
		req.setAttribute("message", i + " researchers inserted");
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
	}
}
