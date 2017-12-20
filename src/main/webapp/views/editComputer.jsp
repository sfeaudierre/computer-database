<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        <div class="navbar-brand" style="margin-top:-50px;margin-left:67%;">
			Language : <a href="?lang=en&id=${pcDto.id}">English</a> | <a href="?lang=fr&id=${pcDto.id}">French</a>
		</div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id:<c:out value="${pcDto.id}"/>
                    </div>
                    <h1>${editComputerTitle}</h1>

                    <form action="editComputer?action=edit" name="form" id="form" method="POST">
                        <input type="hidden" value="${pcDto.id}" id="id" name="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">${editComputerName}</label>
                                <input type="text" class="form-control" name="computerName" id="computerName" placeholder="${editComputerName}" value="${pcDto.nom}" >
                            </div>
                            <div class="form-group">
                                <label for="introduced">${editComputerIntroduced}</label>
                                <input type="date" class="form-control" name="introduced" id="introduced" placeholder="${editComputerIntroduced}"value="${pcDto.introduced}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">${editComputerDiscontinued}</label>
                                <input type="date" class="form-control" name="discontinued" id="discontinued" placeholder="${editComputerDiscontinued}"value="${pcDto.discontinued}">
                            </div>
                            <div class="form-group">
                                <label for="companyId">${editComputerCompany}</label>
                                <select class="form-control" id="companyId" name="companyId">
                                    <c:forEach items="${listcp}" var="cp">
                                    	<option value="${cp.id}"><c:out value="${cp.nom}"></c:out></option>
                                    </c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="${edit}" class="btn btn-primary">
                            ${ou}
                            <a href="dashboard" class="btn btn-default">${cancel}</a>
                        </div>
                    </form>
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