<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>

	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
		</div>
		<div class="navbar-brand" style="margin-top:-50px;margin-left:60%;">
			Language : <a href="?lang=en">English</a> | <a href="?lang=fr">French</a>
		</div>
		<div class="navbar-brand" style="margin-top:-57px;margin-left:74%;">
			<a	class="btn btn-danger" href="login" role="button"><spring:message code="label.logout" /></a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:out value="${count}"/>
				<spring:message code="label.computersFound"/>
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard?action=search"
						method="POST" class="form-inline">

						<input type="search" id="searchbox" name="search" class="form-control" placeholder="<spring:message code="label.search"/>"/> 
							<input type="submit" id="searchsubmit" value="<spring:message code="label.filter"/>" class="btn btn-primary"/>
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"	href="addComputer?action=add"><spring:message code="label.addComputer"/></a> 
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="label.edit"/></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="dashboard?action=delete" method="POST">
			<input type="hidden" name="selection" value="${pcDto.id}">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;">
						<input type="checkbox" id="selectall" /> 
							<span style="vertical-align: top;"> - 
								<a href="" id="deleteSelected" onclick="$.fn.deleteSelected();"> 
									<i class="fa fa-trash-o fa-lg"></i>
								</a>
							</span>
						</th>
						<th><spring:message code="label.name"/></th>
						<th><spring:message code="label.introduced"/></th>
						<th><spring:message code="label.discontinued"/></th>
						<th><spring:message code="label.company"/></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach items="${searchPc}" var="pc"> 
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${pc.id}"></td>
							<td><a href="editComputer?action=edit&id=${pc.id}"
								onclick=""><c:out value="${pc.name}" /></a></td>
							<td><c:out value="${pc.dateIntroduced}" /></td>
							<td><c:out value="${pc.dateDiscontinued}" /></td>
							<td><c:forEach items="${listcp}" var="cp">
									<c:if test="${cp.id == pc.companyId}">
										<c:out value="${cp.nom}" />
									</c:if>
								</c:forEach></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${pageid != 1}">
				<li>
					<a href="dashboard?page=${pageid-1}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				</c:if>
				<c:if test="${pageid == 1}">
				<li>
					<a aria-label="Previous" aria-disabled="true">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				</c:if>
				

				<c:if test="${pageid > 2 && pageid < lastpage-1}">
					<li>
						<a href="dashboard?page=${pageid-2}">
							<c:out value="${pageid-2}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid-1}">
							<c:out value="${pageid-1}" />
						</a>
					</li>
					<li>
						<a style="font-weight: bold;background-color: lightblue;">
							<c:out value="${pageid}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid+1}">
							<c:out value="${pageid+1}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid+2}">
							<c:out value="${pageid+2}" />
						</a>
					</li>
				</c:if>

				<c:if test="${pageid == 1}">
					<li>
						<a style="font-weight: bold;background-color: lightblue;">
							<c:out value="${pageid}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid+1}">
							<c:out value="${pageid+1}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid+2}">
							<c:out value="${pageid+2}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid+3}">
							<c:out value="${pageid+3}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid+4}">
							<c:out value="${pageid+4}" />
						</a>
					</li>
				</c:if>

				<c:if test="${pageid == 2}">
					<li>
						<a href="dashboard?page=${pageid-1}">
							<c:out value="${pageid-1}" />
						</a>
					</li>
					<li>
						<a style="font-weight: bold; background-color: lightblue;">
							<c:out value="${pageid}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid+1}">
							<c:out value="${pageid+1}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid+2}">
							<c:out value="${pageid+2}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid+3}">
							<c:out value="${pageid+3}" />
						</a>
					</li>
				</c:if>
				
				<c:if test="${pageid == lastpage-1}">
					<li>
						<a href="dashboard?page=${pageid-3}">
							<c:out value="${pageid-3}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid-2}">
							<c:out value="${pageid-2}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid-1}">
							<c:out value="${pageid-1}" />
						</a>
					</li>
					<li>
						<a style="font-weight: bold;background-color: lightblue;">
							<c:out value="${pageid}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid+1}">
							<c:out value="${pageid+1}" />
						</a>
					</li>
				</c:if>
				
				<c:if test="${pageid == lastpage}">
					<li>
						<a href="dashboard?page=${pageid-4}">
							<c:out value="${pageid-4}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid-3}">
							<c:out value="${pageid-3}" />
						</a>
					</li>
					<li>
						<a  href="dashboard?page=${pageid-2}">
							<c:out value="${pageid-2}" />
						</a>
					</li>
					<li>
						<a href="dashboard?page=${pageid-1}">
							<c:out value="${pageid-1}" />
						</a>
					</li>
					<li>
						<a style="font-weight: bold;background-color: lightblue;">
							<c:out value="${pageid}" />
						</a>
					</li>
				</c:if>

				<c:if test="${pageid != lastpage}">
				<li>
					<a href="dashboard?page=${pageid+1}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
				<c:if test="${pageid == lastpage}">
				<li>
					<a aria-label="Next" aria-disabled="true">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
			</ul>

			<c:if test="${nombre == 10}">
			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="dashboard?nombre=10">
					<button type="button" style="font-weight: bold; background-color: lightgrey;" class="btn btn-default">10</button>
				</a> 
				<a href="dashboard?nombre=50">
					<button type="button" class="btn btn-default">50</button>
				</a> 
				<a href="dashboard?nombre=100">
					<button type="button" class="btn btn-default">100</button>
				</a>
			</div>
			</c:if>
			
			<c:if test="${nombre == 50}">
			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="dashboard?nombre=10">
					<button type="button" class="btn btn-default">10</button>
				</a> 
				<a href="dashboard?nombre=50">
					<button type="button" style="font-weight: bold; background-color: lightgrey;" class="btn btn-default">50</button>
				</a> 
				<a href="dashboard?nombre=100">
					<button type="button" class="btn btn-default">100</button>
				</a>
			</div>
			</c:if>
			
			<c:if test="${nombre == 100}">
			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="dashboard?nombre=10">
					<button type="button" class="btn btn-default">10</button>
				</a> 
				<a href="dashboard?nombre=50">
					<button type="button" class="btn btn-default">50</button>
				</a> 
				<a href="dashboard?nombre=100">
					<button type="button" style="font-weight: bold; background-color: lightgrey;" class="btn btn-default">100</button>
				</a>
			</div>
			</c:if>
		</div>
	</footer>

	<script src="js/jquery.min.js"></script>
	<script src="js/form-validation.js"></script>
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>