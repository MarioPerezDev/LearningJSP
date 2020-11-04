package es.upm.dit.apsv.webLab.cris.dao;

import java.util.Collection;
import java.util.List;

import es.upm.dit.apsv.webLab.cris.model.Publication;

public interface PublicationDAO {
	public Publication create( Publication publication );
	public Publication read( String publicationId );
	public Publication update( Publication publication );
	public Publication delete( Publication publication );
	
	public List<Publication> readAll();
	public List<Publication> parsePublications(Collection<String> ids);
}
