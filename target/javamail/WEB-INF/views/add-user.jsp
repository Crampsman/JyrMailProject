<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">

<style type="text/css">
label.valid {
	width: 24px;
	height: 24px;
	background:
		url(${pageContext.request.contextPath}/resources/img/valid.png) center
		center no-repeat;
	display: inline-block;
	text-indent: -9999px;
}

label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}

body {
background: rgb(245,246,246); /* Old browsers */
background: -moz-linear-gradient(45deg,  rgba(245,246,246,1) 0%, rgba(219,220,226,1) 21%, rgba(184,186,198,1) 49%, rgba(221,223,227,1) 80%, rgba(245,246,246,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left bottom, right top, color-stop(0%,rgba(245,246,246,1)), color-stop(21%,rgba(219,220,226,1)), color-stop(49%,rgba(184,186,198,1)), color-stop(80%,rgba(221,223,227,1)), color-stop(100%,rgba(245,246,246,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(45deg,  rgba(245,246,246,1) 0%,rgba(219,220,226,1) 21%,rgba(184,186,198,1) 49%,rgba(221,223,227,1) 80%,rgba(245,246,246,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(45deg,  rgba(245,246,246,1) 0%,rgba(219,220,226,1) 21%,rgba(184,186,198,1) 49%,rgba(221,223,227,1) 80%,rgba(245,246,246,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(45deg,  rgba(245,246,246,1) 0%,rgba(219,220,226,1) 21%,rgba(184,186,198,1) 49%,rgba(221,223,227,1) 80%,rgba(245,246,246,1) 100%); /* IE10+ */
background: linear-gradient(45deg,  rgba(245,246,246,1) 0%,rgba(219,220,226,1) 21%,rgba(184,186,198,1) 49%,rgba(221,223,227,1) 80%,rgba(245,246,246,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f5f6f6', endColorstr='#f5f6f6',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
margin-bottom: 60px;
}

html {
	position: relative;
	min-height: 100%;
}
</style>

<title>Add user</title>
</head>
<body>
	<br />
	<br />
	<div class="container">
		<sf:form id="registration-form" class="well form-horizontal" method="post" modelAttribute="user">
			<fieldset>
				<legend align="bottom">
					Join Us Today!
					<c:choose>
						<c:when test="${not empty exists}">
							<h3>User already exists</h3>
						</c:when>
					</c:choose>
				</legend>

				<div class="form-group">
					<label class="control-label col-md-4" for="name">User Name</label>
					<div class="inputGroupContainer col-md-5">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<sf:input path="name" type="text" class="form-control" placeholder="Username must be between 3 and 20 characters long." name="username" id="username" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-4" for="name">Password</label>
					<div class="inputGroupContainer col-md-5">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
							<sf:input path="password" type="text" class="form-control" placeholder="Password must be between 6 and 20 characters long" name="name" id="password" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-4" for="name">Retype Password</label>
					<div class="inputGroupContainer col-md-5">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span> <input type="password" class="form-control"
								placeholder="Password must be between 6 and 20 characters long" name="confirm_password" id="confirm_password">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-4" for="email">Email Address</label>
					<div class="inputGroupContainer col-md-5">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
							<sf:input path="email" type="text" class="form-control" placeholder="Must be a valid e-mail address (user@gmail.com)" name="email" id="email" />
						</div>
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-warning">
						Submit <span class="glyphicon glyphicon-send"></span>
					</button>
					<button type="reset" class="btn">Cancel</button>
				</div>
			</fieldset>
		</sf:form>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
	<script src="${pageContext.request.contextPath}/resources/script.js"></script>
	<script>
		addEventListener('load', prettyPrint, false);
		$(document).ready(function() {
			$('pre').addClass('prettyprint linenums');
		});
	</script>
</body>
</html>