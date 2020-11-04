package es.upm.dit.apsv.webLab.cris.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.apsv.webLab.cris.model.Publication;

public class PublicationDAOImplementation implements PublicationDAO{

	private static PublicationDAOImplementation instance;

	private PublicationDAOImplementation() {
	}

	public static PublicationDAOImplementation getInstance() {
		if (null == instance) {
			instance = new PublicationDAOImplementation();
		}
		return instance;
	}
	@Override
	public Publication create(Publication publication) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save(publication);
		session.getTransaction().commit();
		session.close();
		return publication;
	}

	@Override
	public Publication read(String publicationId) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Publication publication = session.get(Publication.class, publicationId);
		session.getTransaction().commit();
		session.close();
		return publication;
	}

	@Override
	public Publication update(Publication publication) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.update(publication);
		session.getTransaction().commit();
		session.close();
		return publication;
	}

	@Override
	public Publication delete(Publication publication) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(publication);
		session.getTransaction().commit();
		session.close();
		return publication;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Publication> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<Publication> publications = session.createQuery("from Publication").list();
		session.getTransaction().commit();
		session.close();
		return publications;
	}

	@Override
	public List<Publication> parsePublications(Collection<String> ids) {
		List<Publication> publications = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		for(String id : ids) {
			Publication p = session.get(Publication.class, id);
			if(null != p) publications.add(p);
		}
		session.getTransaction().commit();
		session.close();
		return publications;
	}

}
