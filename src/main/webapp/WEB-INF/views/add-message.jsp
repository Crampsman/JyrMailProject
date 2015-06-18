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
<title>Add massage</title>
</head>
<body>
	<br />
	<div class="container">
		<sf:form id="registration-form" class="well form-horizontal" method="post" modelAttribute="message">
			<fieldset>
				<legend align="bottom"> Create new message </legend>
				<div class="form-group">
					<label class="control-label col-md-4" for="name">Message subject</label>
					<div class="inputGroupContainer col-md-5">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<sf:input path="subject" type="text" class="form-control" name="subject" id="subject" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-4" for="name">Message text</label>
					<div class="inputGroupContainer col-md-5">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
							<sf:textarea path="text" type="text" rows="15" cols="50" class="form-control" name="text" id="text" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-4" for="email">Mail to</label>
					<div class="inputGroupContainer col-md-5">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
							<sf:input path="mailTo" type="text" class="form-control" name="mailTo" id="mailTo" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="inputGroupContainer col-md-5">
						<div class="input-group">
							<sf:input path="mailFrom" type="hidden" class="form-control" name="mailFrom" id="mailFrom" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="inputGroupContainer col-md-5">
						<div class="input-group">
							<sf:input path="createDate" type="hidden" class="form-control" name="createDate" id="createDate" />
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