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
	background: rgb(245, 246, 246); /* Old browsers */
	background: -moz-linear-gradient(45deg, rgba(245, 246, 246, 1) 0%,
		rgba(219, 220, 226, 1) 21%, rgba(184, 186, 198, 1) 49%,
		rgba(221, 223, 227, 1) 80%, rgba(245, 246, 246, 1) 100%); /* FF3.6+ */
	background: -webkit-gradient(linear, left bottom, right top, color-stop(0%, rgba(245
		, 246, 246, 1)), color-stop(21%, rgba(219, 220, 226, 1)),
		color-stop(49%, rgba(184, 186, 198, 1)),
		color-stop(80%, rgba(221, 223, 227, 1)),
		color-stop(100%, rgba(245, 246, 246, 1))); /* Chrome,Safari4+ */
	background: -webkit-linear-gradient(45deg, rgba(245, 246, 246, 1) 0%,
		rgba(219, 220, 226, 1) 21%, rgba(184, 186, 198, 1) 49%,
		rgba(221, 223, 227, 1) 80%, rgba(245, 246, 246, 1) 100%);
	/* Chrome10+,Safari5.1+ */
	background: -o-linear-gradient(45deg, rgba(245, 246, 246, 1) 0%,
		rgba(219, 220, 226, 1) 21%, rgba(184, 186, 198, 1) 49%,
		rgba(221, 223, 227, 1) 80%, rgba(245, 246, 246, 1) 100%);
	/* Opera 11.10+ */
	background: -ms-linear-gradient(45deg, rgba(245, 246, 246, 1) 0%,
		rgba(219, 220, 226, 1) 21%, rgba(184, 186, 198, 1) 49%,
		rgba(221, 223, 227, 1) 80%, rgba(245, 246, 246, 1) 100%); /* IE10+ */
	background: linear-gradient(45deg, rgba(245, 246, 246, 1) 0%,
		rgba(219, 220, 226, 1) 21%, rgba(184, 186, 198, 1) 49%,
		rgba(221, 223, 227, 1) 80%, rgba(245, 246, 246, 1) 100%); /* W3C */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f5f6f6',
		endColorstr='#f5f6f6', GradientType=1);
	/* IE6-9 fallback on horizontal gradient */
	margin-bottom: 60px;
}

.con-wrapper {
	margin-top: 3%;
}

.logo-holder {
	margin-top: 3%;
}

html {
	position: relative;
	min-height: 100%;
}
</style>
</head>
<body>

	<div class="logo-holder" align="center">
		<a href="<c:url value="/"/>"><img src="${pageContext.request.contextPath}/resources/img/logo.png" class="img-responsive" alt="logo" width="28%"></a>
	</div>

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
										<a class="btn btn-info
										 navbar-btn" href="<c:url value="/message/edit"/>">Write letter</a>
									</div>
								</li>
								<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Folders<span class="caret"></span></a>
									<ul class="dropdown-menu" role="menu">
										<c:forEach items="${folders}" var="folder">
											<li><a href="<c:url value="/message/account"/>?folderId=${folder.directoryId}">${folder.name}</a></li>
										</c:forEach>
									</ul></li>
							</ul>
							<ul class="nav navbar-nav navbar-right">
								<li>
									<div class="button-group" role="group">
										<button class="btn btn-default navbar-btn active">
											<c:out value=" ${currentFolder.name}" />
										</button>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="panel-group " id="accordion">
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
								<a class="btn btn-danger" href="<c:url value = "/message/move/${message.messageId}?folderId=4"/>">Move to bucket</a> <a class="btn btn-warning"
									href="<c:url value = "/message/move/${message.messageId}?folderId=3"/>">Mark as spam</a> <a class="btn btn-info"
									href="<c:url value = "/message/redirect/${message.messageId}"/>">Resent</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<%--For displaying Previous link except for the 1st page --%>


	<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
	<div class="container">
		<table cellpadding="10">
			<tr>
				<c:if test="${currentPage != 1}">
					<td><a class="btn btn-primary" href="<c:url value="/message/account"/>?page=${currentPage - 1}&folderId=${currentFolder.directoryId}">Previous</a></td>
				</c:if>
				<c:forEach begin="1" end="${noOfPages}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<td class="btn btn-default">${i}</td>
						</c:when>
						<c:otherwise>
							<td><a class="btn btn-info" href="<c:url value="/message/account"/>?page=${i}&folderId=${currentFolder.directoryId}">${i}</a></td>
						</c:otherwise>

					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages}">
					<td><a class="btn btn-primary" href="<c:url value="/message/account"/>?page=${currentPage + 1}&folderId=${currentFolder.directoryId}">Next</a></td>
				</c:if>
			</tr>
		</table>
	</div>
	<%--For displaying Next link --%>





	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
