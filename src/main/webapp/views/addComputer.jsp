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
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form:form modelAttribute="addForm" action="addComputer?action=add" name="form" id="form" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <form:input type="text" class="form-control" path="computerName" name="computerName" id="computerName" placeholder="Computer name"/>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <form:input type="date" class="form-control" path="introduced" name="introduced" id="introduced" placeholder="Introduced date"/>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <form:input type="date" class="form-control" path="discontinued" name="discontinued" id="discontinued" placeholder="Discontinued date"/>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <form:select class="form-control" path="companyId" name="companyId" id="companyId" >
                               		<c:forEach items="${listcp}" var="cp">
                                    	<option value="${cp.id}"><c:out value="${cp.nom}"></c:out></option>
                                    </c:forEach>
                                </form:select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
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