<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

		<jsp:include flush="false" page="../../header.jsp"/>
		
	<div id="page">
		<div id="content">
			<form method="post">
				<table align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="4"
								cellspacing="0" bordercolor="#CCCCCC">
								<tr valign="middle">
									<td width="90%" height="60" valign="middle">
										<h1>Approve Timesheets</h1> <br /></td>
									<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
											out</a></td>
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
											<font color="green"> <c:out value="${message}" /> </font>
											<c:set var="message" value="" scope="session" />
										</c:if></center> <br>
											<table align="center" cellpadding="8" cellspacing="20">
												<tr>
													<th scope="row">
														<div align="center" class="style30">Employee</div></th>
													<th>
														<div align="center" class="style30">Hours For Week</div></th>
													<th>
														<div align="center" class="style30">Status</div></th>
													<th>
														<div align="center" class="style30">Approve</div></th>
												</tr>
												<c:forEach items="${command.timesheets}" var="timesheet"
													varStatus="tStatus">
													<tr>
														<td scope="row">
															<div align="left" class="style30">
																<a
																	href='printhours.htm?tid=<c:out value="${timesheet.id}"/>'>
																	<c:out value="${timesheet.employee.name}" />, <fmt:formatDate
																		value="${timesheet.periodEndingDate}" type="date"
																		pattern="MM/dd/yyyy" /> </a>
															</div></td>
														<td nowrap>
															<div align="right" class="style30">
																<fmt:formatNumber value="${timesheet.totalMinutes / 60}"
																	pattern="0.00" />
																<c:set var="incrementor"
																	value="${incrementor + (timesheet.totalMinutes / 60)}" />
															</div></td>
														<td align="center"
															title="P: pending
			A: approved
			C: paid
			S: submitted
			D: disapproved"><c:out
																value="${timesheet.statusCode}" />
														</td>
														<td nowrap>
															<div align="left" class="style30">
																<spring:bind
																	path="command.timesheets[${tStatus.index}].statusCode">
																	<input name='${status.expression}' type="radio"
																		value="A"
																		<c:if test="${status.value == 'Yes'}">checked</c:if>>
																		Yes&nbsp;&nbsp; <input name='${status.expression}'
																		type="radio" value="D"
																		<c:if test="${status.value == 'No'}">checked</c:if>>
																			No&nbsp;&nbsp; 
																</spring:bind>
															</div></td>
													</tr>
												</c:forEach>
												<tr><td colspan=4>
												<c:if test="${empty command.timesheets}">
													<font color="black"> <c:out value="There are no timesheets to approve" /> </font>
													<br />
												</c:if></td>
												</tr>
												<tr bgcolor="#CCCCCC">
													<td scope="row"><span class="style30"><strong>TOTAL</strong>
													</span>
													</td>
													<td>
														<div align="center" class="style30">
															<strong><c:out value="${incrementor}" /> </strong>
														</div></td>
													<td></td>
													<td></td>
												</tr>
											</table>
									</td>
								</tr>
								<tr>
									<td colspan="2" bgcolor="#C2DCEB">&nbsp;</td>
								</tr>
							</table></td>
					</tr>
				</table>
				<div align="center">
					<input name="SaveChanges" type="submit" value="Send" />
					<input name="Reset" type="button" value="Reset" onclick="javascript:document.forms[0].reset()" />
					<input name="Cancel" type="button" value="Cancel" onclick="javascript:window.location='staffhours.htm'" />
				</div>
			</form>
		</div>
		<!-- end #content -->
		<div id="sidebar">
			<div id="news" class="boxed1">
				<h2 class="title">News &amp; Updates</h2>
				<div class="content">
					<ul>
						<li>
							<h3>
								February 14, 2012: <a href="#">TIMEX Training</a>
							</h3>
							<p>
								We are having a 2 hr training session on TIMEX. Please <a
									href="#">register here&hellip;</a>
							</p></li>
						<li>
							<h3>
								March 31, 2012: <a href="#">End of Quarter Reports</a>
							</h3>
							<p>
								Make sure you submit your timesheets before this date for end of
								the quarter reports. <a href="#">Read more&hellip;</a>
							</p></li>
						<li>
							<h3>
								June 30, 2012: <a href="#">End of Quarter Reports</a>
							</h3>
							<p>
								Make sure you submit your timesheets before this date for end of
								the quarter reports. <a href="#">Read more&hellip;</a>
							</p></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both; height: 1px;"></div>
	</div>
	<!-- end #page -->
	<div id="footer">
		<p>
			Copyright &copy; 2012 TIMEX - Online Timesheet System for <a
				href="http://localhost:8080">ACME Company Inc.</a>
		</p>
	</div>
</body>
</html>
