<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

		<jsp:include flush="false" page="../../header.jsp"/>
		
	<div id="page">
		<div id="content">
			<table align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<table align="center" cellpadding="4" cellspacing="0"
							bordercolor="#CCCCCC">
							<tr valign="middle">
								<td width="90%" height="60" valign="middle">
									<h1>Timesheet List</h1> <br /></td>
								<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
										out</a></td>
							</tr>
							<tr>
								<td colspan="2">
									<p align="center">
										<!-- status messages -->
										<c:if test="${not empty message}">
											<font color="green"> <c:out value="${message}" /></font>
											<c:set var="message" value="" scope="session" />
										</c:if>
									</p> <br />
									<tr>
										<td>&nbsp;</td>
										<td rowspan="2"><img src="${fileURL}${employee.pictureFilename}" height="80" width="60" /></td>
									</tr>
									<tr>
										<td>
											<div align="left">
												Employee :
												<c:out value="${employee.name}" />
											</div>
											<div align="left">
												Type :
												<c:out value="${employee.employeeType}" />
											</div>
										</td>
									</tr>
									<p></p>
									<table border="0" align="center" cellpadding="8"
										cellspacing="10">
										<tr>
											<th>Period Ending</th>
											<th>Hours</th>
											<th>Department</th>
											<th>Status</th>
											<th>Timesheet Id</th>
										</tr>
										<c:forEach items="${timesheetsJSPVar}" var="t">
											<tr>
												<td align="center"><c:if test="${t.statusCode == 80 || t.statusCode == 68}"> <!-- P or D -->
														<a
															href='enterhours.htm?tid=<c:out value="${t.id}"/>'>
															<fmt:formatDate value="${t.periodEndingDate}" type="date"
																pattern="MM/dd/yyyy" /> </a>
													</c:if> <c:if test="${t.statusCode == 65 || t.statusCode == 83}"> <!-- A or S -->
														<a
															href='printhours.htm?tid=<c:out value="${t.id}"/>'>
															<fmt:formatDate value="${t.periodEndingDate}" type="date"
																pattern="MM/dd/yyyy" /> </a>
													</c:if>
												</td>
												<td align="center"><fmt:formatNumber
														value="${t.totalMinutes / 60}" pattern="0.00" /></td>
												<td align="center"><c:out value="${t.department.name}" />
													<td align="center"><c:out value="${t.statusCode}" />
														<div></div>
												</td>
													<td align="center"><c:out value="${t.id}" />
														<div></div>
												</td>
											</tr>
										</c:forEach>
									</table></td>
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
