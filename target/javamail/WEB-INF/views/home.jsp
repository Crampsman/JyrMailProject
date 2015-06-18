<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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

.con-wrapper {
	margin-top: 3%;
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

.footer {
	background: rgb(255,255,255); /* Old browsers */
background: -moz-linear-gradient(45deg,  rgba(255,255,255,1) 0%, rgba(229,229,229,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left bottom, right top, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(229,229,229,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(45deg,  rgba(255,255,255,1) 0%,rgba(229,229,229,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(45deg,  rgba(255,255,255,1) 0%,rgba(229,229,229,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(45deg,  rgba(255,255,255,1) 0%,rgba(229,229,229,1) 100%); /* IE10+ */
background: linear-gradient(45deg,  rgba(255,255,255,1) 0%,rgba(229,229,229,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#e5e5e5',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */

}

.block-button {
	padding: 2%;
	margin: auto;
	width: 45%;
}
</style>
<title>JYR-MAIL</title>
</head>
<body>
	<div class="container con-wrapper">
		<div class="row">
			<div class="col-md-5">
				<img src="${pageContext.request.contextPath}/resources/img/logo.png" class="img-responsive" alt="logo">
			</div>
			<div class="col-md-7">
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras tellus libero, accumsan non justo ornare, dictum viverra mauris. Sed leo ligula, auctor id diam
					ut, aliquam semper purus. Ut non nulla vitae nunc porttitor bibendum. Quisque non elementum ipsum. Morbi mollis, lectus et faucibus posuere, enim lacus dignissim
					leo, at iaculis diam leo ut mi. Ut mollis justo congue tellus volutpat luctus. Pellentesque ac ipsum ac dui hendrerit congue et eu elit. Sed porta porttitor luctus.
					Duis et turpis nec leo commodo volutpat. Curabitur vestibulum sollicitudin arcu et faucibus. Aenean ornare justo quis felis convallis vehicula. Suspendisse vel orci
					feugiat lorem volutpat convallis. Etiam egestas vestibulum sem. Nullam pellentesque augue in ipsum finibus volutpat. Phasellus facilisis turpis eu orci laoreet,
					vitae rhoncus augue facilisis. Suspendisse potenti. Donec id fringilla nisl. Maecenas non nisi auctor, fermentum diam at, congue lacus. Fusce at felis malesuada,
					pretium nunc sit amet, vestibulum nulla. Fusce non faucibus ligula, sit amet maximus leo. Donec eget imperdiet nisi, eget rutrum enim. Fusce dapibus, urna quis
					aliquam vulputate, nulla nibh dapibus tellus, quis dictum purus augue quis est. Etiam et enim tincidunt, consequat augue et, dignissim tellus. Fusce facilisis nunc
					mi, in tincidunt dolor accumsan ac.</p>
			</div>
		</div>

		<sec:authorize access="!isAuthenticated()">

			<div class="block-button" align="center">
				<a href="#pop-up" role="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal">Sign in</a> <a class="btn btn-info btn-lg btn-block"
					href="<c:url value="/user/edit"/>">Registration</a>
			</div>


			<div id="pop-up" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<br /> <br />
				<div class="container">
					<form class="well form-horizontal" method="POST" action="<c:url value = "/j_spring_security_check"/>" id="sign-form">
						<fieldset>
							<legend>Enter to account</legend>
							<div class="form-group">
								<label class="col-md-4 control-label">E-Mail</label>
								<div class="col-md-5 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span> <input name="j_username" placeholder="E-Mail Address must be not empty"
											class="form-control" type="text" id="email">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label">Password</label>
								<div class="col-md-5 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span> <input name="j_password" placeholder="Password must by not empty"
											class="form-control" type="text" id="password" />
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
			</div>




		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<div class="block-button">
				<a class="btn btn-primary btn-lg btn-block" href="<c:url value="message/account"/>">My account</a> <a class="btn btn-info btn-lg btn-block"
					href="<c:url value = "/logout"/>">Exit</a>
			</div>
		</sec:authorize>
	</div>



	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/additional-methods.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
	<script>
		
	</script>
</body>
</html>