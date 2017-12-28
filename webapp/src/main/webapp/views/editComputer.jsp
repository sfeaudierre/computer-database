<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
                    <h1><spring:message code="label.editComputerTitle"/></h1>

                    <form action="editComputer?action=edit" name="form" id="form" method="POST">
                        <input type="hidden" value="${pcDto.id}" id="id" name="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="label.editComputerName"/></label>
                                <input type="text" class="form-control" name="computerName" id="computerName" placeholder="PC" value="${pcDto.name}" >
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="label.editComputerIntroduced"/></label>
                                <input type="date" class="form-control" name="introduced" id="introduced" placeholder="yy/mm/dd"value="${pcDto.dateIntroduced}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="label.editComputerDiscontinued"/></label>
                                <input type="date" class="form-control" name="discontinued" id="discontinued" placeholder="yy/mm/dd"value="${pcDto.dateDiscontinued}">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="label.editComputerCompany"/></label>
                                <select class="form-control" id="companyId" name="companyId">
                                    <c:forEach items="${listcp}" var="cp">
                                    	<option value="${cp.id}"><c:out value="${cp.nom}"></c:out></option>
                                    </c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="label.edit"/>" class="btn btn-primary">
                            <spring:message code="label.ou"/>
                            <a href="dashboard" class="btn btn-default"><spring:message code="label.cancel"/></a>
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