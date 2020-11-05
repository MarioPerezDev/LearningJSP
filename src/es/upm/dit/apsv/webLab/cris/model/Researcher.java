package es.upm.dit.apsv.webLab.cris.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Researcher implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "name", nullable = false, length = 15)
	private String name;
	
	@Column(name = "last_name", nullable = true, length = 15)
	private String lastName;
	
	@Column(name = "email", nullable = false, length = 30)
	private String email;
	
	@Column(name = "password", nullable = false, length = 15)
	private String password;
	
	@Column(name = "scopus_url", nullable = true, length = 255)
	private String scopusUrl;
	
	@Column(name = "eid", nullable = false)
	private String eid;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> publications;

	public Researcher() {
		publications = new HashSet<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScopusUrl() {
		return scopusUrl;
	}

	public void setScopusUrl(String scopusUrl) {
		this.scopusUrl = scopusUrl;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public Set<String> getPublications() {
		return publications;
	}

	public void setPublications(Set<String> publications) {
		this.publications = publications;
	}

}
