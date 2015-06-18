<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
</style>
<title>Sign in</title>
</head>
<body>
	<br />
	<br />
	<div class="container">
		<form class="well form-horizontal" method="POST" action="<c:url value = "/j_spring_security_check"/>">
			<fieldset>
				<legend>Enter to account</legend>
				<div class="form-group">
					<label class="col-md-4 control-label">E-Mail</label>
					<div class="col-md-5 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span> <input name="j_username" placeholder="E-Mail Address" class="form-control"
								type="text">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label">Password</label>
					<div class="col-md-5 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span> <input name="j_password" placeholder="Password" class="form-control" type="text" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<div class="button-group">
							<button type="submit" class="btn btn-warning">
								Submit <span class="glyphicon glyphicon-send"></span>
							</button>
							<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
						</div>
					</div>
				</div>
			</fieldset>
		</form>
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
