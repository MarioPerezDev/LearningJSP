
package es.upm.dit.apsv.webLab.cris.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.upm.dit.apsv.webLab.cris.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.cris.dao.PublicationDAOImplementation;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.cris.dao.ResearcherDAOImplementation;
import es.upm.dit.apsv.webLab.cris.model.Publication;
import es.upm.dit.apsv.webLab.cris.model.Researcher;

@WebServlet("/PopulatePublicationsServlet")
@MultipartConfig
public class PopulatePublicationsServlet extends HttpServlet {

	private final String EXPECTED_HEADER = "id,tilte,eid,publicationName,publicationDate,firstAuthor,authors";
	
	private ResearcherDAO researcherDao = ResearcherDAOImplementation.getInstance();
	private PublicationDAO publicationDao = PublicationDAOImplementation.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part filePart = req.getPart("file");
		InputStream fileContent = filePart.getInputStream();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(fileContent, "UTF8"));
		String line = bReader.readLine();
		int i = 0;
		if (EXPECTED_HEADER.equals(line))
			while (null != (line = bReader.readLine())) {
				String[] lSplit = line.split(",");
				Publication p = new Publication();
				Researcher r = researcherDao.read(Integer.parseInt(lSplit[5]));
				if (null == r)
					continue;
				p.setId(lSplit[0]);
				p.setTitle(lSplit[1]);
				p.setEid(lSplit[2]);
				p.setPublicationName(lSplit[3]);
				p.setPublicationDate(lSplit[4]);
				p.setFirstAuthor(r.getId().toString());
				p.setAuthors(Arrays.asList(lSplit[6].split(";")));
				if (null == publicationDao.read(p.getId()))
					try {
						publicationDao.create(p);
						r.getPublications().add(p.getId());
						researcherDao.update(r);
						i++;
					} catch (Exception e) {
						// TODO: problema con las publicaciones con cientos de autores
					}
			}

		bReader.close();
		req.setAttribute("message", i + " publications inserted");
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
	}
}
