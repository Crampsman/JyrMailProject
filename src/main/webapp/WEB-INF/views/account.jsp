<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<title>JYR-MAIL</title>
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
</head>
<body>
	<h1 align="center">Personal account</h1>
	<br />
	<div class="container">
		<div class="row">
			<div class="navbar navbar-inverse">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
							<span class="sr-only">Open navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
						</button>

						<div class="collapse navbar-collapse" id="responsive-menu">
							<ul class="nav navbar-nav" class="table">
								<li>
									<div class="button-group" role="group">
										<a class="btn btn-default navbar-btn" href="<c:url value="/message/update"/>">Update page</a> <a class="btn btn-default navbar-btn"
											href="<c:url value="/message/edit"/>">Write letter</a>
									</div>
								</li>
								<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Folders<span class="caret"></span></a>
									<ul class="dropdown-menu" role="menu">
										<c:forEach items="${folders}" var="folder">
											<li><a href="<c:url value="/message/folder/${folder.directoryId}"/>">${folder.name}</a></li>
										</c:forEach>
									</ul></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="panel-group" id="accordion">
		<c:forEach items="${messages}" var="message">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parrent="#accordion" href="#<c:url value="${message.messageId}"/>">${message.mailFrom}</a>
					</h4>
				</div>
				<div id="<c:url value="${message.messageId}"/>" class="panel-collapse collapse">
					<div class="panel-body">
						<h4>${message.subject}</h4>
						<p>${message.text}</p>
						<p align="left">${message.createDate}</p>
						<div class="button-group" role="group">
							<a class="btn btn-danger" href="<c:url value = "/message/move/${message.messageId}?folderId=4"/>">Move to bucket</a> 
							<a class="btn btn-warning" href="<c:url value = "/message/move/${message.messageId}?folderId=3"/>">Mark as spam</a> 
							<a class="btn btn-info" href="<c:url value = "/message/redirect/${message.messageId}"/>">Resent</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
