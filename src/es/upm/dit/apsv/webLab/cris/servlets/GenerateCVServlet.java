package es.upm.dit.apsv.webLab.cris.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import es.upm.dit.apsv.webLab.cris.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.cris.dao.PublicationDAOImplementation;
import es.upm.dit.apsv.webLab.cris.model.Publication;
import es.upm.dit.apsv.webLab.cris.model.Researcher;

@WebServlet("/GenerateCVServlet")
public class GenerateCVServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Researcher researcher = (Researcher) req.getSession().getAttribute("researcher");
		ServletOutputStream sout = resp.getOutputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfDocument pdf = new PdfDocument(new PdfWriter(baos));
		Document document = new Document(pdf);
		Paragraph p = new Paragraph("Curriculum Vitae")
				.setFontSize(20);
		document.add(p);

		List list = new List();
		ListItem item = new ListItem("Full name: " + researcher.getName() + " " + researcher.getLastName());
		list.add(item);
		item = new ListItem("Email: "+ researcher.getEmail());
		list.add(item);
		document.add(list);


		Table table = new Table(new float[]{7, 1});
		table.addHeaderCell("Publication title");
		table.addHeaderCell("Citations");
		PublicationDAO pdao = PublicationDAOImplementation.getInstance();
		for(Publication pub : pdao.parsePublications(researcher.getPublications())){
			table.addCell(pub.getTitle());
			table.addCell(Integer.toString(pub.getCiteCount()));
		}
		document.add(table);

		document.close();
		pdf.close();

		resp.setContentType("application/pdf");
		resp.setContentLength(baos.size());
		baos.writeTo(sout);
	}

}
