<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<%@ include file="Head.jsp"%>
<%@ include file="NavBar.jsp"%>
<body>
<div class="container">
	<h2>Set researcher login credentials</h2>
	<form action="UpdateResearcherCredentialsServlet" method="post">

		<input type="text" name="uid" placeholder="User Id"> <input
			type="text" name="email" placeholder="Email"> <input
			type="text" name="password" placeholder="Password">
		<button type="submit">Set credentials</button>
	</form>
	<h2>Create researcher</h2>
	<form action="CreateResearcherServlet" method="post">

		<input type="text" name="uid" placeholder="User Id">
		<input type="text" name="name" placeholder="Name">
		<input type="text" name="last_name" placeholder="Last name">
		<input type="text" name="email" placeholder="Email">
		<input type="text" name="password" placeholder="Password">
		<input type="text" name="scopus_url" placeholder="ScopusUrl">
		<input type="text" name="eid" placeholder="eid">
		<button type="submit">Create researcher</button>
	</form>
	<h2>Populate researchers</h2>
	<form action="PopulateResearchersServlet" method="post"
		enctype="multipart/form-data">
		<input type="file" name="file" />
		<button type="submit">Populate</button>
	</form>
	<h2>Populate publications</h2>
	<form action="PopulatePublicationsServlet" method="post"
		enctype="multipart/form-data">
		<input type="file" name="file" />
		<button type="submit">Populate</button>
	</form>
	</div>
		<%@ include file="Variables.jsp"%>
</body>
</html>