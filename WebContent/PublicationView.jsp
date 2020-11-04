<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publication view</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<h2>Publication info</h2>
	<p>Id: ${publication.id}</p>
	<p>Title: ${publication.title}</p>
	<p>CiteCount: ${publication.citeCount}</p>
	<p>First author: ${firstAuthor.name} ${firstAuthor.lastName}</p>
	<h2>Authors in database</h2>

	<ol>
		<c:forEach items="${authors}" var="auth">
			<li><a href="ResearcherServlet?id=${auth.id}">${auth.id}</a> -
				${auth.name} ${auth.lastName}</li>
		</c:forEach>
	</ol>
	<c:if test="${firstAuthor==user.id || userAdmin}">
You have permission to modify this page
<h3>Update info</h3>
		<form action="UpdatePublicationServlet" method="post">
			<input type="hidden" name="id" value="${publication.id}" />
			<p>
				Title: <input type="text" name="title" value="${publication.title}" />
			</p>
			<p>
				First author: <input type="text" name="first_author"
					value="${publication.firstAuthor}" />
			</p>
			<p>
				CiteCount: <input type="text" name="cite_count" value="${publication.citeCount}" />
			</p>
			<p>
				Publication name: <input type="text" name="publication_name"
					value="${publication.publicationName}" />
			</p>
			<p>
				Publication name: <input type="text" name="publication_date"
					value="${publication.publicationDate}" />
			</p>
			<p>
				Eid: <input type="text" name="eid" value="${publication.eid}" />
			</p>
			<p>
				Authors: <input type="text" name="authors" value="${publication.authors}" />
			</p>
			<button type="submit">Update</button>
		</form>
	</c:if>

</body>
</html>