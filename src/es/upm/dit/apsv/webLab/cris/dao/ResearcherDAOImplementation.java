package es.upm.dit.apsv.webLab.cris.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.apsv.webLab.cris.model.Researcher;

public class ResearcherDAOImplementation implements ResearcherDAO{

	private static ResearcherDAOImplementation instance;

	public ResearcherDAOImplementation() {
	}

	public static ResearcherDAOImplementation getInstance() {
		if (null == instance) {
			instance = new ResearcherDAOImplementation();
		}
		return instance;
	}
	@Override
	public Researcher create(Researcher researcher) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save(researcher);
		session.getTransaction().commit();
		session.close();
		return researcher;
	}

	@Override
	public Researcher read(String researcherId) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Researcher researcher = session.get(Researcher.class, researcherId);
		session.getTransaction().commit();
		session.close();
		return researcher;
	}

	@Override
	public Researcher update(Researcher researcher) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.update(researcher);
		session.getTransaction().commit();
		session.close();
		return researcher;
	}

	@Override
	public Researcher delete(Researcher researcher) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(researcher);
		session.getTransaction().commit();
		session.close();
		return researcher;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Researcher> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<Researcher> researchers = session.createQuery("from Researcher").list();
		session.getTransaction().commit();
		session.close();
		return researchers;
	}

	@Override
	public List<Researcher> parseResearchers(Collection<String> ids) {
		List<Researcher> researchers = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		for(String id : ids) {
			Researcher p = session.get(Researcher.class, id);
			if(null != p) researchers.add(p);
		}
		session.getTransaction().commit();
		session.close();
		return researchers;
	}

	@Override
	public Researcher readAsUser(String email, String password) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Researcher researcher = (Researcher) session
				.createQuery("select r from Researcher r where r.email= :email and r.password = :password")
				.setParameter("email", email)
				.setParameter("password", password)
				.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return researcher;
	}

}
