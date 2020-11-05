<nav
	class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-between">
	<a class="navbar-brand" href="#">CRIS</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link"
				href="ResearcherListServlet">Researchers list </a></li>
			<li class="nav-item"><a class="nav-link"
				href="RegisterServlet">Register researcher</a></li>
		</ul>
	</div>
	<c:if test="${not (empty user)}">
		<!-- //Se presenta un mensaje indicando si el usuario ya se ha autentificado. -->
		<button style="float: right" class="btn btn-outline-success"
			type="submit">
			<a class="nav-link" href="LogoutServlet">Logout </a>
		</button>
	</c:if>
	<c:if test="${empty user}">
		<!--  //se presenta un enlace a LoginServlet para que se autentifique. -->
		<button style="float: right" class="btn btn-outline-success"
			type="submit">
			<a class="nav-link" href="LoginServlet">Login</a>
		</button>
	</c:if>
</nav>
<c:if test="${not (empty user)}">
<!-- //Se presenta un mensaje indicando si el usuario ya se ha autentificado. -->
	<p>You are authenticated as ${user.id}</p> 
</c:if>
<c:if test="${empty user}">
<!--  //se presenta un enlace a LoginServlet para que se autentifique. -->
	<p style="color: red;">${message}</p>
</c:if>