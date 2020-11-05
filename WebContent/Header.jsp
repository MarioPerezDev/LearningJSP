<c:if test="${not (empty user)}">
<!-- //Se presenta un mensaje indicando si el usuario ya se ha autentificado. -->
	<p style="color: green;">You are authenticated as ${user.name}</p> 
	<p>
		<a href="LogoutServlet">Logout</a>
	</p>
</c:if>
<c:if test="${empty user}">
<!--  //se presenta un enlace a LoginServlet para que se autentifique. -->
	<a href="LoginServlet">Login</a>
</c:if>
<p style="color: red;">${message}</p>
<h3>
<!--  //Enlace a la lista pública de investigadores ResearcherListServlet -->
	<a href="ResearcherListServlet">Researchers list</a>
</h3>
<hr>
