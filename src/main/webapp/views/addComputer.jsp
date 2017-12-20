<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
</head>
<body>

    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
        <div class="navbar-brand" style="margin-top:-50px;margin-left:67%;">
			Language : <a href="?lang=en">English</a> | <a href="?lang=fr">French</a>
		</div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>${addComputerTitle}</h1>
                    <form:form modelAttribute="addForm" action="addComputer?action=add" name="form" id="form" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">${addComputerName}</label>
                                <form:input type="text" class="form-control" path="computerName" name="computerName" id="computerName" placeholder="${addComputerName}"/>
                            </div>
                            <div class="form-group">
                                <label for="introduced">${addComputerIntroduced}</label>
                                <form:input type="date" class="form-control" path="introduced" name="introduced" id="introduced" placeholder="${addComputerIntroduced}"/>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">${addComputerDiscontinued}</label>
                                <form:input type="date" class="form-control" path="discontinued" name="discontinued" id="discontinued" placeholder="${addComputerDiscontinued}"/>
                            </div>
                            <div class="form-group">
                                <label for="companyId">${addComputerCompany}</label>
                                <form:select class="form-control" path="companyId" name="companyId" id="companyId" >
                               		<c:forEach items="${listcp}" var="cp">
                                    	<option value="${cp.id}"><c:out value="${cp.nom}"></c:out></option>
                                    </c:forEach>
                                </form:select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="${add}" class="btn btn-primary">
                            ${ou}
                            <a href="dashboard" class="btn btn-default">${cancel}</a>
                        </div>
                    </form:form>
                    <script>
						$("#form").validate();
					</script>
                </div>
            </div>
        </div>
    </section>
<script src="js/jquery.min.js"></script>
<script src="js/form-validation.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
</body>
</html>