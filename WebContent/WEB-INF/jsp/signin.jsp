<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Online Timesheet System</title>
<meta name="keywords" content="Timeheets, Time, Clock" />
<meta name="description"
	content="This is TIMEX, the Online Timesheet System for ACME Company Inc." />
<link href="styles/default.css" rel="stylesheet" type="text/css" />
<link href="styles/menu.css" rel="stylesheet" type="text/css" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/menu.js" type="text/javascript"></script>
</head>
<body>

		<jsp:include page="../../header.jsp"/>
		
	<div id="page">
		<div id="content">
			<form method="post">
				<table align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table align="center" cellpadding="4" cellspacing="0"
								bordercolor="#CCCCCC">
								<tr valign="middle">
									<td width="90%" valign="middle">
										<h1>Sign In</h1><br />
									</td>
								</tr>
								<tr>
									<td colspan="2"><center> <spring:bind
											path="command.*">
											<c:if test="${not empty status.errorMessages}">
												<c:forEach var="error" items="${status.errorMessages}">
													<font color="red"> <c:out value="${error}"
														escapeXml="false" /> </font>
													<br />
												</c:forEach>
											</c:if>
										</spring:bind> <!-- status messages --> <c:if test="${not empty message}">
											<font color="green"> <c:out value="${message}" /></font>
											<c:set var="message" value="" scope="session" />
										</c:if></center>

										<div align="center">
											<p>Please provide your authentication information below.</p>
											<p>
												Employee Id:
												<spring:bind path="command.id">
													<input name='<c:out value="${status.expression}"/>'
														value='<c:out value="${status.value}"/>' type="text"
														size="6" maxlength="6">
												</spring:bind>
												&nbsp;Password:
												<spring:bind path="command.password">
													<input name='<c:out value="${status.expression}"/>'
														value='<c:out value="${status.value}"/>' type="password"
														size="8" maxlength="10">
												</spring:bind>
												&nbsp;<input type="submit" name="Submit" value="Sign In">
											</p>
										</div>
										</form>
									</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
		</div>
		<!-- end #content -->

		<jsp:include page="../../sidebar_home.jsp"/>
		
		<div style="clear: both; height: 1px;"></div>
	</div>
<!-- end #page -->

		<jsp:include page="../../footer.jsp"/>
		
</body>
</html>
</body>
</html>
