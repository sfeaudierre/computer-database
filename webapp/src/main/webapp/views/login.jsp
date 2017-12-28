<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
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
                <table>
					<tr>
						<td class="error" colspan="2" style="color: red;">
							<c:if test="${not empty error}"> ${error}</c:if>
						</td>
					</tr>
				</table>
                    <h1><spring:message code="label.loginTitle"/></h1>
                    <form name="loginForm" action="<c:url value='spring_security'/>" method="POST"> 
                        <fieldset>
                            <div class="form-group">
                                <label for="username"><spring:message code="label.id"/></label>
                                <input type="text" class="form-control" name="username" id="username" placeholder="<spring:message code="label.id"/>"/>
                            </div>
                            <div class="form-group">
                                <label for="password"><spring:message code="label.password"/></label> 
                                <input type="password" class="form-control" name="password" id="password" placeholder="**********"/>
                            </div>
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="label.login"/>" class="btn btn-primary">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
  
</body>
</html>